package com.dgpalife.test;

import com.dgpalife.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMain {

    private static Connection conn = null;

    private static PreparedStatement ps = null;

    private static ResultSet rs = null;

    public static void main(String[] args) throws Exception {
        //复杂业务：张三向李四转账3000元

        conn = DBUtil.getConnection();

        String sql_1 = "select count(*) from t_account where id = 1 for update";
        String account_1 = "张三";
        String sql_2 = "select count(*) from t_account where id = 2 for update";
        String account_2 = "李四";
        String sql_3 = "select balance from t_account where id = 1 for update";
        String sql_4 = "update t_account set balance = balance - 3000 where id = ?";
        String sql_5 = "update t_account set balance = balance + 3000 where id = ?";


        //1、确定张三的账户是否存在
        TestMain.checkAccount(sql_1,account_1);

        //2、确定李四的账户是否存在
        TestMain.checkAccount(sql_2,account_2);


        //3、确定张三的账户余额是否有是大于3000元
        try{
            conn.setAutoCommit(false);
            ps = DBUtil.getPreparedStatement(sql_3);
            rs = ps.executeQuery();
            rs.next();
            int balance = rs.getInt("balance");
            if(balance<3000){
                System.out.println("张三的账户余额小于转账金额，无法转账");
                return;
            }else {
                System.out.println("张三的账户余额大于转账金额，可以转账");
            }
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }


        try {
            conn.setAutoCommit(false);
            //4、向张三的账户余额减少3000元
            ps = DBUtil.getPreparedStatement(sql_4);
            ps.setInt(1,1);
            ps.executeUpdate();

            //5、向李四的账户余额增加3000元
            ps = DBUtil.getPreparedStatement(sql_5);
            ps.setInt(1,2);
            ps.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            conn.rollback();
        }

        DBUtil.close(rs);


    }

    public static void checkAccount(String sql, String account_name) throws Exception{

        try {
            conn.setAutoCommit(false);
            ps = DBUtil.getPreparedStatement(sql);

            rs = ps.executeQuery();

            rs.next();

            int flag = rs.getInt("count(*)");

            if(flag<0){
                System.out.println(account_name+"的账户不存在，请确认");
            }else{
                System.out.println(account_name+"的账户存在");
            }

            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }
    }
}
