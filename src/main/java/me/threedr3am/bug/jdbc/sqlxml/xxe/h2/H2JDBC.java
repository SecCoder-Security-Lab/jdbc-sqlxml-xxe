package me.threedr3am.bug.jdbc.sqlxml.xxe.h2;

import javax.xml.transform.dom.DOMSource;
import java.sql.*;

/**
 * SCSL-2021-1001
 * https://github.com/h2database/h2database/issues/3195
 *
 * schema.sql & data.sql
 */
public class H2JDBC {

    public static void main(String[] args) throws SQLException {
        Connection connection = H2ConnectorFactory.getConnection();
        if (connection != null) {
            Statement statement = connection.createStatement();
            statement.execute("select * from tb_test");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                SQLXML sqlxml = resultSet.getSQLXML("message");
                sqlxml.getSource(DOMSource.class);
            }
        }
    }
}
