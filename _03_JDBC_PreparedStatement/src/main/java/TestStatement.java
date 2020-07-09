import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestStatement {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Pa888888");
        Statement st = con.createStatement();
        long start =  System.currentTimeMillis();
        //推送1000条插入语句
        for(int i=1;i<=100;i++){
            String sql = "insert into t_department value ("+i+",'dept','location')";
            st.executeUpdate(sql);
        }
        long end =  System.currentTimeMillis();
        System.out.println("Statment 推送100条sql语句消耗的时间"+(end-start)+"毫秒");//4342毫秒
    }
}
