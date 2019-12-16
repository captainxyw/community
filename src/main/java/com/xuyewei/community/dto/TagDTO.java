package com.xuyewei.community.dto;

import lombok.Data;

import java.util.List;

/**
 * ClassName:TagDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/16 21:52
 * @Author:xuyewei
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;


}
