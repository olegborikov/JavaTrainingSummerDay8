package com.borikov.day8.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String DATASOURCE_NAME = "java:comp/env/jdbc/day8";
    private static ConnectionPool INSTANCE = null;

    public static ConnectionPool getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ConnectionPool();
        return INSTANCE;
    }


    private ConnectionPool() {
    }

    public Connection getConnection() throws SQLException {
        DataSource dataSource = null;
        Connection connection;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            System.out.println("Naming error");
        }
        connection= dataSource.getConnection();
        return connection;
    }
}
