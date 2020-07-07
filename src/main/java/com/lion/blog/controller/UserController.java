package com.lion.blog.controller;

import com.lion.blog.bean.PaginationDTO;
import com.lion.blog.service.ArticlesService;
import com.lion.blog.service.CommentsService;
import com.lion.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private CommentsService commentsService;

    /**
     * 个人主页
     * @param userId
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/user/{id}")
    public String user(@PathVariable(name = "id") Integer userId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "8") Integer size, Model model) {
        PaginationDTO paginationDTO = articlesService.listArticles(userId, page, size);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("user", usersService.getUserByID(userId));
        model.addAttribute("articleCount", articlesService.countArticleByUserId(userId));
        model.addAttribute("viewsCount", articlesService.countViewsByUserId(userId));
        model.addAttribute("commentCount", articlesService.countCommentByUserId(userId));
        model.addAttribute("comments", commentsService.listNewComments(userId));
        model.addAttribute("articleList", articlesService.listArticles(userId));
        return "userIndex";
    }

}
