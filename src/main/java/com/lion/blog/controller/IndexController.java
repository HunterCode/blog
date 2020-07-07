package com.lion.blog.controller;

import com.lion.blog.bean.PaginationDTO;
import com.lion.blog.service.ArticlesService;
import com.lion.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ArticlesService articlesService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "12") Integer size,@RequestParam(defaultValue = "") String search) {
        PaginationDTO paginationDTO = articlesService.listArticles(page, size, search);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("search", search);
        return "index";
    }






}
