package com.dgpalife.test;

import com.dgpalife.dbutil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException {
        String sql = "select * from dept";

        //Connection conn = DBUtil.createConnection();

        PreparedStatement ps = DBUtil.createPreparedStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String deptno = rs.getString(1);
            String dname = rs.getString(2);
            String loc = rs.getString(3);
            System.out.println(deptno + ',' + dname + ',' + loc);
        }

        DBUtil.close(rs);
    }
}
