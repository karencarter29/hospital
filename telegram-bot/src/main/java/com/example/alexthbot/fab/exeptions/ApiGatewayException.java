package com.example.alexthbot.fab.exeptions;

public class ApiGatewayException extends RuntimeException {

    private ApiGatewayException(String message) {
        super(message);
    }
    public static ApiGatewayException doctors() {

        return new ApiGatewayException("Error getting doctors");
    }

    public static ApiGatewayException appointments() {
        return new ApiGatewayException("Error retrieving appointments");
    }

    public static ApiGatewayException dates() {
        return new ApiGatewayException("Ошибка получения свободных дней");
    }

    public static ApiGatewayException procedures() {
        return new ApiGatewayException("No procedures available");
    }
    public static ApiGatewayException login() {
        return new ApiGatewayException("There is no such login or password");
    }

    public static ApiGatewayException times() {
        return new ApiGatewayException("No available time for this date");
    }
}
