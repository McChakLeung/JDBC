import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestTransactionWithNoneCommit {
    public static void main(String[] args) throws Exception {
        String sql_1 = "update t_account set balance = balance-3000 where account_name = '张三'";
        String sql_2 = "update t_account set balance = balance+3000 where account_name = '李四'";

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,username,password);

        conn.setAutoCommit(false);

        PreparedStatement ps = conn.prepareStatement(sql_1);
        ps.addBatch();
        ps.addBatch(sql_2);

        try {
            ps.executeBatch();
            conn.commit();
        }catch (SQLException e){
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
