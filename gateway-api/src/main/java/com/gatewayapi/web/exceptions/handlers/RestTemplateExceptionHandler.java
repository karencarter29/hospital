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

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode() == HttpStatus.CONFLICT) {
            return true;
        }
        return false;
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            if (response.getStatusCode() == HttpStatus.CONFLICT) {
                log.info("Error handling : " + HttpStatus.CONFLICT);
            }
        }
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
        }
    }
}
