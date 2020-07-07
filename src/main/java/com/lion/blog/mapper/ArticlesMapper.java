package com.lion.blog.mapper;

import com.lion.blog.bean.Articles;
import com.lion.blog.bean.ArticlesDTO;
import com.lion.blog.bean.PaginationDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticlesMapper {

    /**
     * 插入文章数据
     * @param articles
     */
    @Insert("insert into article(creator,category,title,state,description,createtime,updatetime,articleCommentCount,articleViews) values(#{creator}, #{category}, #{title}, #{state}, #{description}, #{createtime}, #{updatetime}, #{articleCommentCount}, #{articleViews})")
    public void create(Articles articles);

    /**
     * 查询所有审核通过的文章
     * @param offset
     * @param size
     * @return
     */
    @Select("select a.*,u.nickname,c.name,u.id as userId from user u, article a, category c where a.creator = u.id and a.category = c.id and state = 3 and title like #{search} order by a.createtime desc limit #{size} offset #{offset}")
    public List<ArticlesDTO> list(Integer offset, Integer size, String search);

    /**
     * 通过用户ID查询审核通过的文章
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("select a.*,u.nickname,c.name,u.id as userId from user u, article a, category c where a.creator = u.id and a.category = c.id and state = 3 and u.id = #{userId} order by a.createtime desc limit #{size} offset #{offset}")
    public List<ArticlesDTO> listByUserId(Integer userId, Integer offset, Integer size);

    /**
     * 通过用户ID查询所有文章
     * @param userId
     * @param offset
     * @param size
     * @return
     */
    @Select("select a.*,u.nickname,c.name,u.id as userId,s.name stateName from user u, article a, category c,state s where a.creator = u.id and a.category = c.id and a.state = s.id and u.id = #{userId} order by a.createtime desc limit #{size} offset #{offset}")
    public List<ArticlesDTO> listManageByUserId(Integer userId, Integer offset, Integer size);


    /**
     * 根据用户ID统计所有文章数量
     * @param userId
     * @return
     */
    @Select("select count(1) from user u, article a, category c,state s where a.creator = u.id and a.category = c.id and a.state = s.id and u.id = #{userId} ")
    public Integer countManageByUserId(Integer userId);

    /**
     * 根据用户ID统计审核通过文章总数
     * @return
     */
    @Select("select count(1) from user u, article a, category c where a.creator = u.id and a.category = c.id and state = 3 and u.id = #{userId}")
    public Integer countByUserId(Integer UserId);

    /**
     * 统计审核中文章总数
     * @return
     */
    @Select("select count(1) from article a,user u,category c where a.creator = u.id and a.category = c.id and state = 2")
    public Integer countState();
    /**
     * 统计文章通过总数
     * @return
     */
    @Select("select count(1) from user u, article a, category c where a.creator = u.id and a.category = c.id and state = 3 and a.title like #{search}")
    public Integer count(String search);

    /**
     * 查询文章通过ID
     * @param id
     * @return
     */
    @Select("select a.*,u.nickname from user u, article a where u.id = a.creator and a.id = #{id}")
    public ArticlesDTO getArticlesById(Integer id);

    @Update("update article set title=#{title},description=#{description},category=#{category},state=#{state} where id=#{id}")
    public void update(Articles articles);

    @Delete("delete from article where id = #{id}")
    void delete(Integer id);

    /**
     * 更新评论数
     * @param id
     */
    @Update("update article set articleCommentCount = (select count(1) from comment where articleId = #{id}) where id = #{id}")
    void updateCommentCount(Integer id);

    /**
     * 累加浏览数
     * @param id
     */
    @Update("update article set articleViews = (articleViews + 1) where id = #{id}")
    void addArticleViews(Integer id);
    /**
     * 查询待审核文章
     */
    @Select("select a.*, u.nickname , c.name from article a,user u,category c where a.creator = u.id and a.category = c.id and state = 2  order by a.createtime desc limit #{size} offset #{offset} ")
    public List<ArticlesDTO> listState(Integer offset, Integer size);

    @Update("update article set state = #{state} where id = #{id}")
    public void updateState(Integer id ,Integer state);

    /**
     * 删除某类别编号下的所有文章
     * @param cid
     */
    @Delete("delete from article where category = #{cid}")
    public void deleteArticleByCategory(Integer cid);

    /**
     * 统计某用户所有审核通过的文章浏览量
     * @param userId
     * @return
     */
    @Select("select sum(articleViews) from article where state = 3 and creator = #{userId}")
    public Integer countViewsByUserId(Integer userId);

    /**
     * 统计某用户所有审核通过的文章评论数
     * @param userId
     * @return
     */
    @Select("select sum(articleCommentCount) from article where state = 3 and creator = #{userId}")
    public Integer countCommentByUserId(Integer userId);

    /**
     *查询某作者5条浏览量最多的文章
     * @param userId
     * @return
     */
    @Select("select * from article where creator = #{userId} and state =3 order by articleViews desc limit 5")
    public List<Articles> listArticles(Integer userId);
}
