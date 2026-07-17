package com.bhojanalay.common.exception;

public class MobileAlreadyExistsException extends RuntimeException {

    public MobileAlreadyExistsException(String mobile) {
        super("Mobile already exists : " + mobile);
    }

}