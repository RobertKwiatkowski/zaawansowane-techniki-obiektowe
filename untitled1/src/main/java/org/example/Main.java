package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.connect();

        while (true) {
            System.out.println("Select:\n 1 to create new person\n 2 to view list of persons\n 3 to delete person \n 4 to update person \n 5 to exit");
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            switch (id) {
                case 1:
                    dbc.savePerson();
                    break;
                case 2:
                    System.out.println("List of users:");
                    dbc.getPersons();
                    break;
                case 3:
                    dbc.deletePerson();
                    break;
                case 4:
                    dbc.updatePerson();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
}