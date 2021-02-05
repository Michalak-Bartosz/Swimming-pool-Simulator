package engine;

import engine.basen.BasenOlimpijski;
import engine.basen.BasenRekreacyjny;
import engine.basen.Brodzik;
import engine.klient.Klient;
import engine.klient.KlientVIP;
import gui.symulacja.BasenOlimpijskiPanel;
import gui.symulacja.BasenRekreacyjnyPanel;
import gui.symulacja.BrodzikPanel;

import static engine.Symulacja.*;
import static gui.kasjer.KasjerPanel.obslugaBledu;

public class Kasjer extends Thread {

    public Kasjer(){
    }

    @Override
    public void run(){
        System.out.println("\n<<<<<<" + this.getName() + ">>>>>>\n\n");
        try {
            while (!SYMULACJA_END) {
                if(SYMULACJA_SERWIS){
                    System.out.println("Rozpoczeto procedurę Serwis");
                    BasenOlimpijskiPanel.dodajOpisBasenuOlimp("[ROZPOCZETO PROCEDURE SERWIS]");
                    BasenRekreacyjnyPanel.dodajOpisBasenuRek("[ROZPOCZETO PROCEDURE SERWIS]");
                    BrodzikPanel.dodajOpisBasenuBro("[ROZPOCZETO PROCEDURE SERWIS]");

                    if(basenOlimpijski.getIleNaBasenie() > 0 ||
                       basenRekreacyjny.getIleNaBasenie() > 0 ||
                       brodzik.getIleNaBasenie() > 0){
                        BasenOlimpijskiPanel.dodajOpisBasenuOlimp("[OCZEKIWANIE NA WYJSCIE WSZYSTKICH KLIENTOW]");
                        BasenRekreacyjnyPanel.dodajOpisBasenuRek("[OCZEKIWANIE NA WYJSCIE WSZYSTKICH KLIENTOW]");
                        BrodzikPanel.dodajOpisBasenuBro("[OCZEKIWANIE NA WYJSCIE WSZYSTKICH KLIENTOW]");
                    }

                    while(SYMULACJA_BREAK ||
                          basenOlimpijski.getIleNaBasenie() > 0 ||
                          basenRekreacyjny.getIleNaBasenie() > 0 ||
                          brodzik.getIleNaBasenie() > 0){if(SYMULACJA_RESET){ break; }Thread.sleep(100);}if(SYMULACJA_RESET){ break; }

                    BasenOlimpijskiPanel.dodajOpisBasenuOlimp(">>>ROZPOCZETO SERWIS BASENU!>>>");
                    BasenRekreacyjnyPanel.dodajOpisBasenuRek(">>>ROZPOCZETO SERWIS BASENU!>>>");
                    BrodzikPanel.dodajOpisBasenuBro(">>>ROZPOCZETO SERWIS BASENU!>>>");

                    int licznik1 = 0;
                    while (licznik1 < CZAS_SERWISU || SYMULACJA_BREAK){
                        if(SYMULACJA_RESET){ break; }
                        Thread.sleep(500);
                        licznik1 ++;
                    }if(SYMULACJA_RESET){ break; }

                    BasenOlimpijskiPanel.dodajOpisBasenuOlimp("[...SERWIS TRWA]");
                    BasenRekreacyjnyPanel.dodajOpisBasenuRek("[...SERWIS TRWA]");
                    BrodzikPanel.dodajOpisBasenuBro("[...SERWIS TRWA]");

                    licznik1 = 0;
                    while (licznik1 < CZAS_SERWISU || SYMULACJA_BREAK){
                        if(SYMULACJA_RESET){ break; }
                        Thread.sleep(500);
                        licznik1 ++;
                    }if(SYMULACJA_RESET){ break; }

                    BasenOlimpijskiPanel.dodajOpisBasenuOlimp("<<<SERWIS ZAKOŃCZONO!<<<");
                    BasenRekreacyjnyPanel.dodajOpisBasenuRek("<<<SERWIS ZAKOŃCZONO!<<<");
                    BrodzikPanel.dodajOpisBasenuBro("<<<SERWIS ZAKOŃCZONO!<<<");
                    SYMULACJA_SERWIS = false;
                }
                while (SYMULACJA_BREAK) { if(SYMULACJA_RESET){ break; } Thread.sleep(100); }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void sprawdzKlientaBasenOlimpijski(Klient klient){

        if (basenOlimpijski.getIleNaBasenie() == BasenOlimpijski.MAX_BasenOlimp) {
            if(CZAS_WYSWIETLANIA != 0) {
                bladMaxBasenOlim();
            }
            klient.zmienFlagi(0, false);
            klient.czyMoze = false;
        }else if(klient.getWiek() >= 18 && klient.getLiczbaDzieci() == 0 ) {
            if(CZAS_WYSWIETLANIA != 0) {
                nieMaBledu();
            }
            klient.czyMoze = true;
        }else if(klient.getLiczbaDzieci() > 0) {
            if(CZAS_WYSWIETLANIA != 0) {
                bladWiekDzieckoBasenOlimp();
            }
            klient.zmienFlagi(0, false);
            klient.czyMoze = false;
        } else if(klient.getWiek() >= 10){
            if(CZAS_WYSWIETLANIA != 0) {
                bladWiekDzieckoBasenOlimp();
            }
            klient.zmienFlagi(0,false);
            klient.czyMoze = false;
        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klient.brakOpcji = true;
            klient.czyMoze = false;
        }

    }

    public static void sprawdzKlientaBasenRekreacyjny(Klient klient){

        if(klient.getWiek() >= 10){
            if(basenRekreacyjny.getIleNaBasenie() == BasenRekreacyjny.MAX_BasenRek){
                if(CZAS_WYSWIETLANIA != 0) {
                    bladMaxBasenRek();
                }
                klient.zmienFlagi(1, false);
                klient.czyMoze = false;
            }else if(basenRekreacyjny.getSredniWiek(klient.getWiek() + klient.getWiekDzieci(), 1 + klient.getLiczbaDzieci()) > 40){
                if(CZAS_WYSWIETLANIA != 0) {
                    bladWiekBasenRek();
                }
                klient.zmienFlagi(1, false);
                klient.czyMoze = false;
            }else {
                if(CZAS_WYSWIETLANIA != 0) {
                    nieMaBledu();
                }
                klient.czyMoze = true;
            }
        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klient.brakOpcji = true;
            klient.czyMoze = false;
        }

    }

    public static void sprawdzKlientaBrodzik(Klient klient){

        if(klient.getWiek() >= 10){
            if (brodzik.getIleNaBasenie() == Brodzik.MAX_Brodzik) {
                if(CZAS_WYSWIETLANIA != 0) {
                    bladMaxBrodzik();
                }
                klient.zmienFlagi(2,false);
                klient.czyMoze = false;
            } else if(klient.getWiekDzieci() > 0) {
                if (klient.czyWiekDzieckaBrodzik()) {
                    if (klient.getPampersJ()) {
                        if(CZAS_WYSWIETLANIA != 0) {
                            nieMaBledu();
                        }
                        klient.czyMoze = true;
                    } else {
                        if(CZAS_WYSWIETLANIA != 0) {
                            bladBrakPampers();
                        }
                        klient.zmienFlagi(3, true);
                        klient.czyMoze = false;
                    }
                } else {
                    if(CZAS_WYSWIETLANIA != 0) {
                        bladWiekDzieckoBrodzik();
                    }
                    klient.zmienFlagi(2, false);
                    klient.czyMoze = false;
                }
            }else{
                if(CZAS_WYSWIETLANIA != 0) {
                    bladWiekDoroslyBrodzik();
                }
                klient.zmienFlagi(2, false);
                klient.czyMoze = false;
            }

        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klient.brakOpcji = true;
            klient.czyMoze = false;
        }

    }

    public static void sprawdzKlientaVipBasenOlimpijski(KlientVIP klientVIP){

        if (basenOlimpijski.getIleNaBasenie() == BasenOlimpijski.MAX_BasenOlimp) {
            if(CZAS_WYSWIETLANIA != 0) {
                bladMaxBasenOlim();
            }
            klientVIP.zmienFlagi(0, false);
            klientVIP.czyMoze = false;
        }else if(klientVIP.getWiek() >= 18 && klientVIP.getLiczbaDzieci() == 0 ) {
            if(CZAS_WYSWIETLANIA != 0) {
                nieMaBledu();
            }
            klientVIP.czyMoze = true;
        }else if(klientVIP.getLiczbaDzieci() > 0) {
            if(CZAS_WYSWIETLANIA != 0) {
                bladWiekDzieckoBasenOlimp();
            }
            klientVIP.zmienFlagi(0, false);
            klientVIP.czyMoze = false;
        } else if(klientVIP.getWiek() >= 10){
            if(CZAS_WYSWIETLANIA != 0) {
                bladWiekDzieckoBasenOlimp();
            }
            klientVIP.zmienFlagi(0,false);
            klientVIP.czyMoze = false;
        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klientVIP.brakOpcji = true;
            klientVIP.czyMoze = false;
        }

    }

    public static void sprawdzKlientaVipBasenRekreacyjny(KlientVIP klientVIP){

        if(klientVIP.getWiek() >= 10){
            if(basenRekreacyjny.getIleNaBasenie() == BasenRekreacyjny.MAX_BasenRek){
                if(CZAS_WYSWIETLANIA != 0) {
                    bladMaxBasenRek();
                }
                klientVIP.zmienFlagi(1, false);
                klientVIP.czyMoze = false;
            }else if(basenRekreacyjny.getSredniWiek(klientVIP.getWiek() + klientVIP.getWiekDzieci(), 1 + klientVIP.getLiczbaDzieci()) > 40){
                if(CZAS_WYSWIETLANIA != 0) {
                    bladWiekBasenRek();
                }
                klientVIP.zmienFlagi(1, false);
                klientVIP.czyMoze = false;
            }else {
                if(CZAS_WYSWIETLANIA != 0) {
                    nieMaBledu();
                }
                klientVIP.czyMoze = true;
            }
        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klientVIP.brakOpcji = true;
            klientVIP.czyMoze = false;
        }

    }

    public static void sprawdzKlientaVipBrodzik(KlientVIP klientVIP){

        if(klientVIP.getWiek() >= 10){
            if (brodzik.getIleNaBasenie() == Brodzik.MAX_Brodzik) {
                if(CZAS_WYSWIETLANIA != 0) {
                    bladMaxBrodzik();
                }
                klientVIP.zmienFlagi(2,false);
                klientVIP.czyMoze = false;
            } else if(klientVIP.getWiekDzieci() > 0) {
                if (klientVIP.czyWiekDzieckaBrodzik()) {
                    if (klientVIP.getPampersJ()) {
                        if(CZAS_WYSWIETLANIA != 0) {
                            nieMaBledu();
                        }
                        klientVIP.czyMoze = true;
                    } else {
                        if(CZAS_WYSWIETLANIA != 0) {
                            bladBrakPampers();
                        }
                        klientVIP.zmienFlagi(3, true);
                        klientVIP.czyMoze = false;
                    }
                } else {
                    if(CZAS_WYSWIETLANIA != 0) {
                        bladWiekDzieckoBrodzik();
                    }
                    klientVIP.zmienFlagi(2, false);
                    klientVIP.czyMoze = false;
                }
            }else{
                if(CZAS_WYSWIETLANIA != 0){
                    bladWiekDoroslyBrodzik();
                }
                klientVIP.zmienFlagi(2, false);
                klientVIP.czyMoze = false;
            }
        }else {
            if(CZAS_WYSWIETLANIA != 0) {
                bladBrakOpiekuna();
            }
            klientVIP.brakOpcji = true;
            klientVIP.czyMoze = false;
        }

    }

    public static void bladMaxBasenOlim(){
        obslugaBledu("+Basen Olimpijski ma maksymalna liczbe osob!");
    }

    public static void bladMaxBasenRek(){
        obslugaBledu("+Basen Rekreacyjny ma maksymalna liczbe osob!");
    }

    public static void bladMaxBrodzik(){
        obslugaBledu("+Brodzik dla dzieci ma maksymalna liczbe osob!");
    }

    public static void bladBrakPampers(){
        obslugaBledu("+Dziecko do 3 roku zycia nie ma pampersa!");
    }

    public static void bladWiekBasenRek(){
        obslugaBledu("+Średnia wieku w basenie rekreacyjnym!");
    }

    public static void bladBrakOpiekuna(){
        obslugaBledu("+Dziecko nie ma opiekuna!");
    }

    public static void bladWiekDzieckoBasenOlimp(){
        obslugaBledu("+Basen olimpijski jest dla dorosłych!");
    }

    public static void bladWiekDzieckoBrodzik(){
        obslugaBledu("+Dziecko jest za duze na brodzik!");
    }

    public static void bladWiekDoroslyBrodzik(){
        obslugaBledu("+Dorosly nie moze kąpać się w brodziku!");
    }

    public static void nieMaBledu(){
        obslugaBledu("+Wszystko jest zgodne z regulaminem!");
    }
}
