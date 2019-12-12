package com.xuyewei.community.service;

import com.xuyewei.community.enums.CommentTypeEnum;
import com.xuyewei.community.exception.CustomizeErrorCode;
import com.xuyewei.community.exception.CustomizeException;
import com.xuyewei.community.mapper.CommentMapper;
import com.xuyewei.community.mapper.QuestionExtMapper;
import com.xuyewei.community.mapper.QuestionMapper;
import com.xuyewei.community.model.Comment;
import com.xuyewei.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:CommentService
 * Package:com.xuyewei.community.service
 * Description:
 *
 * @Date:2019/12/12 21:03
 * @Author:xuyewei
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException((CustomizeErrorCode.TARGET_PARAM_NOT_FOUND));
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException((CustomizeErrorCode.TYPE_PARAM_WRONG));
        }
        if(comment.getType() ==CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null) {
                throw new CustomizeException((CustomizeErrorCode.COMMENT_NOT_FOUND));
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

        }
    }
}
