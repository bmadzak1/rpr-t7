package ba.unsa.etf.rpr.tutorijal7;

public class Drzava {
    private String naziv;
    private int brojStanovinika;
    private double povrsina;
    private String jedinicaZaPovrsinu;
    private Grad glavniGrad;

    public Drzava(){
        naziv = "";
        brojStanovinika = 0;
        povrsina = 0;
        jedinicaZaPovrsinu = "";
        glavniGrad = null;
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

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public String getJedinicaZaPovrsinu() {
        return jedinicaZaPovrsinu;
    }

    public void setJedinicaZaPovrsinu(String jedinicaZaPovrsinu) {
        this.jedinicaZaPovrsinu = jedinicaZaPovrsinu;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
