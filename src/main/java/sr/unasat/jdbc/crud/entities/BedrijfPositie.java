package sr.unasat.jdbc.crud.entities;

import java.util.Objects;

public class BedrijfPositie {
    private Bedrijf bedrijf;
    private String positie;

    public BedrijfPositie(Bedrijf bedrijf, String positie) {
        this.bedrijf = bedrijf;
        this.positie = positie;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    public String getPositie() {
        return positie;
    }

    public void setPositie(String positie) {
        this.positie = positie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BedrijfPositie)) return false;
        BedrijfPositie that = (BedrijfPositie) o;
        return getBedrijf().equals(that.getBedrijf()) && getPositie().equals(that.getPositie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBedrijf(), getPositie());
    }

    @Override
    public String toString() {
        return "{" +
                "" + bedrijf.getNaam() +", " + bedrijf.getAdres() +
                ", positie='" + positie + '\''+"}"
                ;
    }
}
