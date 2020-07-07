package com.lion.blog.service;

import com.lion.blog.bean.Categorys;
import com.lion.blog.mapper.CategorysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategorysService {

    @Autowired
    private CategorysMapper categorysMapper;

    public List<Categorys> listCategoryByUserId(Integer userId) {
        return categorysMapper.listCategoryByUserId(userId);
    }

    /**
     * 插入类别
     * @param categorys
     */
    public void insertCategory(Categorys categorys){
        categorysMapper.insertCategory(categorys);
    };

    /**
     * 修改类别
     * @param id
     * @param name
     */
    public void updateCategory(Integer id, String name) {

        categorysMapper.updateCategory(id, name,new Date());
    }

    /**
     * 删除类别
     * @param id
     */
    public void deleteCategory(Integer id) {
        categorysMapper.deleteCategory(id);
    }
    /**
     * 更新文章总数
     * @param id
     */
    public void updateArticleCount(Integer id) {
        categorysMapper.updateArticleCount(id);
    }
}
