package com.xuyewei.community.dto;

import lombok.Data;

/**
 * ClassName:QuestionQueryDTO
 * Package:com.xuyewei.community.dto
 * Description:
 *
 * @Date:2019/12/20 21:40
 * @Author:xuyewei
 */
@Data
public class QuestionQueryDTO {
  private String search;
  private Integer page;
  private Integer size;
}
