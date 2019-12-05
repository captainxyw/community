package com.xuyewei.community.dto;

import com.xuyewei.community.model.User;
import lombok.Data;

/**
 * ClassName:QuestionDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/5 21:34
 * @Author:xuyewei
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

    private User user;
}
