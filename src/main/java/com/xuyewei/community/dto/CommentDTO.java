package com.xuyewei.community.dto;

import lombok.Data;

/**
 * ClassName:CommentDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/11 23:50
 * @Author:xuyewei
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
