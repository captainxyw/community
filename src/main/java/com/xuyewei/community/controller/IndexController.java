package com.xuyewei.community.controller;

import com.xuyewei.community.dto.PaginationDTO;
import com.xuyewei.community.mapper.UserMapper;
import com.xuyewei.community.model.User;
import com.xuyewei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }

        PaginationDTO pagination = questionService.list(page, size);
            model.addAttribute("pagination", pagination);
        return "index";
    }

}
