package com.response;

/**
 * @author vipul pachauri
 */
public class ErrorResponse {

    private String errorCode;

    private String errorMsg;

    private String errorCause;

    public ErrorResponse(){

    }

    public ErrorResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorResponse(String errorCode, String errorMsg, String errorCause) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorCause = errorCause;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause;
    }
}
