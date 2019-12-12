package com.xuyewei.community.advice;

import com.alibaba.fastjson.JSON;
import com.xuyewei.community.dto.ResultDTO;
import com.xuyewei.community.exception.CustomizeErrorCode;
import com.xuyewei.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    @ResponseBody
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            //返回json
            if(ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException e) {

            }
            return null;
        } else {
            //错误页面条转
            if(ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
