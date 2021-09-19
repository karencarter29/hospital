package com.gatewayapi.web.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateExceptionHandler implements ResponseErrorHandler {

    private static final String LOG_INFO = "Handled error : ";
    private static final String REASON = "reason : ";

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode() == HttpStatus.CONFLICT ||
                clientHttpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR ||
                clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED;
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (response.getStatusCode() == HttpStatus.CONFLICT) {
                log.info(LOG_INFO + HttpStatus.CONFLICT + ", " + REASON + response.getBody());
            }
            if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                log.info(LOG_INFO + HttpStatus.UNAUTHORIZED + ", " + REASON + response.getBody());
            }
        }
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            log.info(LOG_INFO + response.getStatusCode());
        }
    }
}
