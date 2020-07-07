package com.lion.blog.controller;

import com.lion.blog.bean.Comments;
import com.lion.blog.bean.CommentsJSON;
import com.lion.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;


    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentsJSON commentsJSON) {

        Comments comments = new Comments();
        comments.setArticleId(commentsJSON.getArticleId());
        comments.setContent(commentsJSON.getContent());
        comments.setCommentator(commentsJSON.getCommentator());
        comments.setCreatetime(new Date());
        comments.setUpdatetime(new Date());
        commentsService.insert(comments);
        return "成功！";
    }

    @ResponseBody
    @PostMapping("/comment/del")
    public String delComment(Integer id, Integer articleId) {
        commentsService.delComment(id, articleId);
        return "success";
    }
}
