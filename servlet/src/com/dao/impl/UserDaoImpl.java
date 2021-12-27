package com.dao.impl;

import com.beans.PageInfo;
import com.beans.User;
import com.dao.UserDao;
import com.util.DBUtil;
import sun.security.pkcs11.Secmod;

import javax.xml.parsers.DocumentBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String userName, String password) {
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            conn= DBUtil.getConn();
            stm=conn.prepareStatement("select * from user where userName=? ");
            stm.setString(1, userName);
            rs=stm.executeQuery();

            if (rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                return  user;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, rs);
        }
        return null;
    }

    @Override
    public ArrayList<User> searchAllUser(PageInfo page) {
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        ArrayList<User> list=new ArrayList<>();
        try {
            conn=DBUtil.getConn();
            stm=conn.prepareStatement("select * from user limit ?,?");
            stm.setInt(1, page.getBeginRow());
            stm.setInt(2, page.getPageSize());
            rs=stm.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, rs);
        }

        return list;
    }

    @Override
    public int delById(int id) {
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtil.getConn();
            stm=conn.prepareStatement("delete from user where id = ?");
            stm.setInt(1, id);
            int result=stm.executeUpdate();
            return  result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }
        return 0;
    }

    @Override
    public User searchUserById(int id) {
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        conn=DBUtil.getConn();
        User user=null;
        try {
            stm=conn.prepareStatement("select * from user where id=?");
            stm.setInt(1, id);
            rs=stm.executeQuery();
            if (rs.next()){
                user=new User();
                user.setId(id);
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, rs);
        }

        return user;
    }

    @Override
    public int updateUser(User user) {
        Connection conn=DBUtil.getConn();
        PreparedStatement stm=null;
        try {
            stm=conn.prepareStatement("update  user set username=? , password=? where id=?" );
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getId());
            int result = stm.executeUpdate();
            if (result>0){
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }

        return 0;
    }

    @Override
    public int delByIds(String[] duo) {
        Connection conn=DBUtil.getConn();
        PreparedStatement stm=null;


        try {
            String sql="delete from user where id in(";
            for (int i=0;i<duo.length;i++){
                if (i!=duo.length-1){
                    sql+="?,";
                }else{
                    sql+="?";
                }
            }
            sql+=")";
            stm=conn.prepareStatement(sql);

            for (int i=0;i<duo.length;i++){
                stm.setString(i+1, duo[i]);
            }
            int result=stm.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }
        return 0;
    }

    @Override
    public void setStatus(Integer id ,Integer status){
        Connection conn=DBUtil.getConn();
        PreparedStatement stm=null;
        try {
            stm=conn.prepareStatement("update user set status=? where id=?");
            stm.setInt(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }

    }

    @Override
    public int addUser(String userName, String password) {
        Connection conn=DBUtil.getConn();
        PreparedStatement stm=null;
        int result=0;
        try {
            stm=conn.prepareStatement("insert into user(userName,password) values(?,?)");
            stm.setString(1, userName);
            stm.setString(2, password);
             result=stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }
        return result;
    }

    @Override
    public int getCount() {
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        conn=DBUtil.getConn();
        int count=0;
        try {
            stm=conn.prepareStatement("select count(*) from user");
            rs=stm.executeQuery();
            rs.next();

            count=rs.getInt(1);

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, rs);
        }
        return 0;
    }


    public static void main(String[] args) {
        UserDao dao=new UserDaoImpl();
        System.out.println(dao.getCount());

//        for (int i = 0; i <100; i++) {
//            dao.addUser((int)(Math.random()*50000)+"",(int)(Math.random()*50000)+"");
//        }
    }
}
