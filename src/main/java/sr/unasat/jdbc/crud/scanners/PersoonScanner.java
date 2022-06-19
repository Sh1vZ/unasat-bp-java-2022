package sr.unasat.jdbc.crud.scanners;

import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.repositories.PersoonRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersoonScanner extends BaseScanner {

    private PersoonRepository persrepo;
    private Scanner scanner;

    /*
        initialises the needed reposotories
        and executes the runoptions methods to prompt the user
        executeOption to execute the option that the user has chosen
    */

    public PersoonScanner() throws SQLException {
       this.scanner = new Scanner(System.in);
        this.persrepo = new PersoonRepository();
        entity="Person";
        runOptions();
        executeOption();
    }

    /*
       switches between the option and executes the correct method for the given option
    */

    public void executeOption() throws SQLException {
        switch (option){
            case "1":
                insertPersoon();
                break;
            case "2":
                updatePersoon();
                break;
            case "3":
                deletePersoon();
                break;
            case "4":
                findOne();
                break;
            case "5":
                findAll();
                break;
            case "q":
                stopOptions();
                break;
        }
        if(!option.equals("q")){
            runOptions();
            executeOption();
        }
    }

    /*
        all these methods prompts the user to type in info
    */


    private void insertPersoon() throws SQLException {
        System.out.println("Type in the name of the person");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Persoon naame=persrepo.findOne(naam);
        if(naame!=null) {
            System.out.println("\nPerson already exists");
            return;
        }

        Persoon pers=new Persoon(naam);
        persrepo.insertOneRecord(pers);
    }

    private void updatePersoon() throws SQLException {
        System.out.println("Type in the name of the person to be updated");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Persoon pers=persrepo.findOne(naam);
        if(pers==null) {
            System.out.println("\nPerson not found");
            return;
        }
        System.out.println("Type in new name");
        String newNaam=scanner.nextLine();
        if(isEmpty(newNaam)) return;
        Persoon newpers=persrepo.findOne(newNaam);
        if(newpers!=null) {
            System.out.println("\nPerson already exists");
            return;
        }
        pers.setNaam(newNaam);
        persrepo.updateOneRecord(pers);
    }
    private void deletePersoon() throws SQLException {
        System.out.println("Type in the name of the person to be deleted");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Persoon pers=persrepo.findOne(naam);
        if(pers==null) {
            System.out.println("\nPerson not found");
            return;
        }
        persrepo.deleteOneRecord(pers);
    }

    private void findAll() throws SQLException {
        List<Persoon> allpers=persrepo.findAll();
        System.out.println(allpers);
    }

    private void findOne() throws SQLException {
        System.out.println("Type in the name of the person to be searched");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Persoon pers=persrepo.findOne(naam);
        if(pers==null) {
            System.out.println("\nPerson not found");
            return;
        }
        System.out.println(pers);
    }

}
