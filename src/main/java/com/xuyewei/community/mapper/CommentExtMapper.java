package com.xuyewei.community.mapper;

import com.xuyewei.community.model.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentExtMapper {
    int incCommentCount(@Param("record") Comment record);
}