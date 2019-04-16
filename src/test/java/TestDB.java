import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        Connection  connection= SqlHelper.getConnection();
        System.out.println(connection);
        ResultSet resultSet = SqlHelper.executeQueryAll("select * from admins");
       while(resultSet.next()){
           System.out.println(resultSet.getString(1)+resultSet.getString(2)
                   +resultSet.getString(3));
       }
        System.out.println("Hello12");
    }
}
