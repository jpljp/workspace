package com.util;

import java.sql.*;

public class DBUtil {
    private static String className="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/ljp";
    private static String userName="root";
    private static String password="123456";

    static {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url,userName,password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn, Statement stm, ResultSet rs){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stm!=null){
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
