package com.lion.blog.controller;

import com.lion.blog.bean.Articles;
import com.lion.blog.bean.ArticlesDTO;
import com.lion.blog.bean.Categorys;
import com.lion.blog.bean.User;
import com.lion.blog.service.ArticlesService;
import com.lion.blog.service.CategorysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private CategorysService categorysService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,Integer userId, Model model) {
        ArticlesDTO articlesDTO = articlesService.getArticlesById(id);
        model.addAttribute("id", id);
        model.addAttribute("title", articlesDTO.getTitle());
        model.addAttribute("description", articlesDTO.getDescription());
        List<Categorys> categorysList = categorysService.listCategoryByUserId(userId);
        model.addAttribute("categorys", categorysList);
        model.addAttribute("selected", articlesDTO.getCategory());
        return "publish";
    }
    @GetMapping("/publish")
    public String publish(Model model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "login";
        }
        List<Categorys> categorysList = categorysService.listCategoryByUserId(user.getId());
        model.addAttribute("categorys", categorysList);
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Integer creator, String title, String description, Integer category, Integer id, Integer state, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        List<Categorys> categorysList = categorysService.listCategoryByUserId(creator);
        model.addAttribute("categorys", categorysList);
        model.addAttribute("selected", category);
        model.addAttribute("state", state);
        if(StringUtils.isEmpty(title)) {
            model.addAttribute("error","文章标题不能为空！");
            return "publish";
        }
        if(StringUtils.isEmpty(description)) {
            model.addAttribute("error","文章内容不能为空！");
            return "publish";
        }
        if(category == null) {
            model.addAttribute("error","类别不能为空！");
            return "publish";
        }
        Articles articles = new Articles();
        articles.setId(id);
        articles.setTitle(title);
        articles.setDescription(description);
        articles.setState(state);
        articles.setCategory(category);
        articles.setCreator(creator);
        articles.setArticleCommentCount(0);
        articles.setArticleViews(0);
        articlesService.createOrUpdate(articles);
        categorysService.updateArticleCount(category);
        return "redirect:/";
    }
}


