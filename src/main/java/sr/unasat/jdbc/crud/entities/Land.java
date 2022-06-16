package sr.unasat.jdbc.crud.entities;

import java.util.Objects;

public class Land {
    private Integer id;
    private String naam;

    public Land(Integer id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Land(String naam) {
        this.naam = naam;
    }

    public Integer getId() {
        return id;
    }


    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Land)) return false;
        Land land = (Land) o;
        return getId().equals(land.getId()) && getNaam().equals(land.getNaam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNaam());
    }

    @Override
    public String toString() {
        return "Land{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                '}';
    }

}
