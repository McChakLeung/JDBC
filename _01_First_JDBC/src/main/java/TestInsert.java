import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";
        String sql = "insert into t_department (department_name,location) value ('行政部','广发金融大厦')";

        //1.获取myslq jdbc驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取connection
        Connection connection = DriverManager.getConnection(url,username,password);

        //3.创建Statement【数据库操作对象】
        Statement statement = connection.createStatement();

        //4.通过statement进行insert操作
        int flag = statement.executeUpdate(sql);

        //5.判断flag的值，如果大于等于1，则说明更新成功，否则更新失败
        if(flag>=1){
            System.out.println("数据插入成功");
        }else {
            System.out.println("数据插入失败");
        }

        //6.销毁Statement
        if(statement!=null){
            statement.close();
        }

        //7.销毁connection
        if(connection!=null){
            connection.close();
        }
    }
}
