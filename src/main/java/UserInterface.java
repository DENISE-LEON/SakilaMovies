import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    DataSource dataSource;

    ActorManager actorManager;
    FilmManager filmManager;

    public UserInterface(DataSource dataSource) {
        this.dataSource = dataSource;
        this.actorManager = new ActorManager(dataSource);
        this.filmManager = new FilmManager(dataSource);
    }

    public void menuOptions() {
        boolean run = true;
        try {
            while (run) {
                System.out.println("What would you like to do");
                System.out.println("""
                        1) View all actors
                        2) Full name actor search
                        3) Actor ID search
                        4) View all films
                        5) Find film using Film ID
                        6)Find film using actor ID
                        0) Exit
                        """);
                int menuChoice = scanner.nextInt();
                scanner.nextLine();

                switch (menuChoice) {
                    case 1:
                        getAllActorsProcess();
                        break;
                    case 2:
                        fullNameSearchProcess();
                        break;
                    case 3:
                        actorIDSearchProcess();
                        break;
                    case 4:
                        allFilmsProcess();
                        break;
                    case 5:
                        filmIDSearchProcess();
                        break;
                    case 6:
                        filmActorIDSearch();
                        break;
                    case 0:
                        run = false;
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Please input the number that matches what you would like to do");
            System.out.println("Example:");
            System.out.println("I want to: 1) View all actors");
            System.out.println("My input should be: 1");
            //need to clear buffer bc bad input is still in the buffer
            scanner.nextLine();
        }
    }

    //figure out perams later
    public void getAllActorsProcess() {
        printActors(actorManager.getAllActors());
    }

    public void fullNameSearchProcess() {
        System.out.println("Enter the first name:");
        String firstName = scanner.nextLine().toUpperCase().trim();

        System.out.println("Enter the last name:");
        String lastName = scanner.nextLine().toUpperCase().trim();

        List<Actor> actors = actorManager.getByFullName(firstName, lastName);
        printActors(actors);


    }

    public void actorIDSearchProcess() {
        try {
            System.out.println("Please enter the actor ID of the actor you'd like to find");
            int actorID = scanner.nextInt();
            scanner.nextLine();
            List<Actor> actors = actorManager.getActorByID(actorID);
            printActors(actors);
        } catch (Exception e) {
            System.out.println("Please input a number");
            System.out.println("Example:");
            System.out.println("I want to: find actor with actor id 3");
            System.out.println("Your input: 3");
            scanner.nextLine();
        }
    }

    public void allFilmsProcess() {

    }

    public void filmIDSearchProcess() {

    }

    public void filmActorIDSearch() {

    }

    public static void printActors(List<Actor> actors) {
        if (actors.isEmpty()) {
            System.out.println("No actors found.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s%n", "ID", "First Name", "Last Name");
        System.out.println("----------------------------------------");

        for (Actor actor : actors) {
            System.out.printf("%-5d %-15s %-15s%n",
                    actor.getActorID(),
                    actor.getFirstName(),
                    actor.getLastName());
        }
    }


}