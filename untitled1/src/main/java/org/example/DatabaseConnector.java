package org.example;

import java.sql.*;
import java.util.Scanner;


public class DatabaseConnector {
    public static final String HOST = "jdbc:postgresql://kandula.db.elephantsql.com/";
    public static final String DATABASE = "ocnvwjna";
    public static final String USERNAME = "ocnvwjna";
    public static final String PASS = "YBFGxfPtSuEQBObNeJMheq04lUsNUudv";
    public Connection connection;


    public  void connect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection(HOST+DATABASE, USERNAME, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void deletePerson() throws SQLException {
        System.out.println("enter userID to delete");
        Scanner in = new Scanner(System.in);
        long id = in.nextLong();
        String sql = "DELETE FROM person " + "WHERE id = " + id;
        Statement stm = this.connection.createStatement();
        stm.executeUpdate(sql);
    }

    public void updatePerson() throws SQLException {
        Scanner in = new Scanner(System.in);
        // TODO ID I ENABLED TEZ EDYTUJ
        System.out.println("enter userID to edit");
        long id = in.nextLong();
        System.out.println("enter new username");
        String username = in.next();
        System.out.println("enter new email");
        String email = in.next();
        System.out.println("enter new password");
        String password = in.next();
        System.out.println("enter enabled value (true/false)");
        Boolean enabled = in.nextBoolean();
        System.out.println("enter id value ");
        Long newId = in.nextLong();

        Statement stm = this.connection.createStatement();
        String sql = "UPDATE person " + "SET username = " + username + ", email = " + email  + ", password = " + password +
                ", enabled = " + enabled + ", id = " + newId + "WHERE id = " + id;
        stm.executeUpdate(sql);
    }

    public void getPersons() {
        String selectQuery = "select * from person";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(selectQuery);
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePerson() {
        Scanner in = new Scanner(System.in);
        System.out.println("enter new username");
        String username = in.next();
        System.out.println("enter new email");
        String email = in.next();
        System.out.println("enter new password");
        String password = in.next();
        System.out.println("enter enabled value (true/false)");
        Boolean enabled = in.nextBoolean();
        System.out.println("enter id value ");
        Long id = in.nextLong();
        String insertSQL = "insert into person values (" +
                "'" + username + "'," +
                "'" + email + "'," +
                "'" + password + "'," +
                "'" + enabled + "',"
                + id + ")";
        try {
            Statement stm = this.connection.createStatement();
            stm.execute(insertSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
