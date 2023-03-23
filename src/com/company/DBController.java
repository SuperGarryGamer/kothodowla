package com.company;

import java.io.IOException;
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
                cat.setFemale(results.getBoolean("isFemale"));
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

    public static void addCat(Cat cat) {
        Connection conn = connect();
        try {
            Statement statement = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("insert into cats values (?, ?, ?, ?, '', ?);");
            pst.setInt(1, cat.getId());
            pst.setString(2, cat.getName());
            pst.setInt(3, cat.getMother() == null ? -1 : cat.getMother().getId());
            System.out.println(cat.getFather());
            pst.setInt(4, cat.getFather() == null ? -1 : cat.getFather().getId());
            pst.setBoolean(5, cat.isFemale());

            if (cat.getFather() != null) {
                PreparedStatement fatherAddChildren = conn.prepareStatement("update cats set children_ids_csv = ? where id = ?");
                cat.getFather().getChildren().add(cat);
                fatherAddChildren.setInt(2, cat.getFather().getId());
                String fatherChildren = "";
                for (Cat c : cat.getFather().getChildren()) {
                    fatherChildren += String.valueOf(c.getId()) + ",";
                }
                fatherAddChildren.setString(1, fatherChildren);
                fatherAddChildren.execute();
            }

            if (cat.getMother() != null) {
                PreparedStatement motherAddChildren = conn.prepareStatement("update cats set children_ids_csv = ? where id = ?");
                System.out.println(cat.getMother().getChildren().toString());
                cat.getMother().getChildren().add(cat);
                System.out.println(cat.getMother().getChildren().toString());
                motherAddChildren.setInt(2, cat.getMother().getId());
                String motherChildren = "";
                for (Cat c : cat.getMother().getChildren()) {
                    motherChildren += String.valueOf(c.getId()) + ",";
                }
                motherAddChildren.setString(1, motherChildren);
                motherAddChildren.execute();
            }

            pst.execute();
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
        System.out.println(ints);
        return ints;

    }
}
