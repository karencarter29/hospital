package com.gatewayapi.web.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestTemplateExceptionHandler extends DefaultResponseErrorHandler {

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
