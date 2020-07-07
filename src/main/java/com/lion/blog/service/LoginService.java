package com.lion.blog.service;

import com.lion.blog.bean.User;
import com.lion.blog.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsersMapper usersMapper;

    public User doLogin(String username, String password) {
        return usersMapper.checkUserLogin(username, password);
    }
}
