package vn.gotik.sample.service.secure.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Value("${aws.cognito.client-id}")
    private String clientId;

    @Value("${aws.cognito.region}")
    private String region;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        log.info("Attempting to log in with username: {}", username);

        try {
            String cognitoUrl = "https://cognito-idp." + region + ".amazonaws.com/";
            String requestBody = objectMapper.writeValueAsString(Map.of(
                    "AuthFlow", "USER_PASSWORD_AUTH",
                    "ClientId", clientId,
                    "AuthParameters", Map.of(
                            "USERNAME", username,
                            "PASSWORD", password
                    )
            ));
            log.debug("Request body for Cognito prepared");

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(cognitoUrl))
                    .header(HttpHeaders.CONTENT_TYPE, "application/x-amz-json-1.1")
                    .header("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            log.debug("Response received from Cognito");

            JsonNode jsonResponse = objectMapper.readTree(response.body());
            if (response.statusCode() == 200) {
                log.info("Login successful for username: {}", username);
                return ResponseEntity.ok(jsonResponse.get("AuthenticationResult"));
            } else {
                log.warn("Login failed for username: {}. Status code: {}", username, response.statusCode());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
            }
        } catch (IOException | InterruptedException e) {
            log.error("Error while logging in for username: {}", username, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error"));
        }
    }
}
