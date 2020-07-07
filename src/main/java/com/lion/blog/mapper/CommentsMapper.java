package com.lion.blog.mapper;

import com.lion.blog.bean.Comments;
import com.lion.blog.bean.CommentsDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {
    @Insert("insert into comment(articleId,commentator,createtime,updatetime,content) values(#{articleId},#{commentator},#{createtime},#{updatetime},#{content})")
    public void insert(Comments comments);

    @Select("select c.*,u.id uid,u.nickname uname from comment c,user u where c.commentator = u.id and articleId = #{articleId} order by c.createtime desc")
    public List<CommentsDTO> listComments(Integer articlesId);

    /**
     * 删除评论
     * @param id 主键
     */
    @Delete("delete from comment where id = #{id}")
    public void delComment(Integer id);

    /**
     * 最新的5条评论
     * @param userId
     * @return
     */
    @Select("select c.*,u.id uid,u.nickname uname,a.title from article a,comment c,user u where c.articleId = a.id AND c.commentator = u.id  and a.state = 3  and a.creator = #{userId} order by c.createtime  desc limit 5")
    public List<CommentsDTO> listNewComments(Integer userId);
}
