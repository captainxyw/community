package com.xuyewei.community.dto;

import com.xuyewei.community.model.User;
import lombok.Data;

/**
 * ClassName:CommentDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/14 21:46
 * @Author:xuyewei
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;

    private User user;

}
