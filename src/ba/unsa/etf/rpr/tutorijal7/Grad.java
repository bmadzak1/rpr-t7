package ba.unsa.etf.rpr.tutorijal7;

public class Grad {
    private String naziv;
    private int brojStanovinika;
    private double[] temperature = new double[1000];

    Grad(){
        naziv = "";
        brojStanovinika = 0;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovinika() {
        return brojStanovinika;
    }

    public void setBrojStanovinika(int brojStanovinika) {
        this.brojStanovinika = brojStanovinika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }
}
