package com.xuyewei.community.mapper;

import com.xuyewei.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * ClassName:UserMapper
 * Package:com.xuyewei.community.mapper
 * Description:
 *
 * @Date:2019/12/3 23:30
 * @Author:xuyewei
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified, avatar_url) " +
            "values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token=#{token}, gmt_modified=#{gmtModified}, " +
            "avatar_url=#{avatarUrl} where id = #{id}")
    void update(User user);
}
