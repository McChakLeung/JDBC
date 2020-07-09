import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestQuery {

    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";
        String sql = "select * from t_department";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,username,password);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
//            String department_name = resultSet.getString("department_name");
//            String location = resultSet.getString("location");
            String id = resultSet.getString(1);
            String department_name = resultSet.getString(2);
            String location = resultSet.getString(3);
            System.out.println(id);
            System.out.println(department_name);
            System.out.println(location);
        }

        if(statement!=null){
            statement.close();
        }

        if(conn!=null){
            conn.close();
        }
    }

}
