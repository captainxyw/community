package com.xuyewei.community.exception;

/**
 * ClassName:CustomizeException
 * Package:com.xuyewei.community.exception
 * Description:
 *
 * @Date:2019/12/11 21:11
 * @Author:xuyewei
 */

public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode code) {
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
