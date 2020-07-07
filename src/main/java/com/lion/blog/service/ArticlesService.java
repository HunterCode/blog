package com.lion.blog.service;

import com.lion.blog.bean.Articles;
import com.lion.blog.bean.ArticlesDTO;
import com.lion.blog.bean.PaginationDTO;
import com.lion.blog.mapper.ArticlesMapper;
import com.lion.blog.utils.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesMapper articlesMapper;

    /**
     * 查找审核通过的文章
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO listArticles(Integer page, Integer size, String search) {
        search = "%" + search + "%";
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        //偏移量
        int offset = (page - 1) * size;
        List<ArticlesDTO> articlesDTOList =  articlesMapper.list(offset, size, search);
        for (ArticlesDTO articlesDTO : articlesDTOList) {
            //设置展示时间
            articlesDTO.setShowTime(MyDateUtils.getFormatString(articlesDTO.getCreatetime()));
        }
        //文章分页
        paginationDTO.setArticles(articlesDTOList);
        //文章总数
        Integer totalCount = articlesMapper.count(search);
        //设置文章页数
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    /**
     * 文章管理
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO listManageByUserId(Integer userId, Integer page, Integer size) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        //偏移量
        int offset = (page - 1) * size;
        List<ArticlesDTO> articlesDTOList =  articlesMapper.listManageByUserId(userId, offset, size);
        for (ArticlesDTO articlesDTO : articlesDTOList) {
            //设置展示时间
            articlesDTO.setShowTime(MyDateUtils.getFormatString(articlesDTO.getCreatetime()));
        }
        //文章分页
        paginationDTO.setArticles(articlesDTOList);
        //文章总数
        Integer totalCount = articlesMapper.countManageByUserId(userId);
        //设置文章页数
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    /**
     * 查找审核中文章
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO listManageState(Integer page, Integer size) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        //偏移量
        int offset = (page - 1) * size;
        List<ArticlesDTO> articlesDTOList =  articlesMapper.listState(offset, size);
        for (ArticlesDTO articlesDTO : articlesDTOList) {
            //设置展示时间
            articlesDTO.setShowTime(MyDateUtils.getFormatString(articlesDTO.getCreatetime()));
        }
        //文章分页
        paginationDTO.setArticles(articlesDTOList);
        //文章总数
        Integer totalCount = articlesMapper.countState();
        //设置文章页数
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    /**
     * 查找审核通过文章
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO listArticles(Integer userId, Integer page, Integer size) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        //偏移量
        int offset = (page - 1) * size;
        List<ArticlesDTO> articlesDTOList =  articlesMapper.listByUserId(userId, offset, size);
        for (ArticlesDTO articlesDTO : articlesDTOList) {
            //设置展示时间
            articlesDTO.setShowTime(MyDateUtils.getFormatString(articlesDTO.getCreatetime()));
        }
        //文章分页
        paginationDTO.setArticles(articlesDTOList);
        //文章总数
        Integer totalCount = articlesMapper.countByUserId(userId);
        //设置文章页数
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    /**
     *
     * @param id
     * @return
     */
    public ArticlesDTO getArticlesById(Integer id) {
        ArticlesDTO articlesDTO = articlesMapper.getArticlesById(id);
        articlesDTO.setShowTime(MyDateUtils.getFormatString(articlesDTO.getCreatetime()));
        return articlesDTO;
    }

    public void createOrUpdate(Articles articles) {

        if(articles.getId() == null) {
            articles.setCreatetime(new Date());
            articles.setUpdatetime(new Date());
            articlesMapper.create(articles);
        } else {
            articlesMapper.update(articles);
        }
    }

    public void delete(Integer id) {

        articlesMapper.delete(id);
    }

    public void updateState(Integer id, Integer state) {
        articlesMapper.updateState(id, state);
    }

    /**
     * 浏览量+1
     * @param id
     */
    public void addArticleViews(Integer id) {
        articlesMapper.addArticleViews(id);
    }

    /**
     * 更新评论数
     * @param id
     */
    public void updateCommentCount(Integer id) {
        articlesMapper.updateCommentCount(id);
    }

    /**
     * 删除某类别编号下的所有文章
     * @param cid
     */
    public void deleteArticleByCategory(Integer cid) {
        articlesMapper.deleteArticleByCategory(cid);
    }

    /**
     * 统计某用户的已通过文章数
     * @param userId
     * @return
     */
    public Integer countArticleByUserId(Integer userId) {
        return articlesMapper.countByUserId(userId);
    }

    /**
     * 统计某用户的浏览数
     * @param userId
     * @return
     */
    public Integer countViewsByUserId(Integer userId) {
        return articlesMapper.countViewsByUserId(userId);
    }

    /**
     * 查询某用户的评论数
     * @param userId
     * @return
     */
    public Integer countCommentByUserId(Integer userId) {
        return articlesMapper.countCommentByUserId(userId);
    }

    /**
     * 个人主页热门文章
     * @return
     */
    public List<Articles> listArticles(Integer userId){
        return articlesMapper.listArticles(userId);
    }
}
