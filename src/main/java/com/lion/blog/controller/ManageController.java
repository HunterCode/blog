package com.lion.blog.controller;

import com.lion.blog.bean.Categorys;
import com.lion.blog.bean.PaginationDTO;
import com.lion.blog.bean.User;
import com.lion.blog.service.ArticlesService;
import com.lion.blog.service.CategorysService;
import com.lion.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ManageController {
    @Autowired
    private CategorysService categorysService;
    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private UsersService usersService;

    /**
     * 文章管理
     * @param model
     * @return
     */
    @GetMapping("/manage/articles")
    public String articles( @RequestParam(defaultValue = "1") Integer page, Model model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "login";
        }
        PaginationDTO paginationDTO = articlesService.listManageByUserId(user.getId(), page,20);
        model.addAttribute("pagination", paginationDTO);
        return "manageArticles";
    }

    /**
     * 类别管理
     * @param model
     * @return
     */
    @GetMapping("/manage/categorys")
    public String listCategorys(Model model, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "login";
        }
        List<Categorys> categorysList = categorysService.listCategoryByUserId(user.getId());
        model.addAttribute("categorys", categorysList);
        return "manageCategorys";
    }

    /**
     * 审核管理
     * @param model
     * @return
     */
    @GetMapping("/manage/state")
    public String state(@RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationDTO paginationDTO = articlesService.listManageState(page,20);
        model.addAttribute("pagination", paginationDTO);
        return "manageState";
    }

    /**
     * 个人资料管理
     * @param model
     * @return
     */
    @GetMapping("/manage/profile")
    public String profile(Model model, @RequestParam(required = false) String message) {
        if("success".equals(message)) {

            model.addAttribute("success", "修改成功");
        }
        if("error".equals(message)) {
            model.addAttribute("error", "修改失败");
        }
        return "manageProfile";
    }

    /**
     * 修改个人资料
     * @param id
     * @param nickname
     */
    @GetMapping("/manage/profile/update")
    public String updateProfile(Integer id, String nickname, String resume, HttpServletRequest request, Model model) {
        User user = (User)request.getSession().getAttribute("user");
        String message = null;
        if(usersService.updateProfile(id, nickname, resume) == 1 ) {
            user.setNickname(nickname);
            user.setResume(resume);
            request.getSession().setAttribute("user", user);
            message = "success";
        } else {
            message = "false";
        }
        return "redirect:/manage/profile?message=" + message;
    }

    @GetMapping("/check")
    @ResponseBody
    public void check(Integer id, Integer state) {
        articlesService.updateState(id, state);
    }

    /**
     * 增加类别
     * @param name
     * @param request
     */
    @GetMapping("/manage/category/add")
    @ResponseBody
    public void categoryAdd(String name, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        Categorys categorys = new Categorys();
        categorys.setUid(user.getId());
        categorys.setName(name);
        categorys.setArticleCount(0);
        categorys.setCreatetime(new Date());
        categorys.setUpdatetime(new Date());
        categorysService.insertCategory(categorys);
    }

    /**
     * 修改类别
     * @param id
     * @param name
     */
    @GetMapping("/manage/category/update")
    @ResponseBody
    public void categoryUpdate(Integer id, String name) {
        categorysService.updateCategory(id, name);
    }

    /**
     * 删除类别以及该类别所有文章
     * @param id
     */
    @PostMapping("/manage/category/delete")
    @ResponseBody
    public void categoryDelete(Integer id) {
        categorysService.deleteCategory(id);
        articlesService.deleteArticleByCategory(id);
    }
    /**
     * 用户管理
     * @param model
     * @return
     */
    @GetMapping("/manage/user")
    public String user(@RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationDTO paginationDTO = usersService.listManageUsers(page,20);
        model.addAttribute("pagination", paginationDTO);
        return "manageUser";
    }

    @PostMapping("/manage/user/role")
    @ResponseBody
    public void updateRole(Integer userId) {
        usersService.updateRole(userId);
    }


}
