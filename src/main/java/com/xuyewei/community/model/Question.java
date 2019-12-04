package com.xuyewei.community.model;

import lombok.Data;

/**
 * ClassName:Question
 * Package:com.xuyewei.community.model
 * Description:
 *
 * @Date:2019/12/4 20:59
 * @Author:xuyewei
 */
@Data
public class Question {
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

}
