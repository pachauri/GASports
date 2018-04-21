package com.response;

/**
 * @author vipul pachauri
 */
public class ErrorResponse {

    private String errorMsg;

    public ErrorResponse(){

    }

    public ErrorResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
