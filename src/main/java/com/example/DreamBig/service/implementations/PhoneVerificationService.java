package com.example.DreamBig.service.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PhoneVerificationService {

    @Value("${phone.verification.api.url}")
    private String apiUrl;

    @Value("${phone.verification.api.key}")
    private String apiKey;

    public boolean verifyPhoneNumber(String phoneNumber) throws Exception {
        try {
            String fullUrl = apiUrl + "?access_key=" + apiKey + "&number=" + phoneNumber + "&country_code=UA&format=1";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return responseBody.contains("\"valid\":true");
            } else {
                throw new RuntimeException("Error verifying phone number: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify phone number: " + e.getMessage(), e);
        }
    }
}

