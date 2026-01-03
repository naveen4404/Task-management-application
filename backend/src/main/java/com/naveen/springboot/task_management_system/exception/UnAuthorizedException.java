package com.naveen.springboot.task_management_system.exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String msg) {
        super(msg);
    }
}
