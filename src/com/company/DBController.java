package com.company;

import java.sql.*;
import java.util.LinkedList;

public class DBController {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:cats.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void getAllCats() {
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("select * from cats");
            while (results.next()) {
                Cat cat = new Cat(results.getString("name"), results.getInt("id"));
                Main.tree.add(cat);
            }
            results.first();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
