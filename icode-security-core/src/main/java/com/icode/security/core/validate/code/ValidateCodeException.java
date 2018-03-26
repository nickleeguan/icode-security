package com.icode.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 5674134315623215459L;

    public ValidateCodeException(String msg){
        super(msg);
    }
}
