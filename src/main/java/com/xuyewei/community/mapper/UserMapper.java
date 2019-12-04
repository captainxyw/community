package com.xuyewei.community.mapper;

import com.xuyewei.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified) " +
            "values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

}
