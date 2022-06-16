package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Bedrijf;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.services.ConnectionInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BedrijfRepository {
    private final Connection connection;

    public BedrijfRepository() {
        this.connection = ConnectionInstance.getInstance();
    }

    public void insertOne(Bedrijf bedrijf, Land land) throws SQLException {
        Bedrijf find = findOne(bedrijf.getNaam());
        if (find != null) {
            System.out.printf("\nBedrijf %s already exists\n", bedrijf.getNaam());
            return;
        }
        String sql = "INSERT INTO bedrijf (naam,adres,land_id) VALUES (?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, bedrijf.getNaam());
        stmt.setString(2, bedrijf.getAdres());
        stmt.setInt(3, land.getId());
        if (stmt.executeUpdate()==1) {
            System.out.println("\nCompany inserted\n");
        } else {
            System.out.println("\nCompany not inserted\n");
        }
    }

    public void insertOne(Bedrijf bedrijf) throws SQLException {
        Bedrijf find = findOne(bedrijf.getNaam());
        if (find != null) {
            System.out.printf("\nBedrijf %s already exists\n", bedrijf.getNaam());
            return;
        }
        String sql = "INSERT INTO bedrijf (naam,adres,land_id) VALUES (?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, bedrijf.getNaam());
        stmt.setString(2, bedrijf.getAdres());
        stmt.setInt(3, bedrijf.getLand().getId());
        if (stmt.executeUpdate()==1) {
            System.out.println("\nCompany inserted\n");
        } else {
            System.out.println("\nCompany not inserted\n");
        }
    }

    public List<Bedrijf> findAll() throws SQLException {
        List<Bedrijf> list = new ArrayList<>();
        String sql = "SELECT bedrijf.id bedrijf_id,bedrijf.adres bedrijf_adres,bedrijf.naam bedrijf_naam,land.id land_id,land.naam land_naam"
                + " FROM bedrijf join land on land_id=land.id";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            int bedrijfId = res.getInt("bedrijf_id");
            String bedrijfNaam = res.getString("bedrijf_naam");
            String bedrijfAdres = res.getString("bedrijf_adres");

            int landId = res.getInt("land_id");
            String landNaam = res.getString("land_naam");

            list.add(new Bedrijf(bedrijfId, bedrijfNaam, bedrijfAdres, new Land(landId, landNaam)));
        }
        return list;
    }

    public Bedrijf findOne(String naam) throws SQLException {
        String sql = "SELECT bedrijf.id bedrijf_id,bedrijf.adres bedrijf_adres,bedrijf.naam bedrijf_naam,land.id land_id,land.naam land_naam"
                + " FROM bedrijf join land on land_id=land.id"
                + " WHERE bedrijf.naam=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, naam);
        ResultSet res = stmt.executeQuery();
        Bedrijf bedrijf = null;
        if (res.next()) {
            int bedrijfId = res.getInt("bedrijf_id");
            String bedrijfNaam = res.getString("bedrijf_naam");
            String bedrijfAdres = res.getString("bedrijf_adres");

            int landId = res.getInt("land_id");
            String landNaam = res.getString("land_naam");

            bedrijf = new Bedrijf(bedrijfId, bedrijfNaam, bedrijfAdres, new Land(landId, landNaam));
        }
        res.close();
        return bedrijf;
    }
    public Bedrijf findOne(int idnr) throws SQLException {
        String sql = "SELECT bedrijf.id bedrijf_id,bedrijf.adres bedrijf_adres,bedrijf.naam bedrijf_naam,land.id land_id,land.naam land_naam"
                + " FROM bedrijf join land on land_id=land.id"
                + " WHERE bedrijf.id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idnr);
        ResultSet res = stmt.executeQuery();
        Bedrijf bedrijf = null;
        if (res.next()) {
            int bedrijfId = res.getInt("bedrijf_id");
            String bedrijfNaam = res.getString("bedrijf_naam");
            String bedrijfAdres = res.getString("bedrijf_adres");

            int landId = res.getInt("land_id");
            String landNaam = res.getString("land_naam");

            bedrijf = new Bedrijf(bedrijfId, bedrijfNaam, bedrijfAdres, new Land(landId, landNaam));
        }
        res.close();
        return bedrijf;
    }

    public void deleteOne(String naam) throws SQLException {
        Bedrijf find=findOne(naam);
        if(find==null){
            System.out.printf("\nBedrijf %s niet gevonden in het systeem\n",naam);
            return;
        }
        String sql="DELETE FROM bedrijf WHERE id = ?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,find.getId());
        if(stmt.executeUpdate()==1){
            System.out.printf("\n%s deleted\n",find.getNaam());
        }else{
            System.out.printf("\n%s not deleted\n",find.getNaam());
        }
    }
    public void deleteOne(Bedrijf bedrijf) throws SQLException {
        String sql="DELETE FROM bedrijf WHERE id = ?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,bedrijf.getId());
        if(stmt.executeUpdate()==1){
            System.out.printf("\n%s deleted\n",bedrijf.getNaam());
        }else{
            System.out.printf("\n%s not deleted\n",bedrijf.getNaam());
        }
    }

    public void updateOne(Bedrijf bedrijf) throws  SQLException{
        Bedrijf find = findOne(bedrijf.getNaam());
        if (find != null) {
            System.out.printf("\nBedrijf %s already exists\n", bedrijf.getNaam());
            return;
        }

        String sql= "UPDATE bedrijf SET naam=?,adres=?,land_id=? WHERE id=?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,bedrijf.getNaam());
        stmt.setString(2,bedrijf.getAdres());
        stmt.setInt(3,bedrijf.getLand().getId());
        stmt.setInt(4,bedrijf.getId());
        if(stmt.executeUpdate()==1){
            System.out.println("\nCompany updated\n");
        }else{
            System.out.println("\nCompany updated\n");
        }

    }

}
