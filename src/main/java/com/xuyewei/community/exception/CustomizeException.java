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
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
