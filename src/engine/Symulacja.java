package engine;

import engine.basen.BasenOlimpijski;
import engine.basen.BasenRekreacyjny;
import engine.basen.Brodzik;
import engine.klient.GeneratorKlient;
import engine.klient.Klient;
import engine.klient.KlientVIP;
import gui.AppPanel;
import gui.kasjer.KasjerPanel;
import gui.klient.KlientPanel;
import gui.parametryzacja.ParametryzacjaSymulacjaPanel;
import gui.symulacja.BasenOlimpijskiPanel;
import gui.symulacja.BasenRekreacyjnyPanel;
import gui.symulacja.BrodzikPanel;
import gui.symulacja.SymulacjaPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Symulacja extends Thread{

    public static BasenOlimpijski basenOlimpijski = new BasenOlimpijski();
    public static BasenRekreacyjny basenRekreacyjny = new BasenRekreacyjny();
    public static Brodzik brodzik = new Brodzik();

    private static Kasa kasa;

    public static volatile boolean SYMULACJA_END = false;
    public static volatile boolean SYMULACJA_BREAK = false;
    public static volatile boolean SYMULACJA_RESET = true;
    public static volatile boolean SYMULACJA_SERWIS = false;

    public static int ILE_KLIENTOW_POCZAT = 0;
    public static int ILE_KLIENTOW = 0;
    public static int CZAS_GENEROWANIA = 0;
    public static int CZAS_WYSWIETLANIA = 0;
    public static int CZAS_SERWISU = 0;
    public static int CZAS_PLYWANIA = 0;

    public static int liczbaV = 0;
    public static int liczbaVP = 0;
    public static int liczbaN = 0;
    public static int liczbaNP = 0;

    public static int tmpV = 0;
    private static int tmpVP = 0;

    public static int tmpN = 0;
    private static int tmpNP = 0;

    public static int liczbaDoroslych = 0;
    public static int liczbaDzieciDoroslych = 0;
    public static int liczbaDzieci = 0;

    public static int liczbaDoroslychV = 0;
    public static int liczbaDzieciDoroslychV = 0;
    public static int liczbaDzieciV = 0;

    public static int liczbaDoroslychN = 0;
    public static int liczbaDzieciDoroslychN = 0;
    public static int liczbaDzieciN = 0;

    public static int ileWylosowanych = 0;

    private static List<Klient> klienci = new ArrayList<>();
    private static List<KlientVIP> klienciVIP = new ArrayList<>();

    public static Kasjer kasjer;
    public static GeneratorKlient generatorKlient;

    static Random los = new Random();

    public Symulacja(){
        makeThreads();
    }

    public static void makeThreads(){
        kasa = new Kasa();
        kasjer = new Kasjer();
        kasjer.setName("To ja Kasjer!");
        generatorKlient = new GeneratorKlient();
        generatorKlient.setName("To ja Generator Klientow!");
    }

    public static void nowiKlienci() {

        if (ILE_KLIENTOW > 0) {

            liczbaN += getRandomIntNumber(0,(int)((ILE_KLIENTOW - ileWylosowanych)*0.7));

            if((liczbaN - liczbaNP) > 0){

                tmpN += (liczbaN - liczbaNP);
                int temp1 = tmpNP;
                int wartoscGet1;
                while( temp1 < tmpN ){
                    ileWylosowanych++;
                    klienci.add(new Klient((temp1 + 1), temp1, ileWylosowanych, kasa, basenOlimpijski, SymulacjaPanel.basenOlimpijskiSymulacja, basenRekreacyjny, SymulacjaPanel.basenRekreacyjnySymulacja, brodzik, SymulacjaPanel.brodzikSymulacja));
                    if(klienci.get(temp1).getWiek() >= 18){
                        liczbaDoroslychN++;
                        if(klienci.get(temp1).getLiczbaDzieci() > 0){
                            wartoscGet1 = klienci.get(temp1).getLiczbaDzieci();
                            liczbaDzieciDoroslychN += wartoscGet1;
                            ileWylosowanych += wartoscGet1;
                            tmpN -= wartoscGet1;
                        }
                    }else {
                        liczbaDzieciN++;
                    }
                    temp1++;
                }
            }

            if (ILE_KLIENTOW - ileWylosowanych > 0) {

                liczbaV += getRandomIntNumber(0,(ILE_KLIENTOW - ileWylosowanych));

                if(liczbaV - liczbaVP > 0){
                    tmpV += (liczbaV - liczbaVP);
                    int temp2 = tmpVP;
                    int wartoscGet2;
                    while( temp2 < tmpV ){
                        ileWylosowanych++;

                        klienciVIP.add(new KlientVIP((temp2 + 1), temp2, ileWylosowanych, kasa, basenOlimpijski, SymulacjaPanel.basenOlimpijskiSymulacja, basenRekreacyjny, SymulacjaPanel.basenRekreacyjnySymulacja, brodzik, SymulacjaPanel.brodzikSymulacja));

                        if(klienciVIP.get(temp2).getWiek() >= 18){

                            liczbaDoroslychV++;

                            if(klienciVIP.get(temp2).getLiczbaDzieci() > 0){
                                wartoscGet2 = klienciVIP.get(temp2).getLiczbaDzieci();
                                liczbaDzieciDoroslychV += wartoscGet2;
                                ileWylosowanych += wartoscGet2;
                                tmpV -= wartoscGet2;
                            }

                        }else {
                            liczbaDzieciV++;
                        }

                        temp2++;
                    }
                    Kasa.getVipLicznik();
                }
            }

            liczbaDoroslych = liczbaDoroslychN + liczbaDoroslychV;
            liczbaDzieciDoroslych = liczbaDzieciDoroslychN + liczbaDzieciDoroslychV;
            liczbaDzieci = liczbaDzieciN + liczbaDzieciV;
            ParametryzacjaSymulacjaPanel.dodajWynikiLos(String.valueOf(ILE_KLIENTOW),String.valueOf(ileWylosowanych),String.valueOf(liczbaN),String.valueOf(liczbaDoroslychN),String.valueOf(liczbaDzieciDoroslychN),String.valueOf(liczbaDzieciN),String.valueOf(liczbaV),String.valueOf(liczbaDoroslychV),String.valueOf(liczbaDzieciDoroslychV),String.valueOf(liczbaDzieciV));
            ParametryzacjaSymulacjaPanel.dodajWynikiObslugi(String.valueOf(Kasa.ileObsluzono+Kasa.ileOdrzucono),String.valueOf(Kasa.ileObsluzono),String.valueOf(Kasa.ileOdrzucono),String.valueOf(Kasa.licznikVIP));
            AppPanel.redo();

            System.out.println("*******************************************************" +
                    "\nILE KLIENTOW MAX: " + ILE_KLIENTOW +
                    "\nWylosowano: " + ileWylosowanych +
                    "\n*******************************************************" +
                    "\nNIE VIP\tDoroslych: " + liczbaDoroslychN +
                    "\n\tDzieci doroslych: " + liczbaDzieciDoroslychN +
                    "\n\tDzieci: " + liczbaDzieciN +
                    "\n*******************************************************" +
                    "\nVIP\tDorosliVIP: " + liczbaDoroslychV +
                    "\n\tDzieci DoroslychVIP: " + liczbaDzieciDoroslychV +
                    "\n\tDzieciVIP: " + liczbaDzieciV +
                    "\n*******************************************************" +
                    "\nIle nieVip: " + liczbaN +
                    "\nIle VIP: " + liczbaV +
                    "\n*******************************************************" +
                    "\nDoroslych razem: " + liczbaDoroslych +
                    "\nDzieci Doroslych razem: " + liczbaDzieciDoroslych +
                    "\nDzieci razem: " + liczbaDzieci +
                    "\n*******************************************************");
        }
    }

    public static void startThreads(){

            for(int i = tmpVP; i < tmpV; i++){
                klienciVIP.get(i).start();
            }
            liczbaVP = liczbaV;
            tmpVP = tmpV;

            for(int i = tmpNP; i < tmpN; i++){
                klienci.get(i).start();
            }
            liczbaNP = liczbaN;
            tmpNP = tmpN;

    }

    public static void stopThreads(){

        generatorKlient.interrupt();

        kasjer.interrupt();

        for (int i = 0; i < tmpV; i++){
            klienciVIP.get(0).interrupt();
            klienciVIP.remove(0);
        }

        for (int i = 0; i < tmpN; i++){
            klienci.get(0).interrupt();
            klienci.remove(0);
        }

        basenOlimpijski.redo();
        basenRekreacyjny.redo();
        brodzik.redo();

        ILE_KLIENTOW_POCZAT = 0;
        ILE_KLIENTOW = 0;
        CZAS_GENEROWANIA = 0;
        CZAS_WYSWIETLANIA = 0;
        CZAS_SERWISU = 0;
        CZAS_PLYWANIA = 0;

        liczbaV = 0;
        liczbaVP = 0;
        liczbaN = 0;
        liczbaNP = 0;

        tmpV = 0;
        tmpVP = 0;

        tmpN = 0;
        tmpNP = 0;

        liczbaDoroslych = 0;
        liczbaDzieciDoroslych = 0;
        liczbaDzieci = 0;

        liczbaDoroslychV = 0;
        liczbaDzieciDoroslychV = 0;
        liczbaDzieciV = 0;

        liczbaDoroslychN = 0;
        liczbaDzieciDoroslychN = 0;
        liczbaDzieciN = 0;

        ileWylosowanych = 0;


        BasenOlimpijski.ileNaBasenie = 0;
        BasenRekreacyjny.ileNaBasenie = 0;
        BasenRekreacyjny.wiek = 0;
        Brodzik.ileNaBasenie = 0;

        KasjerPanel.czyszczenieBledu();
        KlientPanel.wyczyscCene();
        KlientPanel.wyczyscOpis();
        ParametryzacjaSymulacjaPanel.wyczyscWynikiLos();
        ParametryzacjaSymulacjaPanel.wyczyscWynikiObslugi();
        BasenOlimpijskiPanel.wyczyscOpisBasenuOlimp();
        BasenRekreacyjnyPanel.wyczyscOpisBasenuRek();
        BrodzikPanel.wyczyscOpisBasenuBro();

        System.out.println("*******************************************************" +
                "\nILE KLIENTOW MAX: " + ILE_KLIENTOW +
                "\nWylosowano: " + ileWylosowanych +
                "\n*******************************************************" +
                "\nNIE VIP\tDoroslych: " + liczbaDoroslychN +
                "\n\tDzieci doroslych: " + liczbaDzieciDoroslychN +
                "\n\tDzieci: " + liczbaDzieciN +
                "\n*******************************************************" +
                "\nVIP\tDorosliVIP: " + liczbaDoroslychV +
                "\n\tDzieci DoroslychVIP: " + liczbaDzieciDoroslychV +
                "\n\tDzieciVIP: " + liczbaDzieciV +
                "\n*******************************************************" +
                "\nIle nieVip: " + liczbaN +
                "\nIle VIP: " + liczbaV +
                "\n*******************************************************" +
                "\nDoroslych razem: " + liczbaDoroslych +
                "\nDzieci Doroslych razem: " + liczbaDzieciDoroslych +
                "\nDzieci razem: " + liczbaDzieci +
                "\n*******************************************************");
    }

    public static String wynikiLosowania(){
        String result = "*******************************************************" + "\nILE KLIENTOW MAX: " + ILE_KLIENTOW + "\nWylosowano: " + ileWylosowanych + "\n*******************************************************" + "\nNIE VIP\tDoroslych: " + liczbaDoroslychN + "\n\tDzieci doroslych: " + liczbaDzieciDoroslychN + "\n\tDzieci: " + liczbaDzieciN + "\n*******************************************************" + "\nVIP\tDorosliVIP: " + liczbaDoroslychV + "\n\tDzieci DoroslychVIP: " + liczbaDzieciDoroslychV + "\n\tDzieciVIP: " + liczbaDzieciV + "\n*******************************************************" + "\nIle nieVip: " + liczbaN + "\nIle VIP: " + liczbaV + "\n*******************************************************" + "\nDoroslych razem: " + liczbaDoroslych + "\nDzieci Doroslych razem: " + liczbaDzieciDoroslych + "\nDzieci razem: " + liczbaDzieci + "\n*******************************************************";
        return result;
    }
    public static String wynikiObslugi(){
        String result = "Obsłużono " + (Kasa.ileOdrzucono + Kasa.ileObsluzono) + " osob:\n1) Przyjeto= " + Kasa.ileObsluzono + "\n2) Odrzucono= " + Kasa.ileOdrzucono + "\n3) Pozostało VIP= " + Kasa.licznikVIP;
        return result;
    }

    private static int getRandomIntNumber(int min, int max) {
        return los.nextInt(max - min + 1) + min;
    }

    @Override
    public void run(){    }
}
