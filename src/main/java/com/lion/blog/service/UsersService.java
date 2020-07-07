package com.lion.blog.service;

import com.lion.blog.bean.PaginationDTO;
import com.lion.blog.bean.User;
import com.lion.blog.bean.UsersDTO;
import com.lion.blog.mapper.UsersMapper;
import com.lion.blog.utils.MyDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;

    public User getUserByID(Integer id) {
        return usersMapper.getUserById(id);
    }

    public PaginationDTO listManageUsers(Integer page, Integer size) {
        //分页
        PaginationDTO paginationDTO = new PaginationDTO();
        //偏移量
        int offset = (page - 1) * size;
        List<UsersDTO> usersDTOList =  usersMapper.listUsers(offset, size);
        for (UsersDTO usersDTO : usersDTOList) {
            //设置展示时间
            usersDTO.setShowtime(MyDateUtils.getFormatString(usersDTO.getCreatetime()));
        }
        //分页
        paginationDTO.setUsers(usersDTOList);
        //总数
        Integer totalCount = usersMapper.countAllUsers();
        //设置页数
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    public Integer updateProfile(Integer id, String nickname, String resume) {
        return usersMapper.updateProfile(id, nickname, resume, new Date());
    }

    public void updateRole(Integer id) {
        usersMapper.updateRole(id);
    }
}
