package com.lion.blog.service;

import com.lion.blog.bean.Comments;
import com.lion.blog.bean.CommentsDTO;
import com.lion.blog.mapper.CommentsMapper;
import com.lion.blog.utils.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private ArticlesService articlesService;

    public void insert(Comments comments) {
        commentsMapper.insert(comments);
        articlesService.updateCommentCount(comments.getArticleId());
    }

    public List<CommentsDTO> listComments(Integer articleId) {
        List<CommentsDTO> commentsDTOList = commentsMapper.listComments(articleId);
        for(CommentsDTO commentsDTO: commentsDTOList) {
            commentsDTO.setShowTime(MyDateUtils.getTimeString(commentsDTO.getCreatetime()));
        }
        return commentsDTOList;
    }

    /**
     * 删除评论
     * @param id
     */
    public void delComment(Integer id, Integer articleId) {
        commentsMapper.delComment(id);
        articlesService.updateCommentCount(articleId);
    }

    /**
     * 查询最新的5条评论
     * @param userId
     * @return
     */
    public List<CommentsDTO> listNewComments(Integer userId) {
        List<CommentsDTO> commentsDTOList = commentsMapper.listNewComments(userId);
        for(CommentsDTO commentsDTO : commentsDTOList) {
            commentsDTO.setShowTime(MyDateUtils.getTimeString(commentsDTO.getCreatetime()));
        }
        return commentsDTOList;
    }
}
