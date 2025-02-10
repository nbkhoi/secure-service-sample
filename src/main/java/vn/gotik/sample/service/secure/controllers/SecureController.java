package vn.gotik.sample.service.secure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, secure world!";
    }
}
