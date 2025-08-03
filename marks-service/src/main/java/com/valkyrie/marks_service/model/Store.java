package com.valkyrie.marks_service.model;

import org.springframework.http.HttpStatus;

public class Store<I> {
    private final I instance;
    private final HttpStatus status;

    private Store(HttpStatus status, I instance) {
        this.status = status; this.instance = instance;
    }

    public static <I> Store<I> initialize(HttpStatus status, I instance) {
        return new Store<>(status, instance);
    }

    public I getInstance() {return instance;}

    public HttpStatus getStatus() {return status;}
}
