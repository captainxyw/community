package com.xuyewei.community.service;

import com.xuyewei.community.dto.PaginationDTO;
import com.xuyewei.community.dto.QuestionDTO;
import com.xuyewei.community.dto.QuestionQueryDTO;
import com.xuyewei.community.exception.CustomizeErrorCode;
import com.xuyewei.community.exception.CustomizeException;
import com.xuyewei.community.mapper.QuestionExtMapper;
import com.xuyewei.community.mapper.QuestionMapper;
import com.xuyewei.community.mapper.UserMapper;
import com.xuyewei.community.model.Question;
import com.xuyewei.community.model.QuestionExample;
import com.xuyewei.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
  @Autowired
  private QuestionExtMapper questionExtMapper;
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private UserMapper userMapper;

  public PaginationDTO list(String search, Integer page, Integer size) {
    if (StringUtils.isNotBlank(search)) {
      String[] tags = StringUtils.split(search, " ");
      search = Arrays.stream(tags).collect(Collectors.joining("|"));
    }

    PaginationDTO paginationDTO = new PaginationDTO();
    Integer totalPage;

    QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
    questionQueryDTO.setSearch(search);
    Integer totalCount = (int) questionExtMapper.countBySearch(questionQueryDTO);
    if (totalCount % size == 0) {
      totalPage = totalCount / size;
    } else {
      totalPage = totalCount / size + 1;
    }

    if (page < 1) {
      page = 1;
    }
    if (page > totalPage) {
      page = totalPage;
    }

    paginationDTO.setPagination(totalPage, page);

    Integer offset = size * (page - 1);
    QuestionExample questionExample = new QuestionExample();
    questionExample.setOrderByClause("gmt_create desc");
    questionQueryDTO.setPage(offset);
    questionQueryDTO.setSize(size);
    List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
    ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();

    for (Question question : questions) {
      User user = userMapper.selectByPrimaryKey(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }

    paginationDTO.setData(questionDTOList);

    return paginationDTO;
  }

  public PaginationDTO list(Long userId, Integer page, Integer size) {
    PaginationDTO paginationDTO = new PaginationDTO();
    QuestionExample questionExample = new QuestionExample();
    questionExample.createCriteria().andCreatorEqualTo(userId);
    Integer totalCount = (int) questionMapper.countByExample(questionExample);


    Integer totalPage;
    if (totalCount % size == 0) {
      totalPage = totalCount / size;
    } else {
      totalPage = totalCount / size + 1;
    }

    if (page < 1) {
      page = 1;
    }
    if (page > totalPage) {
      page = totalPage;
    }
    paginationDTO.setPagination(totalPage, page);


    Integer offset = size * (page - 1);
//        QuestionExample questionExample1 = new QuestionExample();
//        questionExample1.createCriteria().andCreatorEqualTo(userId);
    List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

    ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();

    for (Question question : questions) {
      User user = userMapper.selectByPrimaryKey(question.getCreator());
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(question, questionDTO);
      questionDTO.setUser(user);
      questionDTOList.add(questionDTO);
    }

    paginationDTO.setData(questionDTOList);

    return paginationDTO;
  }

  public QuestionDTO getById(Long id) {
    Question question = questionMapper.selectByPrimaryKey(id);
    if (question == null) {
      throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
    }
    QuestionDTO questionDTO = new QuestionDTO();
    BeanUtils.copyProperties(question, questionDTO);
    User user = userMapper.selectByPrimaryKey(question.getCreator());
    questionDTO.setUser(user);
    return questionDTO;
  }

  public void createOrUpdate(Question question) {
    if (question.getId() == null) {
      //创建
      question.setGmtCreate(System.currentTimeMillis());
      question.setGmtModified(question.getGmtCreate());
      question.setViewCount(0);
      question.setLikeCount(0);
      question.setCommentCount(0);
      questionMapper.insert(question);
    } else {
      //更新

      Question updateQuestion = new Question();
      updateQuestion.setGmtModified(question.getGmtCreate());
      updateQuestion.setTitle(question.getTitle());
      updateQuestion.setDescription(question.getDescription());
      updateQuestion.setTag(question.getTag());

      QuestionExample example = new QuestionExample();
      example.createCriteria().andIdEqualTo(question.getId());
      int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
      if (updated != 1) {
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
    }
  }

  public void incView(Long id) {
    Question question = new Question();
    question.setId(id);
    question.setViewCount(1);
    questionExtMapper.incView(question);

  }

  public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
    if (StringUtils.isBlank(queryDTO.getTag())) {
      return new ArrayList<>();
    }
    String[] tags = StringUtils.split(queryDTO.getTag(), ",");
    String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
    Question question = new Question();
    question.setId(queryDTO.getId());
    question.setTag(regexpTag);

    List<Question> questions = questionExtMapper.selectRelated(question);
    List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
      QuestionDTO questionDTO = new QuestionDTO();
      BeanUtils.copyProperties(q, questionDTO);
      return questionDTO;
    }).collect(Collectors.toList());
    return questionDTOS;
  }
}
