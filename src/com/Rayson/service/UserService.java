package com.Rayson.service;

import com.Rayson.domain.PageBean;
import com.Rayson.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();





    void addUser(User user);



    void update(User user);



    void deleteUser(String id);



    User findUserById(String id);



    User login(User user);



    void deleteSelectedUser(String[] ids);



    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);


}
