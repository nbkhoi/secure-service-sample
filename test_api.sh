#!/bin/bash

# API Endpoints
BASE_URL="http://localhost:8080/api"
LOGIN_URL="$BASE_URL/auth/login"
SECURE_URL="$BASE_URL/secure/hello"
PUBLIC_URL="$BASE_URL/public/hello"

# User credentials
USERNAME="khoinb@gotik.vn"
PASSWORD="12345678x@X"

# Function to print headers
print_header() {
    echo -e "\n===== $1 =====\n"
}

# Step 1: Call public API
print_header "STEP 1: Calling public API"
curl -i -X GET $PUBLIC_URL -H "Content-Type: application/json"

# Step 2: Login to get JWT token
print_header "STEP 2: Logging in to get JWT token"
TOKEN=$(curl -s -X POST $LOGIN_URL \
    -H "Content-Type: application/json" \
    -d "{\"username\": \"$USERNAME\", \"password\": \"$PASSWORD\"}" | jq -r '.IdToken')

if [ "$TOKEN" == "null" ] || [ -z "$TOKEN" ]; then
    echo "❌ Login failed! Check username/password or server status."
    exit 1
fi
echo "✅ JWT Token received: $TOKEN"

# Step 3: Call secure API without token (should return 403)
print_header "STEP 3: Calling secure API WITHOUT token (Expected: 403 Forbidden)"
curl -i -X GET $SECURE_URL

# Step 4: Call secure API with valid token
print_header "STEP 4: Calling secure API WITH valid token"
curl -i -X GET $SECURE_URL -H "Authorization: Bearer $TOKEN"

# Step 5: Call secure API with invalid token (should return 403)
print_header "STEP 5: Calling secure API WITH INVALID token (Expected: 403 Forbidden)"
curl -i -X GET $SECURE_URL -H "Authorization: Bearer INVALID_TOKEN"
