package sr.unasat.jdbc.crud.entities;

import java.util.Objects;

public class Bedrijf {
    private int id;
    private String naam;
    private String adres;

    private Land land;

    public Bedrijf(int id, String naam, String adres, Land land) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.land = land;
    }

    public Bedrijf(String naam, String adres, Land land) {
        this.naam = naam;
        this.adres = adres;
        this.land = land;
    }

    public int getId() {
        return id;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }


    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bedrijf)) return false;
        Bedrijf bedrijf = (Bedrijf) o;
        return getId() == bedrijf.getId() && getNaam().equals(bedrijf.getNaam()) && getAdres().equals(bedrijf.getAdres()) && getLand().equals(bedrijf.getLand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaam(), getAdres(), getLand());
    }

    @Override
    public String toString() {
        return "\nBedrijf{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", adres='" + adres + '\'' +
                ", land=" + land +
                '}'+"\n";
    }
}
