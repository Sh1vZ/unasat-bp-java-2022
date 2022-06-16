package sr.unasat.jdbc.crud.scanners;

import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.repositories.LandRepository;
import sr.unasat.jdbc.crud.repositories.PersoonRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LandScanner extends BaseScanner {
    private LandRepository landrepo;
    Scanner scanner;
    public LandScanner() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.landrepo = new LandRepository();
        entity="Country";
        runOptions();
        executeOption();
    }



    public void executeOption() throws SQLException {
        switch (option){
            case "1":
                insertLand();
                break;
            case "2":
                updateLand();
                break;
            case "3":
                deleteLand();
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

    private void insertLand() throws SQLException {
        System.out.println("Type in country name");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Land land=new Land(naam);
        landrepo.insertOneRecord(land);
    }

    private void updateLand() throws SQLException {
        System.out.println("Type in the name of country you want to update");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Land land=landrepo.findOne(naam);
        if(land==null) {
            System.out.println("\nCountry not found");
            return;
        }
        System.out.println("Type in new country name");
        String newNaam=scanner.nextLine();
        Land newLand=landrepo.findOne(newNaam);
        if(newLand!=null) {
            System.out.println("\nCountry already exists");
            return;
        }
        if(isEmpty(newNaam)) return;
        land.setNaam(newNaam);
        landrepo.updateOne(land);
    }
    private void deleteLand() throws SQLException {
        System.out.println("Type in the name of country you want to delete");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Land land=landrepo.findOne(naam);
        if(land==null) {
            System.out.println("\nCountry not found");
            return;
        }
        landrepo.deleteOneRecord(land);
    }

    private void findAll() throws SQLException {
        List<Land> allLand=landrepo.findAllRecords();
        System.out.println(allLand);
    }

    private void findOne() throws SQLException {
        System.out.println("Type in the name of country you want to search");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Land land=landrepo.findOne(naam);
        if(land==null) {
            System.out.println("\nNo country found");
            return;
        }
        System.out.println(land);
    }

}
