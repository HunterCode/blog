package com.lion.blog.controller;

import com.lion.blog.bean.ArticlesDTO;
import com.lion.blog.bean.CommentsDTO;
import com.lion.blog.service.ArticlesService;
import com.lion.blog.service.CategorysService;
import com.lion.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private CategorysService categorysService;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/articles/{id}")
    public String Articles(@PathVariable(name = "id") Integer id, Model model) {
        ArticlesDTO articles = articlesService.getArticlesById(id);
        List<CommentsDTO> commentsList = commentsService.listComments(id);
        model.addAttribute("articles", articles);
        model.addAttribute("comments", commentsList);
        articlesService.addArticleViews(id);
        return "articles";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String Articles(Integer id,Integer category) {
        articlesService.delete(id);
        categorysService.updateArticleCount(category);
        return "result:success";
    }


}
