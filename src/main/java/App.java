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
