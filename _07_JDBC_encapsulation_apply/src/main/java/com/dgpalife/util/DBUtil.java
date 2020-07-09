package com.dgpalife.util;

import java.sql.*;

public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/test";

    private static final String username = "root";

    private static final String password = "Pa888888";

    private static Connection conn;

    private static PreparedStatement ps;

    //加载驱动类
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取connection
    public static Connection getConnection() throws Exception{
        conn = DriverManager.getConnection(url,username,password);
        return conn;
    }

    //创建数据库操作对象
    public static PreparedStatement getPreparedStatement(String sql) throws Exception{
        conn = getConnection();
        ps = conn.prepareStatement(sql);
        return ps;
    }

    public static void close() throws Exception{
        if(ps!=null){
            ps.close();
        }

        if(conn!=null){
            conn.close();
        }
    }

    public static void close(ResultSet rs) throws Exception{
        if(rs!=null){
            rs.close();
        }

        close();
    }

}
