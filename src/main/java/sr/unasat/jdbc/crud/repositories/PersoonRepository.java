package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Bedrijf;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.ConnectionInstance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersoonRepository {
    private final Connection connection;
    /*

    get connections instance from static connection class

    */
    public PersoonRepository() {
        connection = ConnectionInstance.getInstance();
    }

    public List<Persoon> findAll() throws SQLException {
        List<Persoon> persoonList = new ArrayList<Persoon>();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sql = "select * from persoon";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naam = rs.getString("naam");
            persoonList.add(new Persoon(id, naam));
        }
        rs.close();

        return persoonList;
    }

    public void insertOneRecord(Persoon persoon) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into persoon (naam) values(?)";
        Persoon find =findOne(persoon.getNaam());
        if(find!=null){
            System.out.printf("\nPerson %s already exists\n", persoon.getNaam());
            return;
        }
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, persoon.getNaam());
        String msg = stmt.executeUpdate() == 1 ? "Inserted: " + persoon.getNaam() : "Person not inserted";
        System.out.println(msg);
    }

    public void updateOneRecord(Persoon persoon) throws SQLException {
        PreparedStatement stmt = null;
        Persoon find =findOne(persoon.getNaam());
        if(find!=null){
            System.out.printf("\nPerson %s already exists\n", persoon.getNaam());
            return;
        }

        String sql = "UPDATE persoon SET naam = ? WHERE id=? ";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, persoon.getNaam());
        stmt.setInt(2, persoon.getId());
        String msg= stmt.executeUpdate()==1? "\nPerson updated\n" :"\nPerson did not update\n";
        System.out.println(msg);
    }

    public void deleteOneRecord(Persoon persoon) throws SQLException {
        PreparedStatement stmt = null;
        int result = 0;
        String sql = "DELETE FROM persoon WHERE persoon.id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, persoon.getId());
        String msg = stmt.executeUpdate() == 1 ? "Deleted: " + persoon.getNaam() : "Person not deleted";
        System.out.println(msg);
    }

    public Persoon findOne(String naam) throws SQLException {
        String sql = "SELECT * FROM persoon WHERE naam= ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, naam);
        ResultSet res = stmt.executeQuery();
        Persoon persoon = null;
        if (res.next()) {
            int id = res.getInt("id");
            String perNaam = res.getString("naam");
            persoon = new Persoon(id, perNaam);
        }
        res.close();
        return persoon;
    }

    public Persoon findOne(int idnr) throws SQLException {
        String sql = "SELECT * from persoon WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idnr);
        ResultSet res = stmt.executeQuery();
        Persoon persoon = null;
        if (res.next()) {
            int id = res.getInt("id");
            String perNaam = res.getString("naam");
            persoon = new Persoon(id, perNaam);
        }
        res.close();
        return persoon;
    }

}
