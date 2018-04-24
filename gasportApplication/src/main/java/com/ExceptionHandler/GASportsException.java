package com.ExceptionHandler;

import enums.ErrorCodes; /**
 * @author vipul pachauri
 */
public class GASportsException extends RuntimeException {


    public GASportsException(String message) {
        super(message);
    }

}
