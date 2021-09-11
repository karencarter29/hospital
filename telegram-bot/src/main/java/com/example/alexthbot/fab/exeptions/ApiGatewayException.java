package com.example.alexthbot.fab.exeptions;

public class ApiGatewayException extends RuntimeException {
    private ApiGatewayException(String message) {
        super(message);
    }

    public static ApiGatewayException doctors() {
        return new ApiGatewayException("Ошибка получения докторов");
    }

    public static ApiGatewayException appointments() {
        return new ApiGatewayException("Ошибка получения записей");
    }

    public static ApiGatewayException dates() {
        return new ApiGatewayException("Ошибка получения свободных дней");
    }
}
