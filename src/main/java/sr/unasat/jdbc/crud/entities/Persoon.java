package sr.unasat.jdbc.crud.entities;

import sr.unasat.jdbc.crud.repositories.PersoonBedrijfRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Persoon {
    private Integer id;
    private String naam;
    private List<BedrijfPositie> bedrijf= new ArrayList<>();
  public Persoon(int id, String naam) throws SQLException {
      PersoonBedrijfRepository persoonBedrijfrepoRepository =new PersoonBedrijfRepository();
      this.id = id;
      this.naam = naam;
      persoonBedrijfrepoRepository.addPeroonBedrijf(this);
  }

    public Persoon(String naam) {
        this.naam = naam;
    }

    public Persoon(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addBedrijf(BedrijfPositie bedrijf) {
        this.bedrijf.add(bedrijf);
    }

    public void removeBedrijf(BedrijfPositie bedrijf){
        int getIndex=getBedrijf().indexOf(bedrijf);
        if(getIndex!=-1) getBedrijf().remove(getIndex);
    }
    public List<BedrijfPositie> getBedrijf() {
        return bedrijf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", bedrijf=" + bedrijf +
                '}';
    }
}
