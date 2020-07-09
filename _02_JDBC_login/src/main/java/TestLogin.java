import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestLogin {
    public static void main(String[] args) throws Exception{
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入登录名称");
//        String username = scanner.next();
//        System.out.println("请输入密码");
//        String password = scanner.next();

        String username = "smith' or '1=1";
        String password = "7177 or 1=1";


        String url = "jdbc:mysql://localhost:3306/test";
        String sql = "select count(*) from emp where ename='" + username + "' and empno = " + password;

        //通过jdbc查询数据库数据

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,"root","Pa888888");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int flag = resultSet.getInt("count(*)");
        if(flag>=1){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败，请重新登录");
        }

        if(statement!=null){
            statement.close();
        }

        if(connection!=null){
            connection.close();
        }
    }
}
