package sr.unasat.jdbc.crud.entities;

public class PersoonBedrijfPositie {
    private Bedrijf bedrijf;
    private String positie;
    private Persoon persoon;

    private int id;

    public PersoonBedrijfPositie(Persoon persoon, Bedrijf bedrijf, String positie) {
        this.bedrijf = bedrijf;
        this.positie = positie;
        this.persoon = persoon;
    }
    public PersoonBedrijfPositie(int id,Persoon persoon, Bedrijf bedrijf, String positie) {
        this.bedrijf = bedrijf;
        this.positie = positie;
        this.persoon = persoon;
        this.id = id;
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

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\n{" +
                "persoon=" + persoon.getNaam() +
                ", bedrijf=" + bedrijf.getNaam() +
                ", positie='" + positie + '\'' +
                "}\n";
    }
}
