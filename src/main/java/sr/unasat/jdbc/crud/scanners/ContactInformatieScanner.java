package sr.unasat.jdbc.crud.scanners;
import sr.unasat.jdbc.crud.entities.ContactInformatie;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.repositories.ContactInformatieRepository;
import sr.unasat.jdbc.crud.repositories.LandRepository;
import sr.unasat.jdbc.crud.repositories.PersoonRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContactInformatieScanner extends BaseScanner {
    private Scanner scanner;
    private LandRepository landRepository;
    private PersoonRepository persRepo;
    private ContactInformatieRepository contRepo;
    public ContactInformatieScanner() throws SQLException {
        this.scanner = new Scanner(System.in);
        this.landRepository = new LandRepository();
        this.persRepo = new PersoonRepository();
        this.contRepo = new ContactInformatieRepository();
        entity="Contact Information";
        runOptions();
        executeOption();
    }



    public void executeOption() throws SQLException {
        switch (option){
            case "1":
                insertContact();
                break;
            case "2":
                updateContact();
                break;
            case "3":
                deleteContact();
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

    private void insertContact() throws SQLException {
        System.out.println("Type in name of the person");
        String naam=scanner.nextLine();
        if(isEmpty(naam)) return;
        Persoon persoon=persRepo.findOne(naam);
        if(persoon==null){
            System.out.println("Person not found");
        }
        System.out.println("Type in adress of the person");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        System.out.println("Type in phone number of the person");
        String phone=scanner.nextLine();
        if(isEmpty(phone)) return;
        System.out.println("Type in country of the person");
        String country=scanner.nextLine();
        if(isEmpty(country)) return;

        Land land=landRepository.findOne(country);
        if(land==null){
            System.out.println("Country not found");
        }

        ContactInformatie info=new ContactInformatie(adress,phone,persoon,land);
        contRepo.insertOne(info);
    }

    private void updateContact() throws SQLException {
        System.out.println("Type in phone number");
        String phone=scanner.nextLine();
        if(isEmpty(phone)) return;
        System.out.println("Type in adress");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        ContactInformatie cont=contRepo.findOneRecord(phone,adress);
        if(cont==null){
            System.out.println("Information not found");
            return;
        }
        System.out.println("Type in new phone number");
        String newPhone=scanner.nextLine();
        if(isEmpty(newPhone)) return;
        System.out.println("Type in new adress");
        String newAdress=scanner.nextLine();
        if(isEmpty(newAdress)) return;
        System.out.println("Type in new country");
        String newLand=scanner.nextLine();
        if(isEmpty(newLand)) return;
        Land land = landRepository.findOne(newLand);
        if(land==null){
            System.out.println("Country not found");
            return;
        }
        cont.setTelefoonNummer(newPhone);
        cont.setAdres(newAdress);
        cont.setLand(land);
        contRepo.updateOneRecord(cont);
    }
    private void deleteContact() throws SQLException {
        System.out.println("Type in phone number to be deleted");
        String phone=scanner.nextLine();
        if(isEmpty(phone)) return;
        System.out.println("Type in adress to be deleted");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        ContactInformatie cont=contRepo.findOneRecord(phone,adress);
        contRepo.deleteOne(cont);

    }

    private void findAll() throws SQLException {
        List<ContactInformatie> recs=contRepo.findAllRecords();
        System.out.println(recs);
    }

    private void findOne() throws SQLException {
        System.out.println("Type in phone number to be searched");
        String phone=scanner.nextLine();
        if(isEmpty(phone)) return;
        System.out.println("Type in adress to be searched");
        String adress=scanner.nextLine();
        if(isEmpty(adress)) return;
        ContactInformatie cont=contRepo.findOneRecord(phone,adress);
        System.out.println(cont);
    }

}
