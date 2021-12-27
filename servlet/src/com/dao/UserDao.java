package com.dao;

import com.beans.PageInfo;
import com.beans.User;

import java.util.ArrayList;

public interface UserDao {
    public User login(String userName,String password);

    ArrayList<User> searchAllUser(PageInfo page);

    int delById(int id);

    User searchUserById(int id);

    int updateUser(User user);

    int delByIds(String [] duo);


    void setStatus(Integer id ,Integer status);

    int addUser(String userName, String password);

    int getCount();
}
