package com.griddynamics.mosb.catalog.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class CatalogItemNotFoundException extends RuntimeException {

    public CatalogItemNotFoundException(String message) {
        super(message);
    }

    public CatalogItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatalogItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
