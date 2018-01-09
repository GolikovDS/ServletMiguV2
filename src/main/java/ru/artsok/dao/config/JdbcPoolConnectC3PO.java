package ru.artsok.dao.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcPoolConnectC3PO {

    private ComboPooledDataSource cpds;
    private static ru.artsok.dao.config.JdbcPoolConnectC3PO jdbcPoolConnectC3PO;


    private JdbcPoolConnectC3PO() throws IOException, SQLException, PropertyVetoException {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/migu_servlet");
        cpds.setUser("root");
        cpds.setPassword("11");

        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(50);
        cpds.setMaxStatements(180);
    }

    public static ru.artsok.dao.config.JdbcPoolConnectC3PO getInstance() throws IOException, SQLException, PropertyVetoException {
        if (jdbcPoolConnectC3PO == null) {
            jdbcPoolConnectC3PO = new ru.artsok.dao.config.JdbcPoolConnectC3PO();

        }
        return jdbcPoolConnectC3PO;
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }


}
