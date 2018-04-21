package com.response;

import com.db.ApplicationUser; /**
 * @author vipul pachauri
 */
public class APIResponse {

    private String status;
    private ErrorResponse errorResponse;
    private Object data;

    public APIResponse() {

    }

    public APIResponse(String msg, Object object) {
        this.status=msg;
        this.data=object;

    }

    public APIResponse(String msg, ErrorResponse errorResponse) {
        this.status=msg;
        this.errorResponse=errorResponse;

    }

    public APIResponse(String status, ErrorResponse errorResponse, Object data) {
        this.status = status;
        this.errorResponse = errorResponse;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "status='" + status + '\'' +
                ", errorResponse=" + errorResponse +
                ", data=" + data +
                '}';
    }
}
