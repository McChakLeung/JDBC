import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestPreparedStatement {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://localhost:3306/test";
        String sql = "insert into t_department values (?,?,'loc')";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,"root","Pa888888");
        PreparedStatement ps = connection.prepareStatement(sql);
        Long start = System.currentTimeMillis();
        for(int i=1; i<=100;i++ ){
            ps.setInt(1,i);
            ps.setString(2, "department" +i );
            ps.executeUpdate();
        }
        Long end = System.currentTimeMillis();

        System.out.println("PreparedStatement的推送时间：" + (end-start) + "毫秒");

        if(ps!=null){
            ps.close();
        }

        if(connection!=null){
            connection.close();
        }
    }
}
