package com.xuyewei.community.mapper;

import com.xuyewei.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Insert("insert into question(title, description, gmt_modified, gmt_create, creator,  tag) " +
            "values(#{title},#{description},#{gmtModified},#{gmtCreate},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param("userId")Integer userId, @Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);
}
