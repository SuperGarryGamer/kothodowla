package com.company;

import java.sql.*;
import java.util.LinkedList;

public class DBController {
    public static Connection connect() {
            String url = "jdbc:sqlite:src/com/company/cats.db";
            try {
                Connection conn = DriverManager.getConnection(url);
                return conn;
            } catch (SQLException e) {
                return null;
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
            results = statement.executeQuery("select * from cats");
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
        if (csv == null) return ints;
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
