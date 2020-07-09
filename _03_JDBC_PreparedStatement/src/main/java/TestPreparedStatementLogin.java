import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestPreparedStatementLogin {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";
        String ename = "smith' or '1=1";
        String empno = "7369";
        String sql = "select count(*) from emp " +
                "where ename = ? " +
                "and empno = ?";

        //获取driver
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,username,password);
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,ename);
        ps.setString(2,empno);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer flag = rs.getInt("count(*)");
        if(flag>=1){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        if(ps!=null){
            ps.close();
        }

        if(connection!=null){
            connection.close();
        }

    }
}
