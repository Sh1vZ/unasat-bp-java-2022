package sr.unasat.jdbc.crud.scanners;

import sr.unasat.jdbc.crud.entities.Bedrijf;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.repositories.BedrijfRepository;
import sr.unasat.jdbc.crud.repositories.LandRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BedrijfScanner extends BaseScanner {
    private Scanner scanner;
    private BedrijfRepository bedrijfrepo;
    private LandRepository landRepository;

    /*
        initialises the needed reposotories
        and executes the runoptions methods to prompt the user
        executeOption to execute the option that the user has chosen
    */
    public BedrijfScanner() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.bedrijfrepo = new BedrijfRepository();
        this.landRepository = new LandRepository();
        entity="Company";
        runOptions();
        executeOption();
    }

    /*
       switches between the option and executes the correct method for the given option
    */


    private void executeOption() throws SQLException {
        switch (option){
            case "1":
                insertBedrijf();
                break;
            case "2":
                updateBedrijf();
                break;
            case "3":
                deleteBedrijf();
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

    private void insertBedrijf() throws SQLException {
        System.out.println("Type in company name");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Bedrijf bedre=bedrijfrepo.findOne(naam);
        if(bedre!=null) {
            System.out.println("\nCompany already exists");
            return;
        }
        System.out.println("Type in company adress");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        System.out.println("Type in company country name (Must already be inserted in the database)");
        String landNaam=scanner.nextLine();
        if(isEmpty(landNaam)) return;
        Land land=landRepository.findOne(landNaam);
        if(land==null) return;
        Bedrijf bedr=new Bedrijf(naam,adress,land);
        bedrijfrepo.insertOne(bedr);
    }

    private void updateBedrijf() throws SQLException {
        System.out.println("Type in the name of company you want to update");
        String naam=scanner.nextLine();
        Bedrijf bedrijf=bedrijfrepo.findOne(naam);
        if(bedrijf==null) {
            System.out.println("\nCompany not found");
            return;
        }
        System.out.println("Type in new company name");
        String newNaam=scanner.nextLine();
        if(isEmpty(newNaam)) return;
        Bedrijf newbedrijf=bedrijfrepo.findOne(newNaam);
        if(newbedrijf!=null) {
            System.out.println("\nCompany already exists");
            return;
        }
        System.out.println("Type in new company adress");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        System.out.println("Type in new company country name (Must already be inserted in the database)");
        String landNaam=scanner.nextLine();
        if(isEmpty(landNaam)) return;
        Land newLand=landRepository.findOne(landNaam);
        if(newLand==null) return;
        bedrijf.setNaam(newNaam);
        bedrijf.setLand(newLand);
        bedrijf.setAdres(adress);
        bedrijfrepo.updateOne(bedrijf);
    }
    private void deleteBedrijf() throws SQLException {
        System.out.println("Type in the name of company you want to delete");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Bedrijf bedrijf=bedrijfrepo.findOne(naam);
        if(bedrijf==null) {
            System.out.println("\nCompany not found");
            return;
        }
        bedrijfrepo.deleteOne(bedrijf);
    }

    private void findAll() throws SQLException {
        List<Bedrijf> allBedrijf=bedrijfrepo.findAll();
        System.out.println(allBedrijf);
    }

    private void findOne() throws SQLException {
        System.out.println("Type in the name of company you want to search");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Bedrijf bedr=bedrijfrepo.findOne(naam);
        if(bedr==null) {
            System.out.println("\nCompany not found");
            return;
        }
        System.out.println(bedr);
    }

}
