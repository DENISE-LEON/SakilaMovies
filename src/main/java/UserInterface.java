//import org.apache.commons.dbcp2.BasicDataSource;
//
//public class UserInterface {
//
//    public static void menuOptions(BasicDataSource basicDataSource) {
//        boolean run = true;
//
//        while (run) {
//            System.out.println("What would you like to do");
//            System.out.println("""
//                    1) Last name actor search
//                    2) Full name actor search
//                    0) Exit
//                    """);
//            int menuChoice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (menuChoice) {
//                case 1:
//                    lastNameSearch(basicDataSource);
//                    break;
//                case 2:
//                    fullNameSearch(basicDataSource);
//                    break;
//                case 0:
//                    run = false;
//                    break;
//            }
//        }
//    }
//}
