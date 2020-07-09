package com.dgpalife.test;

import com.dgpalife.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestMain2 {

    public static void main(String[] args) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //复杂任务：张三向李四转账3000元
        //1、确认张三的账户是否存在
        String sql_1 = "select count(*) from t_account where id = 1 for update";

        try {
            conn = DBUtil.getConnection();
            ps = DBUtil.getPreparedStatement(sql_1);
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            rs.next();
            int flag = rs.getInt("count(*)");
            if (flag < 0) {
                System.out.println("查询的账户不存在");
            }else {
                System.out.println("查询的账户存在");
            }
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            DBUtil.close(rs);
        }

        //2、确认李四的账户是否存在
        String sql_2 = "select count(*) from t_account where id = 2 for update";

        try {
            conn = DBUtil.getConnection();
            ps = DBUtil.getPreparedStatement(sql_2);
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            rs.next();
            int flag = rs.getInt("count(*)");
            if (flag < 0) {
                System.out.println("查询的账户不存在");
            }else {
                System.out.println("查询的账户存在");
            }
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            DBUtil.close(rs);
        }

        //3、确认张三的账户余额是否>=3000
        String sql_3 = "select balance from t_account    where id = 1";
        try{
            conn = DBUtil.getConnection();
            ps = DBUtil.getPreparedStatement(sql_3);
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            rs.next();
            int balance = rs.getInt("balance");
            if(balance<=3000){
                System.out.println("该用户余额不足，无法转账");
                return;
            }else {
                System.out.println("该用户余额足够，可以转账");
            }
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            DBUtil.close(rs);
        }

        String sql_4 = "update t_account set balance = balance - 3000 where id = ?";
        String sql_5 = "update t_account set balance = balance + 3000 where id = ?";

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            //4、将张三的账户余额减少3000
            ps = DBUtil.getPreparedStatement(sql_4);
            ps.setInt(1,1);
            ps.executeUpdate();

            //5、将李四的账户余额增加3000
            ps = DBUtil.getPreparedStatement(sql_5);
            ps.setInt(1,2);
            ps.executeUpdate();

            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            DBUtil.close();
        }


    }
}
