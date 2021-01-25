package com.Rayson.service.impl;

import com.Rayson.dao.UserDao;
import com.Rayson.dao.impl.UserDaoImpl;
import com.Rayson.domain.PageBean;
import com.Rayson.domain.User;
import com.Rayson.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override

    public List<User> findAll() {

        // 调用dao 查询

        return dao.findAll();

    }


    @Override

    public void addUser(User user) {

        // 添加用户

        dao.add(user);

    }


    @Override

    public void update(User user) {

        dao.update(user);

    }


    @Override

    public void deleteUser(String id) {

        dao.delete(Integer.parseInt(id));

    }


    @Override

    public User findUserById(String id) {


        return dao.findById(Integer.parseInt(id));

    }


    @Override

    public User login(User user) {


        return dao.findUserByUserNameAndPassword(user.getUsername(), user.getPassword());

    }


    @Override

    public void deleteSelectedUser(String[] ids) {
        UserDao userDao = new UserDaoImpl();
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                userDao.delete(Integer.parseInt(ids[i]));
            }

        }
    }


    @Override

    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);

        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {

            currentPage = 1;

        }

        // 创建 PageBean对象

        PageBean<User> pb = new PageBean();

        // 设置参数

        pb.setCurrentPage(currentPage);

        pb.setRows(rows);


        // 调用dao 的 查询总记录数

        int totalCount = dao.findTotalCount(condition);

        pb.setTotalCount(totalCount);

        // 调用 dao 分页查询

        int start = (currentPage - 1) * rows;

        List<User> list = dao.findByPage(start, rows, condition);

        pb.setList(list);

        // 计算总页码

        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : totalCount / rows + 1;

        pb.setTotalPage(totalPage);


        return pb;

    }
}
