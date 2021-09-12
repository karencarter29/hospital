package com.gatewayapi.web.services;

import com.gatewayapi.model.Patient;
import com.gatewayapi.security.TokenConfig;
import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class SecurityService {

    private static final String ADDRESS_SECURITY_SERVICE = "http://localhost:8077";
    private static final String ADDRESS_PATIENT_SERVICE = "http://localhost:8082";

    private final RestTemplate restTemplate;
    private final TokenConfig tokenConfig;

    @Autowired
    public SecurityService(RestTemplateBuilder restTemplateBuilder, TokenConfig tokenConfig) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
        this.tokenConfig = tokenConfig;
    }

    public ResponseEntity<String> register(Map<String, Object> userInformation) {
        String url = ADDRESS_SECURITY_SERVICE + "/hospital/auth/register";
        ResponseEntity<String> response = restTemplate.postForEntity(url, userInformation, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            return response;
        }
        HttpHeaders headers = response.getHeaders();
        Patient patient = getPatientInformation(Objects.requireNonNull(headers.getFirst("Authorization")));
        String url1 = ADDRESS_PATIENT_SERVICE + "/patient";
        restTemplate.postForEntity(url1, patient, String.class);
        return response;
    }

    public ResponseEntity<String> auth(Map<String, Object> userInformation) {
        String url = ADDRESS_SECURITY_SERVICE + "/hospital/auth/login";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }

    private Patient getPatientInformation(String header) {
        Patient patient = new Patient();
        String token = header.replace(tokenConfig.getPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(tokenConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
        patient.setId(claims.get("id", String.class));
        patient.setFirstName(claims.get("firstName", String.class));
        patient.setLastName(claims.get("secondName", String.class));
        log.info("Patient: { " + patient.getId() + ", " + patient.getFirstName() + ", " + patient.getLastName() + " }");
        return patient;
    }
}
