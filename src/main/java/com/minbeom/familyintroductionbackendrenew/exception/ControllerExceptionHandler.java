package com.minbeom.familyintroductionbackendrenew.exception;

import com.minbeom.familyintroductionbackendrenew.response.ErrorCode;
import com.minbeom.familyintroductionbackendrenew.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);

        ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        log.error("handleInvalidParameterException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                .create()
                .status(errorCode.getStatus())
                .message(e.getLocalizedMessage())
                .errors(e.getErrors());
        
        return new ResponseEntity<>(response, HttpStatus.resolve(errorCode.getStatus()));
    }
}
