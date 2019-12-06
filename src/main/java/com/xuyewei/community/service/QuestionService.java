package com.xuyewei.community.service;

import com.xuyewei.community.dto.PaginationDTO;
import com.xuyewei.community.dto.QuestionDTO;
import com.xuyewei.community.mapper.QuestionMapper;
import com.xuyewei.community.mapper.UserMapper;
import com.xuyewei.community.model.Question;
import com.xuyewei.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:QuestionService
 * Package:com.xuyewei.community.service
 * Description:
 *
 * @Date:2019/12/5 21:38
 * @Author:xuyewei
 */
//
@Service
public class QuestionService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private QuestionMapper questionMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);


        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
}
