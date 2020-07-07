package com.lion.blog.controller;

import com.lion.blog.bean.User;
import com.lion.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(String username, String password, HttpServletRequest request) {
        User user = loginService.doLogin(username, password);
        if(user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/quit")
    public String quitLogin(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/checkLogin")
    public Boolean isRight(String username, String password) {
        User user = loginService.doLogin(username, password);
        if(user != null) {
            return true;
        }
        return false;
    }
}
