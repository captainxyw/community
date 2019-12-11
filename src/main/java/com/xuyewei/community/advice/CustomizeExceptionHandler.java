package com.xuyewei.community.advice;

import com.xuyewei.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:CustomizeExceptionHandler
 * Package:com.xuyewei.community.advice
 * Description:
 *
 * @Date:2019/12/11 21:01
 * @Author:xuyewei
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model) {
        if(ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服务冒烟了，要不你待会再试试");
        }
        return new ModelAndView("error");
    }
}
