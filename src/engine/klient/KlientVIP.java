package engine.klient;

import engine.Kasa;
import engine.basen.BasenOlimpijski;
import engine.basen.BasenRekreacyjny;
import engine.basen.Brodzik;
import gui.klient.KlientPanel;
import gui.symulacja.BasenOlimpijskiPanel;
import gui.symulacja.BasenRekreacyjnyPanel;
import gui.symulacja.BrodzikPanel;

import java.text.DecimalFormat;
import java.util.Random;

import static engine.Symulacja.*;
import static gui.klient.KlientPanel.*;
import static gui.parametryzacja.ParametryzacjaSymulacjaPanel.dodajWynikiObslugi;

public class KlientVIP extends Thread{

    private Kasa kasa;

    private BasenOlimpijski basenOlimpijski;
    private BasenRekreacyjny basenRekreacyjny;
    private Brodzik brodzik;
    private BasenOlimpijskiPanel basenOlimpijskiPanel;
    private BasenRekreacyjnyPanel basenRekreacyjnyPanel;
    private BrodzikPanel brodzikPanel;

    private final boolean VIP = true;
    private boolean oplata = false;
    private final int wiek;
    public final int czasPlywania;
    private int jakiBasen;

    private int liczbaDzieci;
    private int[] wiekDziecka = {0, 0};
    public boolean[] pampersJ = {false, false};

    public int wyswietlanie1;
    public int wyswietlanie2;
    private boolean[] dostepneBaseny = {true, true, true};

    public boolean czyMoze = false;
    public boolean brakOpcji = false;

    public int flagBasen = -1;
    public boolean flagPampers = false;

    DecimalFormat df = new DecimalFormat("#0.00");
    Random los = new Random();

    public KlientVIP(int numer, int tmp, int maxLos, Kasa kasa, BasenOlimpijski basenOlimpijski, BasenOlimpijskiPanel basenOlimpijskiPanel, BasenRekreacyjny basenRekreacyjny, BasenRekreacyjnyPanel basenRekreacyjnyPanel, Brodzik brodzik, BrodzikPanel brodzikPanel){

        this.kasa = kasa;
        this.basenOlimpijski = basenOlimpijski;
        this.basenRekreacyjny = basenRekreacyjny;
        this.brodzik = brodzik;
        this.basenOlimpijskiPanel = basenOlimpijskiPanel;
        this.basenRekreacyjnyPanel = basenRekreacyjnyPanel;
        this.brodzikPanel = brodzikPanel;

        this.wiek = getRandomIntNumber(1, 70);
        this.czasPlywania = getRandomIntNumber(20,120);
        this.jakiBasen = getRandomIntNumber(0,2);

        if(this.wiek >= 18){
            this.liczbaDzieci = getRandomIntNumber(0,2);
            if(this.liczbaDzieci > 0){
                if(((tmpV - this.liczbaDzieci) > tmp) ||
                   ((maxLos + this.liczbaDzieci) < liczbaV)){
                    for(int i = 0; i < liczbaDzieci; i++){
                        this.wiekDziecka[i] = getRandomIntNumber(1,17);
                        if(wiekDziecka[i] <= 3){
                            pampersJ[i] = los.nextBoolean();
                        }else {
                            pampersJ[i] = false;
                        }
                    }
                }else {
                    this.liczbaDzieci = 0;
                }
            }
            this.setName("[" + numer + " Dorosly VIP]");
        }else {
            this.liczbaDzieci = 0;
            this.setName("[" + numer + " Dziecko VIP]");
        }
    }

    public boolean getVip(){
        return this.VIP;
    }

    public boolean getOplata(){
        return this.oplata;
    }

    public int getWiek(){
        return this.wiek;
    }

    public int getCzasPlywania(){
        return this.czasPlywania;
    }

    public int getJakiBasen(){
        return this.jakiBasen;
    }

    public int getLiczbaDzieci(){
        return this.liczbaDzieci;
    }

    public int getWiekDzieci(){
        if(this.liczbaDzieci > 0){
            int sum = 0;
            for(int i = 0; i < liczbaDzieci; i++){
                sum += wiekDziecka[i];
            }
            return sum;
        }else {
            return 0;
        }
    }

    public boolean getPampersJ(){
        boolean res = true;
        for(int i = 0; i < liczbaDzieci; i++){
            if(wiekDziecka[i] <= 3 && !pampersJ[i]){
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean czyWiekDzieckaBrodzik(){
        boolean res = true;
        for(int i = 0; i < liczbaDzieci; i++){
            if(wiekDziecka[i] >= 5){
                res = false;
                break;
            }
        }
        return res;
    }

    public void zmienFlagi(int basen, boolean pampers){
        this.flagBasen = basen;
        this.flagPampers = pampers;
    }

    public void zmienParametry() {
        if (flagPampers) {
            flagPampers = false;
            for (int i = 0; i < liczbaDzieci; i++) {
                if (wiekDziecka[i] <= 3) {
                    pampersJ[i] = true;
                }
            }
        }
        if (flagBasen == 0 || flagBasen == 1 || flagBasen == 2) {
            dostepneBaseny[flagBasen] = false;
            flagBasen = - 1;
            while(!dostepneBaseny[this.jakiBasen]){
                if (!dostepneBaseny[0] && !dostepneBaseny[1] && !dostepneBaseny[2]) {
                    brakOpcji = true;
                    break;
                }
                this.jakiBasen = getRandomIntNumber(0,2);
            }
        }else if(flagBasen == 3){
            flagBasen = - 1;
            this.jakiBasen = getRandomIntNumber(0,2);
        }
    }

    private int getRandomIntNumber(int min, int max) {
        return los.nextInt(max - min + 1) + min;
    }

    @Override
    public void run(){
        System.out.println(toStringUruchomiono());

        try {
            while (!czyMoze){
                if(!SYMULACJA_RESET){

                    wyswietlanie1 = 0;
                    wyswietlanie2 = 0;

                    /////////////////////////////////////////////////////////////////////////////////////////////////////////
                    while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////

                    kasa.opisKlientaVIP(this);

                    /////////////////////////////////////////////////////////////////////////////////////////////////////////
                    while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////

                    kasa.koniecOpisuKlientaVIP(this);

                    /////////////////////////////////////////////////////////////////////////////////////////////////////////
                    while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////

                    if(this.czyMoze){

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////

                        kasa.wpisNaBasenVIP(this);

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////

                        kasa.koniecWpisNaBasenVIP(this);

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////

                        if(CZAS_PLYWANIA != 0){
                            int aktualnyCzasPlywania = 0;
                            try{
                                aktualnyCzasPlywania = this.czasPlywania/CZAS_PLYWANIA;
                            }catch (ArithmeticException e){
                                aktualnyCzasPlywania = 1;
                            }

                            while(this.wyswietlanie2 < aktualnyCzasPlywania && !SYMULACJA_RESET){
                                while(SYMULACJA_BREAK){
                                    if (SYMULACJA_RESET) { return; }
                                    Thread.sleep(100);
                                }
                                if (SYMULACJA_RESET) { return; }
                                this.wyswietlanie2++;
                                Thread.sleep(1000);
                            }
                        }

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////

                        kasa.wypisZbasenuVIP(this);

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////

                        kasa.koniecWypisZbasenuVIP(this);

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                        while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }

                    if(this.brakOpcji){
                        Kasa.ileOdrzucono += 1 + this.getLiczbaDzieci();
                        dodajWynikiObslugi(String.valueOf(Kasa.ileObsluzono+Kasa.ileOdrzucono),String.valueOf(Kasa.ileObsluzono),String.valueOf(Kasa.ileOdrzucono),String.valueOf(Kasa.licznikVIP));
                        this.czyMoze = true;
                    }else { Thread.sleep(1000); }

                    /////////////////////////////////////////////////////////////////////////////////////////////////////////
                    while (SYMULACJA_BREAK || SYMULACJA_RESET || SYMULACJA_SERWIS) { if (SYMULACJA_RESET) { return; } Thread.sleep(100); }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////
                }else {
                    this.czyMoze = true;
                }
            }
        }
        catch (InterruptedException e) { }

        System.out.println(toStringZakonczono());
    }

    private String toStringUruchomiono(){
        return this.getName() + " uruchomiono!";
    }

    private String toStringZakonczono(){
        return this.getName() + " zakonczono!";
    }

    public void opisKlientVip(){

        String VIP = "TAK";
        String basen = "";
        if(this.jakiBasen == 0) { basen = "Basen Olimpijski"; }
        else if(this.jakiBasen == 1) { basen = "Basen Rekreacyjny"; }
        else if(this.jakiBasen == 2) { basen = "Brodzik dla dzieci"; }

        String oplata = "";
        if(this.czyMoze) { oplata = "Zapłacono"; }
        else { oplata = "Nie zapłacono"; }
        float cenaf = 0;
        String cena = "";
        int ileDzieciOplata = 0;

        if(this.liczbaDzieci == 2){
            for (int i = 0; i < this.liczbaDzieci; i++){ if(this.wiekDziecka[i] >= 10) { ileDzieciOplata++; } }

            String pampers1 = "";
            if(this.pampersJ[0]) { pampers1 = "TAK"; }else { pampers1 = "NIE"; }
            String pampers2 = "";
            if(this.pampersJ[1]) { pampers2 = "TAK"; }else { pampers2 = "NIE"; }

            cenaf = this.czasPlywania*0.25f + (ileDzieciOplata*(this.czasPlywania*0.2f));
            cena = String.valueOf(df.format(cenaf));
            dodajCene(cena);

            KlientPanel.dodajOpis("KLIENT: " + this.getName(),"WIEK: " + String.valueOf(this.wiek), "CZAS PLYWANIA: " + String.valueOf(this.czasPlywania), "JAKI BASEN: " + basen, "CZY VIP: " + VIP, "OPLATA: " + oplata, "LICZBA DZIECI: " + String.valueOf(this.liczbaDzieci),"DZIECKO 1\nDziecko VIP 1 --> " + this.getName(), "\tWIEK: " + String.valueOf(this.wiekDziecka[0]), "\tPAMPERS: " + pampers1, "DZIECKO2\nDziecko VIP 2 --> " + this.getName(), "\tWIEK: " + String.valueOf(this.wiekDziecka[1]), "\tPAMPERS: " + pampers2 );
        }else if(this.liczbaDzieci == 1){

            String pampers1 = "";
            if(this.pampersJ[0]) { pampers1 = "TAK"; }else { pampers1 = "NIE"; }

            for (int i = 0; i < this.liczbaDzieci; i++){ if(this.wiekDziecka[i] >= 10) { ileDzieciOplata++; } }
            cenaf = this.czasPlywania*0.25f + (ileDzieciOplata*(this.czasPlywania*0.2f));
            cena = String.valueOf(df.format(cenaf));
            dodajCene(cena);
            KlientPanel.dodajOpis("KLIENT: " + this.getName(),"WIEK: " + String.valueOf(this.wiek), "CZAS PLYWANIA: " + String.valueOf(this.czasPlywania), "JAKI BASEN: " + basen, "CZY VIP: " + VIP, "OPLATA: " + oplata, "LICZBA DZIECI: " + String.valueOf(this.liczbaDzieci),"DZIECKO 1:\nDziecko VIP 1 --> " + this.getName(), "\tWIEK: " + String.valueOf(this.wiekDziecka[0]), "\tPAMPERS: " + pampers1, "", "", "");
        }else {
            cenaf = this.getCzasPlywania()*0.25f;
            cena = String.valueOf(df.format(cenaf));
            dodajCene(cena);
            KlientPanel.dodajOpis("KLIENT: " + this.getName(),"WIEK: " + String.valueOf(this.wiek), "CZAS PLYWANIA: " + String.valueOf(this.czasPlywania), "JAKI BASEN: " + basen, "CZY VIP: " + VIP, "OPLATA: " + oplata, "LICZBA DZIECI: " + String.valueOf(this.liczbaDzieci),"", "", "", "", "", "");
        }
    }

    public void wpisNaBasen(){
        if(this.jakiBasen == 0){
            this.basenOlimpijski.dodajKlientaOlimp();
            this.basenOlimpijskiPanel.dodajOpisBasenuOlimp(getName() + " zaczyna pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    this.basenOlimpijski.dodajKlientaOlimp();
                    this.basenOlimpijskiPanel.dodajOpisBasenuOlimp("Dziecko VIP" + (i + 1) + "-->" + this.getName() + " zaczyna pływać!");
                }
            }
        }else if(this.jakiBasen == 1){
            this.basenRekreacyjny.dodajKlientaRek(this.wiek);
            this.basenRekreacyjnyPanel.dodajOpisBasenuRek(getName() + " zaczyna pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    this.basenRekreacyjny.dodajKlientaRek(this.wiekDziecka[i]);
                    this.basenRekreacyjnyPanel.dodajOpisBasenuRek("Dziecko VIP" + (i + 1) + "-->" + this.getName() + " zaczyna pływać!");
                }
            }
        }else if(this.jakiBasen == 2){
            this.brodzik.dodajKlientaBro();
            this.brodzikPanel.dodajOpisBasenuBro(getName() + " zaczyna pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    this.brodzik.dodajKlientaBro();
                    this.brodzikPanel.dodajOpisBasenuBro("Dziecko VIP" + (i + 1) + "-->" + this.getName() + " zaczyna pływać!");
                }
            }
        }
    }

    public void wypisZBasenu(){
        if(this.jakiBasen == 0){
            this.basenOlimpijski.usunKlientaOlimp();
            this.basenOlimpijskiPanel.dodajOpisBasenuOlimp("\t" + getName() + " kończy pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    this.basenOlimpijski.usunKlientaOlimp();
                    this.basenOlimpijskiPanel.dodajOpisBasenuOlimp("\tDziecko VIP " + (i + 1) + "-->" + this.getName() + " kończy pływać!");
                }
            }
        }else if(this.jakiBasen == 1){
            this.basenRekreacyjny.usunKlientaRek(this.wiek);
            this.basenRekreacyjnyPanel.dodajOpisBasenuRek("\t" + getName() + " kończy pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    basenRekreacyjny.usunKlientaRek(this.wiekDziecka[i]);
                    this.basenRekreacyjnyPanel.dodajOpisBasenuRek("\tDziecko VIP " + (i + 1) + "-->" + this.getName() + " kończy pływać!");
                }
            }
        }else if(this.jakiBasen == 2){
            this.brodzik.usunKlientaBro();
            this.brodzikPanel.dodajOpisBasenuBro("\t" + getName() + " kończy pływać!");
            if(this.liczbaDzieci > 0){
                for(int i = 0; i < liczbaDzieci; i++){
                    this.brodzik.usunKlientaBro();
                    this.brodzikPanel.dodajOpisBasenuBro("\tDziecko VIP " + (i + 1) + "-->" + this.getName() + " kończy pływać!");
                }
            }
        }
    }
}
