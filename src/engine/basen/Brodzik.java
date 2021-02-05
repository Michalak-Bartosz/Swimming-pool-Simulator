package engine.basen;

import static gui.symulacja.SymulacjaPanel.brodzikProgres;

public class Brodzik {

    public static final int MAX_Brodzik = 20;
    public static int ileNaBasenie = 0;

    public int getIleNaBasenie(){
        return ileNaBasenie;
    }

    synchronized public void dodajKlientaBro(){
        ileNaBasenie++;
        brodzikProgres.setValue(ileNaBasenie);
        brodzikProgres.repaint();
    }

    synchronized public static void usunKlientaBro(){
        ileNaBasenie--;
        brodzikProgres.setValue(ileNaBasenie);
        brodzikProgres.repaint();
    }

    synchronized public void redo(){
        this.ileNaBasenie = 0;
        brodzikProgres.setValue(ileNaBasenie);
        brodzikProgres.repaint();
    }
}
