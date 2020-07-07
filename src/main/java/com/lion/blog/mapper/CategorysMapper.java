package com.lion.blog.mapper;

import com.lion.blog.bean.Categorys;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CategorysMapper {
    @Select("select * from category where uid = #{userId}")
    public List<Categorys> listCategoryByUserId(Integer userId);

    /**
     * 插入类别
     * @param categorys
     */
    @Insert("insert into category(name, uid, articleCount, createtime, updatetime) values(#{name},#{uid}, #{articleCount}, #{createtime}, #{updatetime})")
    public void insertCategory(Categorys categorys);

    /**
     * 修改类别
     * @param id
     * @param name
     * @param updatetime
     */
    @Update("update category set name = #{name}, updatetime = #{updatetime} where id = #{id}")
    public void updateCategory(Integer id, String name, Date updatetime);

    /**
     * 更新文章总数
     * @param id
     */
    @Update("update category set articleCount = (select count(1) from article where category = #{id}) where id = #{id}")
    public void updateArticleCount(Integer id);

    /**
     * 删除类别
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    public void deleteCategory(Integer id);
}