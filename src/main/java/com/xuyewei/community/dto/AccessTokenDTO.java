package com.xuyewei.community.dto;

import lombok.Data;

/**
 * ClassName:AccessTokenDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/3 19:34
 * @Author:xuyewei
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
