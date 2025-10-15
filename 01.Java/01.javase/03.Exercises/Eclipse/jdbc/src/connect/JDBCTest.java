package connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\IDEA\\learn\\src\\jdbc\\news.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user,password);
        System.out.println(connection);

//        String sql = "INSERT INTO news VALUES (1, 'wow'),(2, 'd3'),(3, 'overwatch'),(4, 'war3'),(5, 'sc3');";
//        String sql = "UPDATE news SET content = 'dota2' WHERE id = 1;";
        String sql = "DELETE FROM news WHERE id = 1;";

        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);

        statement.close();
        connection.close();
    }
}
