package com.ExceptionHandler;

import com.response.APIResponse;
import com.response.ErrorResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author vipul pachauri
 */
@ControllerAdvice
public class GASportsExceptionHandler {

    @ExceptionHandler(GASportsException.class)
    @ResponseBody
    public APIResponse handleException(GASportsException e){
        return new APIResponse("Failure",new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public APIResponse handleException(ConstraintViolationException e){
        return new APIResponse("Failure",new ErrorResponse(e.getConstraintViolations().iterator().next().getMessageTemplate()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public APIResponse handleException(HttpMessageNotReadableException e){
        return new APIResponse("Failure",new ErrorResponse(e.getMessage()));
    }
}

