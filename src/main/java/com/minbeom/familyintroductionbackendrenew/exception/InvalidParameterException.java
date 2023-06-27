package com.minbeom.familyintroductionbackendrenew.exception;

import com.minbeom.familyintroductionbackendrenew.response.ErrorCode;
import org.springframework.validation.Errors;

public class InvalidParameterException extends CustomException{
    private final Errors errors;
    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
