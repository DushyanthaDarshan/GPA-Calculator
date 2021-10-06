package com.TeamPhoenix.gpaCalculator.service.dao;

import java.sql.*;

public class CommonDb {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/PHOENIX_GPA_CALCULATOR";

    // Database credentials
    static final String USER = "root";
    static final String PASSWORD = "P@ssw0rd";

    public ResultSet getDataFromDb(String query) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //Execute a query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }

        return rs;
    }

    public void saveDataToDb(String query) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }
}
