package com.accesscontrol.manage.exception;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MyException.java
 * @createTime 2020年07月08日 09:35
 **/
public class MyException extends Exception {
    private String says;

    public String getSays() {
        return this.says;
    }

    public MyException(String message, String says) {
        super(message);
        this.says = says;
    }
}
