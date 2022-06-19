package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.*;
import sr.unasat.jdbc.crud.services.ConnectionInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersoonBedrijfRepository {
    private final Connection connection;
    String msg;
    /*

    get connections instance from static connection class

    */
    public PersoonBedrijfRepository() {
        connection = ConnectionInstance.getInstance();
    }


    public List<PersoonBedrijfPositie> findAll() throws SQLException {
        String sql ="SELECT p.id as pid,p.naam pnaam,b.id bid,b.naam bnaam,b.adres badres,l.id lid,l.naam lnaam,positie  FROM adres_boek.persoon_bedrijf pb\n" +
                "join persoon p on pb.persoon_id = p.id \n" +
                "join bedrijf b on pb.bedrijf_id = b.id \n" +
                "join land l on b.land_id =l.id";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet res = stmt.executeQuery();
        List<PersoonBedrijfPositie> list = new ArrayList<>();

        while (res.next()) {
            int bedrijfId = res.getInt("bid");
            String bedrijfNaam = res.getString("bnaam");
            String bedrijfAdres = res.getString("badres");

            int landId = res.getInt("lid");
            String landNaam = res.getString("lnaam");

            Land land = new Land(landId, landNaam);
            Bedrijf bedrijf = new Bedrijf(bedrijfId, bedrijfNaam, bedrijfAdres, land);

            int id = res.getInt("pid");
            String naam = res.getString("pnaam");

            Persoon persoon = new Persoon(id, naam);

            String positie = res.getString("positie");

            PersoonBedrijfPositie obj = new PersoonBedrijfPositie(persoon, bedrijf, positie);

            list.add(obj);
        }
        return list;
    }

    public void insertOne(PersoonBedrijfPositie obj) throws SQLException {

        String sql = "INSERT INTO persoon_bedrijf (bedrijf_id,persoon_id,positie) VALUES (?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(2, obj.getPersoon().getId());
        stmt.setInt(1, obj.getBedrijf().getId());
        stmt.setString(3, obj.getPositie());
        if (stmt.executeUpdate() == 1) {
            BedrijfPositie bedrijfPositie = new BedrijfPositie(obj.getBedrijf(), obj.getPositie());
            msg = "\nSuccesvol ingevoerd\n";
        } else {
            msg = "\nNiet ingevoerd\n";
        }
        System.out.println(msg);
    }

    public void deleteOne(PersoonBedrijfPositie obj) throws SQLException {
        String sql = "DELETE FROM persoon_bedrijf WHERE bedrijf_id=?  and persoon_id=? and positie=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(2, obj.getPersoon().getId());
        stmt.setInt(1, obj.getBedrijf().getId());
        stmt.setString(3, obj.getPositie());
        if (stmt.executeUpdate() == 1) {
            BedrijfPositie bedrijfPositie = new BedrijfPositie(obj.getBedrijf(), obj.getPositie());
            msg = "\nDeleted\n";
        } else {
            msg = "\nNot Deleted\n";
        }
        System.out.println(msg);
    }

    public void updateOne(PersoonBedrijfPositie obj) throws SQLException {
        String sql = "UPDATE persoon_bedrijf SET bedrijf_id=?,persoon_id=?,positie=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(2, obj.getPersoon().getId());
        stmt.setInt(1, obj.getBedrijf().getId());
        stmt.setString(3, obj.getPositie());
        stmt.setInt(4, obj.getId());
        if (stmt.executeUpdate() == 1) {
            msg = "\nUpdated\n";
        } else {
            msg = "\nNot updated\n";
        }
        System.out.println(msg);
    }

    public void addPeroonBedrijf(Persoon persoon) throws SQLException {
        BedrijfRepository bedrijfRepo = new BedrijfRepository();
        String sql = "SELECT bedrijf_id,positie FROM persoon_bedrijf WHERE persoon_id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, persoon.getId());
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            int bedrId = res.getInt("bedrijf_id");
            String bedrPos = res.getString("positie");
            Bedrijf bedrijf = bedrijfRepo.findOne(bedrId);
            BedrijfPositie bedrijfPositie = new BedrijfPositie(bedrijf, bedrPos);
            persoon.addBedrijf(bedrijfPositie);
        }
        res.close();
    }

    public PersoonBedrijfPositie findOne(Persoon persoon,Bedrijf bedrijf,String positie) throws SQLException {
        String sql ="SELECT p.id as pid,p.naam pnaam,b.id bid,b.naam bnaam,b.adres badres,l.id lid,l.naam lnaam,positie,pb.id pbid  FROM adres_boek.persoon_bedrijf pb\n" +
                "join persoon p on pb.persoon_id = p.id \n" +
                "join bedrijf b on pb.bedrijf_id = b.id \n" +
                "join land l on b.land_id =l.id WHERE pb.persoon_id=? AND pb.bedrijf_id=? AND pb.positie=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,persoon.getId());
        stmt.setInt(2, bedrijf.getId());
        stmt.setString(3, positie);
        ResultSet res = stmt.executeQuery();
        PersoonBedrijfPositie returnObj = null;
        if (res.next()) {
            int bedrijfId = res.getInt("bid");
            String bedrijfNaam = res.getString("bnaam");
            String bedrijfAdres = res.getString("badres");

            int landId = res.getInt("lid");
            String landNaamn = res.getString("lnaam");

            Land land = new Land(landId, landNaamn);
            Bedrijf bedrijfn = new Bedrijf(bedrijfId, bedrijfNaam, bedrijfAdres, land);

            int id = res.getInt("pid");
            String naam = res.getString("pnaam");

            Persoon persoonn = new Persoon(id, naam);

            String positien = res.getString("positie");

            int pbid = res.getInt("pbid");
            returnObj = new PersoonBedrijfPositie(pbid,persoonn, bedrijfn, positien);
        }
        return returnObj;
    }

}
