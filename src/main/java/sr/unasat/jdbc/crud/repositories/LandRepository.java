package sr.unasat.jdbc.crud.repositories;

import com.mysql.cj.protocol.Resultset;
import sr.unasat.jdbc.crud.entities.Bedrijf;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.ConnectionInstance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LandRepository {
    private final Connection connection;

    public LandRepository() {
        connection = ConnectionInstance.getInstance();
    }

    public List<Land> findAllRecords() throws SQLException {
        List<Land> landList = new ArrayList<>();
        Statement stmt = null;
        stmt = connection.createStatement();
        String sql = "select * from land";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naam = rs.getString("naam");
            landList.add(new Land(id, naam));
        }
        rs.close();
        return landList;
    }

    public void insertOneRecord(Land land) throws SQLException {
        PreparedStatement stmt = null;
        Land find = findOne(land.getNaam());
        if (find != null) {
            System.out.printf("\nCountry %s already exists\n", land.getNaam());
            return;
        }
        String sql = "insert into land (naam) values(?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, land.getNaam());
            String msg = stmt.executeUpdate()==1 ? "Inserted: " + land.getNaam() :"Country not inserted";
            System.out.println(msg);
    }

    public void updateOne(Land land) throws SQLException {
        Land find = findOne(land.getNaam());
        if (find != null) {
            System.out.printf("\nCountry %s already exists\n", land.getNaam());
            return;
        }

        String sql = "UPDATE land SET naam=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, land.getNaam());
        stmt.setInt(2, land.getId());
        String msg = stmt.executeUpdate() == 1 ? "\nCountry updated\n" : "\nCountry not updated\n";
        System.out.println(msg);
    }

    public Land findOne(String naam) throws SQLException {
        String sql = "SELECT * FROM land WHERE naam = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, naam);
        ResultSet res = stmt.executeQuery();
        Land land = null;
        if (res.next()) {
            String landNaam = res.getString("naam");
            int id = res.getInt("id");
            land = new Land(id, landNaam);
        }
        res.close();

        return land;
    }

    public void deleteOneRecord(Land land) throws SQLException {
        PreparedStatement stmt = null;
        int result = 0;
        String sql = "DELETE FROM land WHERE id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, land.getId());
        String msg = stmt.executeUpdate() == 1 ? "\nDeleted: " + land.getNaam() : "\nCountry not deleted\n";
        System.out.println(msg);
    }

}
