package com.response;

/**
 * @author vipul pachauri
 */
public class SuccessResponse {

    private String successCode;

    private String successMessage;

    public SuccessResponse(String successCode, String successMessage) {
        this.successCode = successCode;
        this.successMessage = successMessage;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
