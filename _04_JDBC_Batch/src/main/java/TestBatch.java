import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestBatch {
    public static void main(String[] args) throws Exception {
        //定义相关变量
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "Pa888888";
        String sql = "insert into t_department value (null,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,username,password);
        PreparedStatement ps = conn.prepareStatement(sql);

        long start = System.currentTimeMillis();
        //开始循环
        for(int i= 0; i<100;i++){
            //ps.setInt(1);
            ps.setString(1,"dname"+i);
            ps.setString(2,"loc");
            ps.addBatch();
        }
        ps.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("消耗时间"+(end-start));

        //关闭ps
        if(ps!=null){
            ps.close();
        }

        //关闭conn
        if(conn!=null){
            conn.close();
        }
    }
}
