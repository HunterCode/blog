package com.lion.blog.service;

import com.lion.blog.bean.Categorys;
import com.lion.blog.bean.User;
import com.lion.blog.mapper.CategorysMapper;
import com.lion.blog.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CategorysMapper categorysMapper;

    public boolean doRegist(String username, String password, String nickname) {
        // 如果用户名存在，返回false
        if(hasUsername(username)) {
            return false;
        }
        String resume = "暂无";
        usersMapper.insertUsers(username, password, nickname, resume);
        Categorys categorys = new Categorys();
        categorys.setName("默认类别");
        categorys.setUid(usersMapper.getUserByName(username).getId());
        categorys.setArticleCount(0);
        categorys.setCreatetime(new Date());
        categorys.setUpdatetime(new Date());
        categorysMapper.insertCategory(categorys);
        return true;
    }

    /**
     * 用户名是否已存在
     * @param username 用户名
     * @return
     */
    public boolean hasUsername(String username) {
        User user = usersMapper.getUserByName(username);

        if(user == null) {
            return false;
        }
        return true;
    }

}
