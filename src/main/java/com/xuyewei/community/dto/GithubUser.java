package com.xuyewei.community.dto;

import lombok.Data;

/**
 * ClassName:GithubUser
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/3 20:10
 * @Author:xuyewei
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;


}
