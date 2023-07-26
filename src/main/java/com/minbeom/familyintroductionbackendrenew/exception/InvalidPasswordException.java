package com.minbeom.familyintroductionbackendrenew.exception;

import com.minbeom.familyintroductionbackendrenew.response.ErrorCode;
import org.springframework.validation.Errors;

public class InvalidPasswordException extends CustomException{
    private static final long serialVersionUID = -2116671122895194101L;
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD_USER);
    }
}
