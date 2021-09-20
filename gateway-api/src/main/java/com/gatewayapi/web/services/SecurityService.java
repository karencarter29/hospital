package com.gatewayapi.web.services;

import com.gatewayapi.model.Patient;
import com.gatewayapi.security.TokenConfig;
import com.gatewayapi.web.exceptions.handlers.RestTemplateExceptionHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class SecurityService {

    @Value("${security-service}")
    private static String securityServiceAddress;
    @Value("${patient-service}")
    private static String patientServiceAddress;

    private final RestTemplate restTemplate;
    private final TokenConfig tokenConfig;

    @Autowired
    public SecurityService(RestTemplateBuilder restTemplateBuilder, TokenConfig tokenConfig) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateExceptionHandler()).build();
        this.tokenConfig = tokenConfig;
    }

    public ResponseEntity<String> register(Map<String, Object> userInformation) {
        log.info("SecurityService#register(userInformation: {})", userInformation);
        String url = securityServiceAddress + "/hospital/auth/register";
        ResponseEntity<String> userRegisterResponse = restTemplate.postForEntity(url, userInformation, String.class);
        if (userRegisterResponse.getStatusCode() != HttpStatus.OK) {
            log.info("Registration is failed");
            return userRegisterResponse;
        }
        log.info("Registration is successful");
        HttpHeaders headers = userRegisterResponse.getHeaders();
        Patient patient = getPatientInformation(headers.getFirst("Authorization"));
        log.info("Patient: {}", patient);
        String url1 = patientServiceAddress + "/patient";
        ResponseEntity<String> patientRegisterResponse = restTemplate.postForEntity(url1, patient, String.class);
        if (patientRegisterResponse.getStatusCode() != HttpStatus.OK) {
            log.info("Patient registration is failed");
            return patientRegisterResponse;
        }
        log.info("Patient registration is successful");
        return userRegisterResponse;
    }

    public ResponseEntity<String> auth(Map<String, Object> userInformation) {
        log.info("SecurityService#auth(userInformation: {})", userInformation);
        String url = securityServiceAddress + "/hospital/auth/login";
        return restTemplate.postForEntity(url, userInformation, String.class);
    }

    private Patient getPatientInformation(String header) {
        log.info("SecurityService#getPatientInformation()");
        Patient patient = new Patient();
        if (header == null) {
            return patient;
        }
        String token = header.replace(tokenConfig.getPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(tokenConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
        patient.setId(claims.get("id", String.class));
        patient.setFirstName(claims.get("firstName", String.class));
        patient.setLastName(claims.get("secondName", String.class));
        return patient;
    }
}
