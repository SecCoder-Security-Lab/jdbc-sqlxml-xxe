package me.threedr3am.bug.jdbc.sqlxml.xxe.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectorJFactory {

    private static final String url = "jdbc:mysql://localhost:3306/test?user=root&password=123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Error();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
