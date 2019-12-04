package com.xuyewei.community.model;

import lombok.Data;

/**
 * ClassName:User
 * Package:com.xuyewei.community.model
 * Description:
 *
 * @Date:2019/12/3 23:33
 * @Author:xuyewei
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
