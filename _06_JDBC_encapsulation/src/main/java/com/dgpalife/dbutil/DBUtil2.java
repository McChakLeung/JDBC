package com.dgpalife.dbutil;

import java.sql.*;
import java.util.Map;

public class DBUtil2 {

    private static Connection conn;

    private static PreparedStatement ps;

    private static final String url = "jdbc:mysql://localhost:3306/test";

    private static final String username = "root";

    private static final String password = "Pa888888";

    //加载驱动使用静态语句块，只加载一次即可
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取Connection
    public static Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("创建连接通道失败，可能的原因：配置信息错误");
        }
        return conn;
    }

    //获取数据库操作对象
    public static PreparedStatement getPreparedStatement(String sql){
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
