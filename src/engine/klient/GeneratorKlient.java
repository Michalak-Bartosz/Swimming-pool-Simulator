package engine.klient;

import engine.Symulacja;

import static engine.Symulacja.*;

public class GeneratorKlient extends Thread{

    private int generowanie = 0;

    public void generuj(){
            Symulacja.nowiKlienci();
            Symulacja.startThreads();
            ILE_KLIENTOW += ILE_KLIENTOW_POCZAT;
            if(ILE_KLIENTOW == 10000){
                ILE_KLIENTOW_POCZAT = 0;
            }
    }

    @Override
    public void run() {
        System.out.println("\n<><><>To ja generator klientów!<><><>\n\n");
        try {
            while (!Symulacja.SYMULACJA_END) {
                while (!Symulacja.SYMULACJA_BREAK && !Symulacja.SYMULACJA_RESET && ileWylosowanych != 10000) {
                        generowanie = 0;
                        generuj();
                    while (generowanie < Symulacja.CZAS_GENEROWANIA ) {
                        while (Symulacja.SYMULACJA_BREAK) {
                            Thread.sleep(100);
                            if(SYMULACJA_RESET || ileWylosowanych == 10000){ break; }
                        }
                        if(SYMULACJA_RESET || ileWylosowanych == 10000){ break; }
                        generowanie++;
                        Thread.sleep(1000);
                    }
                }
                Thread.sleep(500);
            }
        }catch (InterruptedException e) {}
    }
}
