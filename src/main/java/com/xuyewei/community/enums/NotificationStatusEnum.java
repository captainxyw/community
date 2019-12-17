package com.xuyewei.community.enums;

/**
 * ClassName:NotificationStatusEnum
 * Package:com.xuyewei.community.enums
 * Description:
 *
 * @Date:2019/12/17 20:51
 * @Author:xuyewei
 */

public enum NotificationStatusEnum {
    UNREAD(0), READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
