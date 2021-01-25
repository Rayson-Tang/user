package com.Rayson.dao;

import com.Rayson.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();



    public  void add(User user);



    public void update(User user);



    public void delete(int id);



    public User findById(int id);



    public User findUserByUserNameAndPassword(String username, String password);

    /**

     * 查询总记录数

     */

    int findTotalCount( Map<String, String[]> condition);

    /**

     * 分页查询每页记录

     */

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
