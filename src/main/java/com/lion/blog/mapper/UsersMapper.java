package com.lion.blog.mapper;

import com.lion.blog.bean.User;
import com.lion.blog.bean.UsersDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("select * from user where username = #{username}")
    public User getUserByName(String username);

    @Insert("insert into user(username,password,nickname,roleid,resume,createtime, updatetime) values(#{username}, #{password}, #{nickname}, 2, #{resume}, now(), now())")
    public void insertUsers(String username, String password, String nickname,String resume);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User checkUserLogin(String username, String password);

    /**
     * 通过id查找用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User getUserById(Integer id);

    /**
     * 查询所有用户信息
     * @param offset
     * @param size
     * @return
     */
    @Select("select u.id, u.username, u.nickname, u.roleid, u.createtime, r.rolename from user u, role r where u.roleid = r.id order by u.createtime asc limit #{size} offset #{offset}")
    public List<UsersDTO> listUsers(Integer offset, Integer size);

    /**
     * 查询用户总数
     * @return
     */
    @Select("select count(1) from user u, role r where u.roleid = r.id")
    public Integer countAllUsers();

    /**
     * 修改个人用户资料
     * @param id
     * @param nickname
     * @param updatetime
     */
    @Update("update user set nickname = #{nickname}, updatetime = #{updatetime}, resume = #{resume} where id = #{id}")
    public Integer updateProfile(Integer id, String nickname, String resume, Date updatetime);

    /**
     * 设为管理员
     * @param id
     */
    @Update("update user set roleid = 1 where id = #{id}")
    public void updateRole(Integer id);
}
