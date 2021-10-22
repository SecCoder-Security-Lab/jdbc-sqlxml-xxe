package me.threedr3am.bug.jdbc.sqlxml.xxe.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2ConnectorFactory {

    private static final String url = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:schema.sql'\\;RUNSCRIPT FROM 'classpath:data.sql'";

    static {
        try {
            Class.forName("org.h2.Driver");
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
