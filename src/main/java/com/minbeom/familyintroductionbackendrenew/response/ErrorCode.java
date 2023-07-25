package com.minbeom.familyintroductionbackendrenew.response;

public enum ErrorCode {
    INVALID_PARAMETER(400, null, "유효하지 않은 데이터입니다"),
    VALIDATE_DUPLICATE_USER(401, "U001", "이미 존재하는 유저입니다"),
    INVALID_PASSWORD_USER(402, "U002", "비밀번호가 틀렸습니다.");

    private final int status;
    private final String code;
    private final String message;


    public int getStatus() {
        return status;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }



    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
