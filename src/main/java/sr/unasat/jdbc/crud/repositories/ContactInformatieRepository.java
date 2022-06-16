package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.ContactInformatie;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.ConnectionInstance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInformatieRepository {
    private final Connection connection;

    public ContactInformatieRepository() {
        connection = ConnectionInstance.getInstance();
    }

    public List<ContactInformatie> findAllRecords() throws SQLException {
        List<ContactInformatie> contactList = new ArrayList<ContactInformatie>();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sql = "select ci.id, ci.adres, ci.telefoon_nummer , p.id pid, p.naam pnaam, l.id lid, l.naam land_naam" +
                " from contact_informatie ci" +
                " join persoon p" +
                " on p.id = ci.persoon_id" +
                " join land l  " +
                " on l.id = ci.land_id";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String adres = rs.getString("adres");
            String  telefoonNummer = rs.getString("telefoon_nummer");
            int persoonId = rs.getInt("pid");
            String persoonNaam = rs.getString("pnaam");
            Persoon persoon = new Persoon(persoonId, persoonNaam);

            int landId = rs.getInt("lid");
            String landNaam = rs.getString("land_naam");
            Land land = new Land(landId, landNaam);

            contactList.add(new ContactInformatie(id, adres, telefoonNummer, persoon, land));
        }
        rs.close();
        
        return contactList;
    }

    public ContactInformatie findOneRecord(String telNum, String ciAdres) throws SQLException {
        ContactInformatie contactInformatie = null;
        PreparedStatement stmt = null;
        String sql = "select ci.id, ci.adres, ci.telefoon_nummer , p.id pid, p.naam pnaam, l.id lid, l.naam land_naam" +
                " from contact_informatie ci" +
                " join persoon p" +
                " on p.id = ci.persoon_id" +
                " join land l" +
                " on l.id = ci.land_id where ci.telefoon_nummer = ? or ci.adres = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, telNum);
        stmt.setString(2, ciAdres);
        ResultSet rs = stmt.executeQuery();
        //STEP 5: Extract data from result set
        if (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String adres = rs.getString("adres");
            String telefoonNummer = rs.getString("telefoon_nummer");

            int persoonId = rs.getInt("pid");
            String persoonNaam = rs.getString("pnaam");
            Persoon persoon = new Persoon(persoonId, persoonNaam);

            int landId = rs.getInt("lid");
            String landNaam = rs.getString("land_naam");
            Land land = new Land(landId, landNaam);

            contactInformatie = new ContactInformatie(id, adres, telefoonNummer, persoon, land);
        }
        rs.close();
        

        return contactInformatie;
    }

    public void updateOneRecord(ContactInformatie contact) throws SQLException {
        PreparedStatement stmt = null;
        int result = 0;
        String sql = "update contact_informatie ci set ci.telefoon_nummer = ?, ci.persoon_id = ?,ci.land_id = ?,ci.adres = ? where ci.id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, contact.getTelefoonNummer());
        stmt.setInt(2, contact.getPersoon().getId());
        stmt.setInt(3, contact.getLand().getId());
        stmt.setString(4, contact.getAdres());
        stmt.setInt(5, contact.getId());
        String msg = stmt.executeUpdate() == 1 ? "\nInfo updated\n" : "\nInfo not updated\n";
        System.out.println(msg);
    }
    public void insertOne(ContactInformatie contact) throws SQLException {
       String SQL="INSERT INTO contact_informatie" +
               " (adres, telefoon_nummer, persoon_id, land_id)" +
               " VALUES(?,?,?,?)";
       PreparedStatement stmt=connection.prepareStatement(SQL);
       stmt.setString(1,contact.getAdres());
       stmt.setString(2,contact.getTelefoonNummer());
       stmt.setInt(3,contact.getPersoon().getId());
       stmt.setInt(4,contact.getLand().getId());
        String msg = stmt.executeUpdate() == 1 ? "\nInfo inserted\n" : "\nInfo not inserted\n";
        System.out.println(msg);

    }
    public void deleteOne(ContactInformatie contact) throws SQLException {
       String SQL="DELETE FROM contact_informatie WHERE telefoon_nummer=? and adres=?";
       PreparedStatement stmt=connection.prepareStatement(SQL);
       stmt.setString(1,contact.getAdres());
       stmt.setString(2,contact.getTelefoonNummer());
        String msg = stmt.executeUpdate() == 1 ? "\nInfo deleted\n" : "\nInfo not deleted\n";
        System.out.println(msg);

    }

}
