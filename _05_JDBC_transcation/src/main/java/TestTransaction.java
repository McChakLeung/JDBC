import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) throws Exception {
        //业务:张三给李四转账3000元
        //#张三账户的余额减少3000元
        String sql_1 = "Update t_account  set  balance= balance-3000 where account_Name='张三'";
        //#李四账户的余额增加3000元. [错误的sql]
        String sql_2 = "Update t_account  set  balance= balance+3000 where account_Name='李四'";

        //定义相关变量
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";

        //与mysql数据库建立连接
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,username,password);
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement(sql_1);
        ps.addBatch(sql_1);
        ps.addBatch(sql_2);

        try {
            ps.executeBatch();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }finally {
            if(ps!=null){
                ps.close();
            }

            if(conn!=null){
                conn.close();
            }
        }


    }
}
