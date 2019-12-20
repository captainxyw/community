package com.xuyewei.community.controller;

import com.xuyewei.community.dto.PaginationDTO;
import com.xuyewei.community.mapper.UserMapper;
import com.xuyewei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:HelloController
 * Package:com.xuyewei.c ommunity.controller
 * Description:
 *
 * @Date:2019/12/1 20:55
 * @Author:xuyewei
 */
@Controller
public class IndexController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDTO pagination = questionService.list(search, page, size);
        model.addAttribute("search", search);
        model.addAttribute("pagination", pagination);
        return "index";
    }

}
