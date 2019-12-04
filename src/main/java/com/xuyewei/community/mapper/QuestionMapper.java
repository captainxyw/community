package com.xuyewei.community.mapper;

import com.xuyewei.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:QuestionMapper
 * Package:com.xuyewei.community.mapper
 * Description:
 *
 * @Date:2019/12/4 20:52
 * @Author:xuyewei
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title, description, gmt_modified, creator,  tag) " +
            "values(#{title},#{description},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);
}
