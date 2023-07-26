package com.minbeom.familyintroductionbackendrenew.exception;

import com.minbeom.familyintroductionbackendrenew.response.ErrorCode;

public class ValidateDuplicateUserException extends CustomException{
    public ValidateDuplicateUserException() {
        super(ErrorCode.VALIDATE_DUPLICATE_USER);
    }
}
