package sr.unasat.jdbc.crud.scanners;

import sr.unasat.jdbc.crud.entities.Bedrijf;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.entities.PersoonBedrijfPositie;
import sr.unasat.jdbc.crud.repositories.BedrijfRepository;
import sr.unasat.jdbc.crud.repositories.PersoonBedrijfRepository;
import sr.unasat.jdbc.crud.repositories.PersoonRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersoonBedrijfPositieScanner extends BaseScanner {

    private PersoonRepository persoonRepo;
    private BedrijfRepository bedrijfRepo;
    private PersoonBedrijfRepository bedrijfPersRepo;
    Scanner scanner;

    public PersoonBedrijfPositieScanner() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.bedrijfRepo = new BedrijfRepository();
        this.persoonRepo = new PersoonRepository();
        this.bedrijfPersRepo = new PersoonBedrijfRepository();
        entity="Person Company";
        runOptions();
        executeOption();
    }


    public void executeOption() throws SQLException {
        switch (option) {
            case "1":
                insertPersoonBedrijf();
                break;
            case "2":
                updatePersoonBedrijf();
                break;
            case "3":
                deletePersoonBedrijf();
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
        if (!option.equals("q")) {
            runOptions();
            executeOption();
        }
    }

    private Object[] promptIfo(String type) throws SQLException {
        Object[] arr = new Object[3];
        System.out.printf("\nType in person's name to be %s\n", type);
        String naam = scanner.nextLine();
        System.out.printf("\nType in company name to be %s\n", type);
        String bedrijfNaam = scanner.nextLine();
        System.out.printf("\nType in person's position to be %s\n", type);
        String positie = scanner.nextLine();
        Bedrijf bedrijf = bedrijfRepo.findOne(bedrijfNaam);
        if (bedrijf == null) {
            System.out.println("\nCompany not found");
        }
        Persoon persoon = persoonRepo.findOne(naam);
        if (persoon == null) {
            System.out.println("\nPerson not found");
        }
        arr[0] = persoon;
        arr[1] = bedrijf;
        arr[2] = positie;
        return arr;
    }


    private void insertPersoonBedrijf() throws SQLException {
        Object[] info = promptIfo("inserted");
        if (info[0] == null || info[1] == null || info[2] == "") {
            System.out.println("\nInfo is not correct");
            return;
        }
        PersoonBedrijfPositie obj = new PersoonBedrijfPositie((Persoon) info[0], (Bedrijf) info[1], (String) info[2]);
        bedrijfPersRepo.insertOne(obj);
    }

    private void updatePersoonBedrijf() throws SQLException {
        Object[] info = promptIfo("updated");
        if (info[0] == null || info[1] == null || info[2] == "") {
            System.out.println("\nInfo is not correct");
            return;
        }
        PersoonBedrijfPositie obj = bedrijfPersRepo.findOne((Persoon) info[0], (Bedrijf) info[1], (String) info[2]);
        if (obj == null) {
            System.out.println("\nRecord with given info not found");
            return;

        }
        Object[] infoNew = promptIfo("new value");
        if (infoNew[0] == null || infoNew[1] == null || infoNew[2] == "") {
            System.out.println("\nInfo is not correct");
            return;
        }
        obj.setBedrijf((Bedrijf) infoNew[1]);
        obj.setPositie((String) infoNew[2]);
        obj.setPersoon((Persoon) infoNew[0]);
        bedrijfPersRepo.updateOne(obj);
    }

    private void deletePersoonBedrijf() throws SQLException {
        Object[] info = promptIfo("deleted");
        if (info[0] == null || info[1] == null || info[2] == "") {
            System.out.println("\nInfo is not correct");
            return;
        }
        PersoonBedrijfPositie obj = bedrijfPersRepo.findOne((Persoon) info[0], (Bedrijf) info[1], (String) info[2]);
        if (obj == null) {
            System.out.println("\nRecord with given info not found");
            return;
        }
        bedrijfPersRepo.deleteOne(obj);
    }

    private void findAll() throws SQLException {
        List<PersoonBedrijfPositie> allRec = bedrijfPersRepo.findAll();
        System.out.println(allRec);
    }

    private void findOne() throws SQLException {
        Object[] info = promptIfo("searched");
        PersoonBedrijfPositie obj = bedrijfPersRepo.findOne((Persoon) info[0], (Bedrijf) info[1], (String) info[2]);
        if (obj == null) {
            System.out.println("\nRecord with given info not found");
            return;
        }
        System.out.println(obj);
    }


}
