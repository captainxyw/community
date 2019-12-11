package com.xuyewei.community.mapper;

import com.xuyewei.community.model.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionExtMapper {

    int incView(@Param("record") Question record);


}