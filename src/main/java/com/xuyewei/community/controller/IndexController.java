package com.xuyewei.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:HelloController
 * Package:com.xuyewei.community.controller
 * Description:
 *
 * @Date:2019/12/1 20:55
 * @Author:xuyewei
 */
@Controller
public class IndexController {
    @GetMapping("/hello")
    public String index() {
        return "index";
    }

}
