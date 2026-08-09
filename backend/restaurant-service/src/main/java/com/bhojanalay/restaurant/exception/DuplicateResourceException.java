package com.bhojanalay.restaurant.exception;

import com.bhojanalay.restaurant.enums.ErrorCode;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String message) {
        super(message, ErrorCode.DUPLICATE_RESOURCE);
    }
}