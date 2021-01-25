package com.Rayson.dao.impl;

import com.Rayson.dao.UserDao;
import com.Rayson.domain.User;
import com.Rayson.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override

    public List<User> findAll() {

        String sql = "select * from user";

        // query():查询结果，将结果封装为JavaBean对象 * query的参数：RowMapper

        // * 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装  new BeanPropertyRowMapper<类型>(类型.class)

//        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

//        return users;

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));

    }



    @Override

    public void add(User user) {

        String sql = "insert into user values(null, ?,?,?,?,?,?,null ,null)";

        int i = template.update(sql, user.getName(),

                user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

        System.out.println(i);

    }



    @Override
    public void update(User user){
        String sql = "update user set name = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());

    }




    @Override

    public void delete(int id){
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }



    @Override
public User findById(int id){
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }




    @Override

    public User findUserByUserNameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where num = ? and password = ?";
            User user = (User) template.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    @Override

    public int findTotalCount(Map<String, String[]> condition) {

        String sql = "SELECT COUNT(*) FROM user where 1 = 1";

        StringBuilder sb = new StringBuilder(sql);

        // 遍历map

        Set<String> keySet = condition.keySet();

        // {name : "李" , address :"广"   email = "111"}



        List<Object> params = new ArrayList<>();



        for (String key :keySet){

            // 排除 分页的条件参数

            if ("currentPage".equals(key) || "rows".equals(key)){

                continue;

            }

//            SELECT count(*) FROM user WHERE 1=1 AND name LIKE '%李%'

            // 获取value值

            String value = condition.get(key)[0];

            // 判断value是否有值

            if (value !=null && "".equals(value)){

                sb.append("  and "+ key +"like ?  ");

                params.add("%" + value+"%");

            }

        }

        System.out.println("sql findTotalCount" +sb.toString());

        System.out.println(params);





//        return template.queryForObject(sb.toString(), Integer.class);

        return template.queryForObject(sb.toString(), Integer.class, params.toArray());

    }



    @Override

    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {



        String sql = "select * from user where 1= 1  ";

//        String sql = "select * from user  limit ?,?";

        StringBuilder sb = new StringBuilder(sql);

        Set<String> keySet = condition.keySet();

//

        List<Object> params = new ArrayList<>();



        for (String key :keySet){

            // 排除 分页的条件参数

            if ("currentPage".equals(key) || "rows".equals(key)){

                continue;

            }

//            SELECT count(*) FROM user WHERE 1=1 AND name LIKE '%李%'

            // 获取value值

            String value = condition.get(key)[0];

            // 判断value是否有值

            if (value !=null && "".equals(value)){

                sb.append("  and "+ key +"like ?  ");

                params.add("%" + value+"%");

            }

        }

//        // 添加分页

        sb.append("   limit ?,? ");

        params.add(start);

        params.add(rows);

        sql = sb.toString();

        System.out.println("sqlfindByPage"+sql);

        System.out.println("params"+params);





//        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());

    }
}
