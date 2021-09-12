package com.example.alexthbot.fab.utils;

import org.springframework.core.ParameterizedTypeReference;

public class CollectionParams {

    public static <T> ParameterizedTypeReference<T> get() {
        return new ParameterizedTypeReference<T>() {
        };
    }
}
