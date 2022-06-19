package sr.unasat.jdbc.crud.app;
import sr.unasat.jdbc.crud.scanners.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
/*

    the main method takes in the user input and initialises the correct scanner

*/
    public static void main(String[] args) throws SQLException {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.println("Select number 1-5");
            System.out.println("\nSelect the entity to mutate\n1 Person\n2 Country\n3 Company\n4 Contact information\n5 Person Company\nq Quit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    new PersoonScanner();
                    break;
                case "2":
                    new LandScanner();
                    break;
                case "3":
                    new BedrijfScanner();
                    break;
                case "4":
                    new ContactInformatieScanner();
                    break;
                case "5":
                    new PersoonBedrijfPositieScanner();
                    break;
                case "q":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }

        }

    }

}
