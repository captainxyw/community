package com.xuyewei.community.controller;

import com.xuyewei.community.dto.QuestionDTO;
import com.xuyewei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        //增加阅读数
        questionService.incView(id);
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
