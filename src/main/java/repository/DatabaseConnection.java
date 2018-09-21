package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/todos";
        String user = "todouser";
        String password = "todopass";
        return DriverManager.getConnection(url, user, password);
    }
}
