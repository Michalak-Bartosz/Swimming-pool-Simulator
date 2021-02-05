package engine.basen;

import static gui.symulacja.SymulacjaPanel.basenOlimProgres;

public class BasenOlimpijski {

    public static final int MAX_BasenOlimp = 35;
    public static int ileNaBasenie = 0;

    public int getIleNaBasenie(){
        return this.ileNaBasenie;
    }

    synchronized public void dodajKlientaOlimp(){
        ileNaBasenie ++;
        basenOlimProgres.setValue(ileNaBasenie);
        basenOlimProgres.repaint();
    }

    synchronized public static void usunKlientaOlimp(){
        ileNaBasenie --;
        basenOlimProgres.setValue(ileNaBasenie);
        basenOlimProgres.repaint();
    }

    synchronized public void redo(){
        this.ileNaBasenie = 0;
        basenOlimProgres.setValue(ileNaBasenie);
        basenOlimProgres.repaint();
    }

}
