package com.xuyewei.community.controller;

import com.xuyewei.community.dto.QuestionDTO;
import com.xuyewei.community.mapper.UserMapper;
import com.xuyewei.community.model.User;
import com.xuyewei.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String index(HttpServletRequest request, Model model) {
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

        List<QuestionDTO> questionList = questionService.list();
            for(QuestionDTO questionDTO : questionList) {
                questionDTO.setDescription("changed");
            }
            model.addAttribute("questions", questionList);
        return "index";
    }

}
