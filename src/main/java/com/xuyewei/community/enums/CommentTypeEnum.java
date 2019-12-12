package com.xuyewei.community.enums;

/**
 * ClassName:CommentTypeEnum
 * Package:com.xuyewei.community.enums
 * Description:
 *
 * @Date:2019/12/12 21:01
 * @Author:xuyewei
 */

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
