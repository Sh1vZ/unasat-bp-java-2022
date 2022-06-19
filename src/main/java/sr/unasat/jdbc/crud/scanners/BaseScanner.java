package sr.unasat.jdbc.crud.scanners;

import java.util.Arrays;
import java.util.Scanner;


/*

basescanner get inherited by all the child scanners to be able to access
these functions and variables

*/
public class BaseScanner {
    protected boolean isRunning = false;
    protected String option;
    protected String entity;
    private final String[] validOptions = {"1", "2", "3", "4", "5", "q"};

    /*
    Prompt user these options
    */
    protected void runOptions() {
        this.isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.println("\nSelected: " + entity);
            System.out.println("1 Insert\n2 Update\n3 Delete\n4 Find one\n5 Find all\nq Go back");
            this.option = scanner.nextLine();
            if (!Arrays.asList(validOptions).contains(option)) {
                System.out.println("\nPlease enter a valid option");
                continue;
            }
            isRunning = false;
        }
    }

    protected void stopOptions() {
        this.isRunning = false;
    }

    /*
        shared function to check if value is empty
    */
    protected boolean isEmpty(String value) {
        boolean isEmpty = false;
        if (value.equals("")) {
            isEmpty = true;
            System.out.println("Empty values not allowed!");
        }
        return isEmpty;
    }
}
