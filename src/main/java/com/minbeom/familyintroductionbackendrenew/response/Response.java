package com.minbeom.familyintroductionbackendrenew.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class Response {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private Object data;
}
