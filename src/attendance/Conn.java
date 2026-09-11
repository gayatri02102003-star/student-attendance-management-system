package attendance;
import java.sql.*;
public class Conn {
    public Connection c;
    public Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///attendance","root","1234");
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(Exception e){ System.out.println(e); }
    }
}