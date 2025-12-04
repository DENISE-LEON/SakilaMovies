import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        //password username check
        if (args.length != 2) {
            System.out.println("Application needs two args to run: A username and a password for the db");
            //exit the app due to failure because we dont have a username and password from the command line
            System.exit(1);
        }
        System.out.println("password and username found");

        //username and password from args
        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/sakila";


        try (
                //new data source object
                BasicDataSource dataSource = new BasicDataSource()
        ) {
            //setting the url, password, username
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            //display the menu
            //ui works bc the data source(which has all necessary info) is passed into the ui
            UserInterface ui = new UserInterface(dataSource);
            ui.menuOptions();
        } catch (SQLException e) {
            System.out.println("Error woopsie" + e.getMessage());
        }

    }
}



//    public static void lastNameSearch(BasicDataSource basicDataSource) {
//        try (
//                Connection connection = basicDataSource.getConnection();
//
//                PreparedStatement preparedStatement = connection.prepareStatement("""
//                        SELECT first_name,
//                        last_name
//                        FROM actor
//                        WHERE last_name = ?;
//                        """)) {
//            System.out.println("Enter the last name of your favorite actor:");
//            String lastName = scanner.nextLine().toUpperCase().trim();
//            preparedStatement.setString(1, lastName);
//
//            try (ResultSet set = preparedStatement.executeQuery()
//            ) {
//                printResults(set);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void fullNameSearch(BasicDataSource basicDataSource) {
//        try (
//                Connection connection = basicDataSource.getConnection();
//
//                PreparedStatement preparedStatement = connection.prepareStatement("""
//                        SELECT first_name,
//                        last_name
//                        FROM actor
//                        WHERE first_name = ? AND last_name = ?;
//                        """)) {
//            System.out.println("Enter the first name:");
//            String firstName = scanner.nextLine().toUpperCase().trim();
//
//            System.out.println("Enter the last name:");
//            String lastName = scanner.nextLine().toUpperCase().trim();
//
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//
//            try (ResultSet set = preparedStatement.executeQuery()
//            ) {
//                printResults(set);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error" + " " + e.getMessage());
//        }
//    }

//
//    public static void printResults(ResultSet resultSet) {
//        try {
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            //bool for handling no matches
//            boolean any = false;
//
//            //result set starts before first row, therefore need next
//            while (resultSet.next()) {
//                any = true;
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//
//                    String value = resultSet.getString(i);
//                    System.out.printf("%-15s: %-20s%n", columnName, value);
//                }
//
//                //print an empty line to make the results prettier
//                System.out.println("--------------------------------");
//            }
//            if (!any) {
//                System.out.println("No matches found");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error" + " " + e.getMessage());
//        }
//    }
//}