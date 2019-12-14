package com.xuyewei.community.controller;

import com.xuyewei.community.dto.CommentDTO;
import com.xuyewei.community.dto.QuestionDTO;
import com.xuyewei.community.service.CommentService;
import com.xuyewei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * ClassName:QuestionController
 * Package:com.xuyewei.community.controller
 * Description:
 *
 * @Date:2019/12/8 22:19
 * @Author:xuyewei
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        List<CommentDTO> comments = commentService.listByQuestionId(id);
        //增加阅读数
        questionService.incView(id);
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
