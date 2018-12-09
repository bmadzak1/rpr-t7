package ba.unsa.etf.rpr.tutorijal7;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Tutorijal {

    Tutorijal(){}

    public static void main(String[] args){
        zapisiXml(ucitajXml(ucitajGradove()));
    }

    public static ArrayList<Grad> ucitajGradove(){
        ArrayList<Grad> gradovi = new ArrayList<>();
        Scanner ulaz;
        int brojac = -1;
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Datoteka mjerenja.txt ne postoji ili se ne može otvoriti.");
            System.out.println("Greška: " + e);
            return null;
        }
        try{
            while (ulaz.hasNextLine()){
                boolean zarez = false;
                String str = "";
                String grad = ulaz.nextLine();
                int tempBrojac = 0;
                brojac++;
                for(int i = 0; i < grad.length(); i++){
                    if(grad.charAt(i) == ',' && !zarez){
                        gradovi.add(new Grad());
                        gradovi.get(brojac).setNaziv(str);
                        zarez = true;
                        str = "";
                    }
                    else if(grad.charAt(i) == ',' && zarez){
                        gradovi.get(brojac).getTemperature()[tempBrojac] = Double.parseDouble(str);
                        str = "";
                        tempBrojac++;
                    }
                    else if(grad.charAt(i) != ','){
                        str += grad.charAt(i);
                    }

                    if(tempBrojac == 1000)
                        break;
                }
            }
        }catch (Exception e){
        }

        return gradovi;
    }

    public static UN ucitajXml(ArrayList<Grad> gradovi){
        UN un = new UN();
        ArrayList<Drzava> drzave = new ArrayList<Drzava>();
        Document xmldoc = null;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmldoc = docReader.parse(new File("drzave.xml"));
            NodeList djeca = xmldoc.getDocumentElement().getChildNodes();
            for(int i = 0; i < djeca.getLength(); i++){
                Node dijete = djeca.item(i);
                if(dijete instanceof Element){
                    Element drzava = (Element) dijete;
                    if(drzava.getTagName() == "drzava"){
                        int brojStanovnika = Integer.parseInt(drzava.getAttribute("stanovnika"));
                        String naziv = drzava.getElementsByTagName("naziv").item(0).getTextContent();
                        Double povrsina = Double.parseDouble(drzava.getElementsByTagName("povrsina").item(0).getTextContent());
                        Node temp = drzava.getElementsByTagName("povrsina").item(0);
                        String jedinicaPovrsine = drzava.getAttribute("jedinica");
                        NodeList glavniGrad = drzava.getElementsByTagName("glavnigrad");
                        Element grad = (Element)glavniGrad.item(0);
                        String nazivGlavnogGrada = grad.getElementsByTagName("naziv").item(0).getTextContent();
                        int brojStanovnikaGrada = Integer.parseInt(grad.getAttribute("stanovnika"));
                        double[] temperatureGrada = new double[1000];
                        boolean postojiGrad = false;
                        for(Grad g : gradovi){
                            if(g.getNaziv() == nazivGlavnogGrada){
                                temperatureGrada = g.getTemperature();
                                g.setBrojStanovinika(brojStanovnikaGrada);
                                drzave.add(new Drzava(naziv, brojStanovnika, povrsina, jedinicaPovrsine, g));
                                postojiGrad = true;
                            }
                        }
                        if(!postojiGrad){
                            drzave.add(new Drzava(naziv, brojStanovnika, povrsina, jedinicaPovrsine, null));
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("drzave.xml se ne moze otvoriti");
        }
        un.setDrzave(drzave);
        return un;
    }

    public static void zapisiXml(UN un){
        XMLEncoder izlaz = null;
        try{
            izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(un);
        }catch (FileNotFoundException e){
            System.out.println("un.xml ne postoji");
        }finally {
            izlaz.close();
        }
    }
}
