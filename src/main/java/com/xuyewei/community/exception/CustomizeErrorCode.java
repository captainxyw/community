package com.xuyewei.community.exception;

/**
 * ClassName:CustomizeErrorCode
 * Package:com.xuyewei.community.exception
 * Description:
 *
 * @Date:2019/12/11 21:22
 * @Author:xuyewei
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不再了，要不要换个试试？");


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
