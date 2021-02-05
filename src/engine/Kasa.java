package engine;

import engine.klient.Klient;
import engine.klient.KlientVIP;
import gui.klient.KlientPanel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static engine.Symulacja.*;
import static engine.Symulacja.SYMULACJA_RESET;
import static gui.kasjer.KasjerPanel.czyszczenieBledu;
import static gui.klient.KlientPanel.wyczyscCene;
import static gui.klient.KlientPanel.wyczyscOpis;
import static gui.parametryzacja.ParametryzacjaSymulacjaPanel.dodajWynikiObslugi;

public class Kasa{

    private static AtomicInteger klienci1 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienci1 = new AtomicInteger(0);
    private static AtomicInteger klienciVIP1 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienciVIP1 = new AtomicInteger(0);

    private static AtomicInteger klienci2 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienci2 = new AtomicInteger(0);
    private static AtomicInteger klienciVIP2 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienciVIP2 = new AtomicInteger(0);

    private static AtomicInteger klienci3 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienci3 = new AtomicInteger(0);
    private static AtomicInteger klienciVIP3 = new AtomicInteger(0);
    private static AtomicInteger czekajacyKlienciVIP3 = new AtomicInteger(0);

    public static int licznikVIP = 0;
    public static int ileObsluzono = 0;
    public static int ileOdrzucono = 0;

    private Lock lock1;
    private Condition warunekVIP1;
    private Condition warunekNieVIP1;

    private Lock lock2;
    private Condition warunekVIP2;
    private Condition warunekNieVIP2;

    private Lock lock3;
    private Condition warunekVIP3;
    private Condition warunekNieVIP3;


    public Kasa() {

        lock1 = new ReentrantLock();
        warunekVIP1 = lock1.newCondition();
        warunekNieVIP1 = lock1.newCondition();

        lock2 = new ReentrantLock();
        warunekVIP2 = lock2.newCondition();
        warunekNieVIP2 = lock2.newCondition();

        lock3 = new ReentrantLock();
        warunekVIP3 = lock3.newCondition();
        warunekNieVIP3 = lock3.newCondition();
    }

    public static void redo(){
        klienci1.set(0);
        czekajacyKlienci1.set(0);
        klienciVIP1.set(0);
        czekajacyKlienciVIP1.set(0);

        klienci2.set(0);
        czekajacyKlienci2.set(0);
        klienciVIP2.set(0);
        czekajacyKlienciVIP2.set(0);

        klienci3.set(0);
        czekajacyKlienci3.set(0);
        klienciVIP3.set(0);
        czekajacyKlienciVIP3.set(0);

        licznikVIP = 0;
        ileObsluzono = 0;
        ileOdrzucono = 0;
    }

    public static synchronized void getVipLicznik(){
        licznikVIP += (liczbaV - liczbaVP);
    }

    public static void stanPoResecie(){
        System.out.println( "RESET_RESET_RESET_RESET_RESET" +
                                "\nKlienci wypisujacy opis: " + klienci1 +
                                "\nCzekajacy klienci do wypisania opisu: " + czekajacyKlienci1 +
                                "\nKlienci VIP wypisujacy opis: " + klienciVIP1 +
                                "\nCzekajacy klienci VIP do wypisania opisu: " + czekajacyKlienciVIP1 +

                                "\n\nKlienci wpisujacy sie na basen: " + klienci2 +
                                "\nKlienci czekajacy do wpisania sie n basenu: " + czekajacyKlienci2 +
                                "\nKleinci VIP wpisujacy sie na basen: " + klienciVIP2 +
                                "\nKlienci VIP czekajacy do wpisania sie na basen" + czekajacyKlienciVIP2 +

                                "\n\nKlienci wypisujacy sie z baseny: " + klienci3 +
                                "\nKlienci czekajacy na wypisanie sie z basenu: " + czekajacyKlienci3 +
                                "\nKlienci VIP wypisujacy sie z basenu: " + klienciVIP3 +
                                "\nKlienci VIP czekajacy na wypisanie sie z basenu: " + czekajacyKlienciVIP3 +

                                "\n\nLicznik VIP: " + licznikVIP +
                                "\nIle obsluzono: " + ileObsluzono +
                                "\nIle odrzucono: " + ileOdrzucono +
                                "\nRESET_RESET_RESET_RESET_RESET");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void opisKlienta(Klient klient) throws InterruptedException {
        lock1.lock();

        if (klienciVIP1.get() > 0 || czekajacyKlienciVIP1.get() > 0 || licznikVIP > 0) {
            czekajacyKlienci1.incrementAndGet();
            warunekNieVIP1.await();
            czekajacyKlienci1.decrementAndGet();
        }

        klienci1.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        if(klient.getJakiBasen() == 0){
            Kasjer.sprawdzKlientaBasenOlimpijski(klient);
        }else if(klient.getJakiBasen() == 1){
            Kasjer.sprawdzKlientaBasenRekreacyjny(klient);
        }else if(klient.getJakiBasen() == 2){
            Kasjer.sprawdzKlientaBrodzik(klient);
        }

        if(CZAS_WYSWIETLANIA != 0) {

            klient.opisKlient();

            while (klient.wyswietlanie1 < CZAS_WYSWIETLANIA && !SYMULACJA_RESET) {
                while (SYMULACJA_BREAK || SYMULACJA_SERWIS) {
                    if (SYMULACJA_RESET) {
                        break;
                    }
                    Thread.sleep(100);
                }
                if (SYMULACJA_RESET) {
                    break;
                }
                klient.wyswietlanie1++;
                Thread.sleep(1000);
            }

            wyczyscOpis();
            Thread.sleep(100);
            czyszczenieBledu();
            Thread.sleep(100);
            wyczyscCene();
        }else{
            KlientPanel.dodajOpis("","Czas wyswietlania jest równy zero!","","","","","","","","","","","");
        }

        klient.zmienParametry();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock1.unlock();
    }

    public void koniecOpisuKlienta (Klient klient) throws InterruptedException{
        lock1.lock();
        klienci1.decrementAndGet();
        if (klienci1.get() == 0) {
            warunekVIP1.signalAll();
        }
        lock1.unlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void opisKlientaVIP (KlientVIP klientVIP) throws InterruptedException {
        lock1.lock();

        if (klienci1.get() > 0) {
            czekajacyKlienciVIP1.incrementAndGet();
            warunekVIP1.await();
            czekajacyKlienciVIP1.decrementAndGet();
        }
        klienciVIP1.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        if(klientVIP.getJakiBasen() == 0){
            Kasjer.sprawdzKlientaVipBasenOlimpijski(klientVIP);
        }else if(klientVIP.getJakiBasen() == 1){
            Kasjer.sprawdzKlientaVipBasenRekreacyjny(klientVIP);
        }else if(klientVIP.getJakiBasen() == 2){
            Kasjer.sprawdzKlientaVipBrodzik(klientVIP);
        }

        if(CZAS_WYSWIETLANIA != 0) {

            klientVIP.opisKlientVip();

            while (klientVIP.wyswietlanie1 < CZAS_WYSWIETLANIA && !SYMULACJA_RESET) {
                while (SYMULACJA_BREAK || SYMULACJA_SERWIS) {
                    if (SYMULACJA_RESET) {
                        break;
                    }
                    Thread.sleep(100);
                }
                if (SYMULACJA_RESET) {
                    break;
                }
                klientVIP.wyswietlanie1++;
                Thread.sleep(1000);
            }
            wyczyscOpis();
            Thread.sleep(100);
            czyszczenieBledu();
            Thread.sleep(100);
            wyczyscCene();
        }else{
            KlientPanel.dodajOpis("","Czas wyswietlania jest równy zero!","","","","","","","","","","","");
        }

        klientVIP.zmienParametry();

        if(klientVIP.brakOpcji){
            Kasa.licznikVIP -= 1 + klientVIP.getLiczbaDzieci();
        }

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock1.unlock();
    }

    public void koniecOpisuKlientaVIP (KlientVIP klientVIP) throws InterruptedException{
        lock1.lock();
        klienciVIP1.decrementAndGet();
        if ( klienciVIP1.get() == 0 ) {
            warunekNieVIP1.signalAll();
        }
        lock1.unlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void wpisNaBasenVIP (KlientVIP klientVIP) throws InterruptedException {
        lock2.lock();

        if (klienci2.get() > 0) {
            czekajacyKlienciVIP2.incrementAndGet();
            warunekVIP2.await();
            czekajacyKlienciVIP2.decrementAndGet();
        }
        klienciVIP2.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            ileObsluzono += 1 + klientVIP.getLiczbaDzieci();
            licznikVIP -= 1 + klientVIP.getLiczbaDzieci();

            dodajWynikiObslugi(String.valueOf(Kasa.ileObsluzono+Kasa.ileOdrzucono),String.valueOf(Kasa.ileObsluzono),String.valueOf(Kasa.ileOdrzucono),String.valueOf(Kasa.licznikVIP));

            klientVIP.wpisNaBasen();
            Thread.sleep(100);

                System.out.println( "/////////////////////////////////" +
                        klientVIP.getName() + " zaczyna pływać!" +
                        "/////////////////////////////////");

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock2.unlock();
    }

    public void koniecWpisNaBasenVIP (KlientVIP klientVIP) throws InterruptedException{
        lock2.lock();
        klienciVIP2.decrementAndGet();
        if ( klienciVIP2.get() == 0) {
            warunekNieVIP2.signalAll();
        }
        lock2.unlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void wypisZbasenuVIP (KlientVIP klientVIP) throws InterruptedException {
        lock3.lock();

        if (klienci3.get() > 0) {
            czekajacyKlienciVIP3.incrementAndGet();
            warunekVIP3.await();
            czekajacyKlienciVIP3.decrementAndGet();
        }
        klienciVIP3.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            klientVIP.wypisZBasenu();
            Thread.sleep(100);

                System.out.println( "|||||||||||||||||||||||||||||||||" +
                        klientVIP.getName() + " kończy pływać!" +
                        "|||||||||||||||||||||||||||||||||");

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock3.unlock();
    }

    public void koniecWypisZbasenuVIP (KlientVIP klientVIP) throws InterruptedException{
        lock3.lock();
        klienciVIP3.decrementAndGet();
        if ( klienciVIP3.get() == 0) {
            warunekNieVIP3.signalAll();
        }
        lock3.unlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void wpisNaBasen(Klient klient) throws InterruptedException {
        lock2.lock();

        if (klienciVIP2.get() > 0 || czekajacyKlienciVIP2.get() > 0) {
            czekajacyKlienci2.incrementAndGet();
            warunekNieVIP2.await();
            czekajacyKlienci2.decrementAndGet();
        }

        klienci2.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        if(klient.czyMoze){

            ileObsluzono += 1 + klient.getLiczbaDzieci();
            dodajWynikiObslugi(String.valueOf(Kasa.ileObsluzono+Kasa.ileOdrzucono),String.valueOf(Kasa.ileObsluzono),String.valueOf(Kasa.ileOdrzucono),String.valueOf(Kasa.licznikVIP));

            klient.wpisNaBasen();
            Thread.sleep(100);

                System.out.println( "/////////////////////////////////" +
                        klient.getName() + " zaczyna pływać!" +
                        "/////////////////////////////////");

        }

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock2.unlock();
    }

    public void koniecWpisNaBasen (Klient klient) throws InterruptedException{
        lock2.lock();
        klienci2.decrementAndGet();
        if (klienci2.get() == 0) {
            warunekVIP2.signalAll();
        }
        lock2.unlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void wypisZbasenu(Klient klient) throws InterruptedException {
        lock3.lock();

        if (klienciVIP3.get() > 0 || czekajacyKlienciVIP3.get() > 0) {
            czekajacyKlienci3.incrementAndGet();
            warunekNieVIP3.await();
            czekajacyKlienci3.decrementAndGet();
        }

        klienci3.incrementAndGet();

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        if(klient.czyMoze){

            klient.wypisZBasenu();
            Thread.sleep(100);

                System.out.println( "|||||||||||||||||||||||||||||||||" +
                        klient.getName() + " kończy pływać!" +
                        "|||||||||||||||||||||||||||||||||");

        }
        if(klient.brakOpcji){

            ileOdrzucono += 1 + klient.getLiczbaDzieci();
            dodajWynikiObslugi(String.valueOf(Kasa.ileObsluzono+Kasa.ileOdrzucono),String.valueOf(Kasa.ileObsluzono),String.valueOf(Kasa.ileOdrzucono),String.valueOf(Kasa.licznikVIP));
            klient.czyMoze = true;

        }

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        lock3.unlock();
    }

    public void koniecWypisZbasenu (Klient klient) throws InterruptedException{
        lock3.lock();
        klienci3.decrementAndGet();

        if (klienci3.get() == 0) {
            warunekVIP3.signalAll();
        }
        lock3.unlock();
    }

}
