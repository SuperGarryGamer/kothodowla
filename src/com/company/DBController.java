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
            while (results.next()) {
                Cat cat = Main.tree.getById(results.getInt("id"));
                if (results.getInt("mother_id") != -1)  {
                    cat.setMommy(Main.tree.getById(results.getInt("mother_id")));
                }
                if (results.getInt("father_id") != -1) {
                    cat.setDaddy(Main.tree.getById(results.getInt("father_id")));
                }

                for (int childId: intsFromCSV(results.getString("children_ids_csv"))) {
                    cat.addChild(Main.tree.getById(childId));
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static LinkedList<Integer> intsFromCSV(String csv) {
        LinkedList<Integer> ints = new LinkedList<>();
        int currInt = 0;
        for (char c: csv.toCharArray()) {
            if (Character.isDigit(c)) {
                currInt *= 10;
                currInt += Character.getNumericValue(c);
            }
            else {
                ints.add(currInt);
                currInt = 0;
            }
        }
        return ints;

    }
}
