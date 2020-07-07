package com.lion.blog.controller;

import com.lion.blog.bean.User;
import com.lion.blog.service.LoginService;
import com.lion.blog.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RegistController {

    @Autowired
    private RegistService registService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/doregist")
    @ResponseBody
    public Boolean doRegist(String username, String password, String nickname, HttpServletRequest request) {
        Boolean result = registService.doRegist(username,password, nickname);

        if(result) {
            User user = loginService.doLogin(username, password);
            if(user != null) {
                request.getSession().setAttribute("user", user);
            }
        }

        return result;
    }

    @GetMapping("/regist")
    public String regist() { return "regist"; }
}
