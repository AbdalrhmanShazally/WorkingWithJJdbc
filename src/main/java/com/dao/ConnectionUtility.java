package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    static String url = "jdbc:mysql://localhost:3306/crud";
    static String username = "root";
    static String password = "";

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url,username,password);
    }

}
