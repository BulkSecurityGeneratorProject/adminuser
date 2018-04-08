package com.rishi.adminuser.util;

/**
 * @author raushanr
 *
 */

public class CustomErrorException {

    private String errorMessage;

    public CustomErrorException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
