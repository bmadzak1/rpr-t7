package ba.unsa.etf.rpr.tutorijal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    Tutorijal(){}

    public static void main(String[] args){
        ucitajGradove();
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

    void ubaciGrad(String s){

    }
}
