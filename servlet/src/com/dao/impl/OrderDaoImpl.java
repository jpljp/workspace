package com.dao.impl;


import com.beans.Order;
import com.dao.OrderDao;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public int addOrder(String msg, Integer id) {
        Connection conn= DBUtil.getConn();
        PreparedStatement stm=null;
        try {
            stm=conn.prepareStatement("insert into `order`(msg,fuid,createTime) values(?,?,?)");
            stm.setString(1, msg);
            stm.setInt(2, id);
            stm.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
    public List<Order> searchAllOrder(Integer id) {
        Connection conn= DBUtil.getConn();
        PreparedStatement stm=null;
        ResultSet rs=null;
        List<Order> orderList = new ArrayList<>();
        try {
            stm=conn.prepareStatement("select * from `order` where status=0 and fuid!=? ");
            stm.setInt(1, id);
            rs=stm.executeQuery();
            while(rs.next()){
                Order order=new Order();
                order.setId(rs.getInt("id"));
                order.setFuid(rs.getInt("fuid"));
                order.setMsg(rs.getString("msg"));
                order.setCreateTime(rs.getString("createTime"));
                order.setStatus(rs.getInt("status"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }
        return orderList;
    }

    @Override
    public int updateOrder(Integer id) {
        Connection conn=DBUtil.getConn();
        PreparedStatement stm=null;
        try {
            stm=conn.prepareStatement("update `order` set status=1 ,qTime=? where id=? and status=0");
            stm.setString(1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            stm.setInt(2, id);
            int result=stm.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, stm, null);
        }
        return 0;
    }

    public static void main(String[] args) {
        OrderDao dao=new OrderDaoImpl();


            System.out.println(dao.updateOrder(5));


    }
}
