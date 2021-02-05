package engine.basen;

import gui.symulacja.SymulacjaPanel;
import java.text.DecimalFormat;
import static gui.symulacja.SymulacjaPanel.basenRekProgres;

public class BasenRekreacyjny {

    public static final int MAX_BasenRek = 45;
    public static int ileNaBasenie = 0;
    public static int wiek = 0;

    static DecimalFormat df = new DecimalFormat("#0.00");

    public int getIleNaBasenie(){
        return ileNaBasenie;
    }

    public float getSredniWiek(int wiekPlus, int ilePlus){
            return (wiek + wiekPlus)/(ileNaBasenie + ilePlus);
    }

    synchronized public void dodajKlientaRek(int w){
        ileNaBasenie ++;
        basenRekProgres.setValue(ileNaBasenie);
        basenRekProgres.repaint();
        wiek += w;
        try {
            SymulacjaPanel.sredniWiekText.setText(String.valueOf(df.format((float)(wiek/ileNaBasenie))));
        }catch (ArithmeticException e){
            SymulacjaPanel.sredniWiekText.setText("0");
        }
    }

    synchronized public static void usunKlientaRek(int w){
        ileNaBasenie --;
        basenRekProgres.setValue(ileNaBasenie);
        basenRekProgres.repaint();
        wiek -= w;
        try {
            SymulacjaPanel.sredniWiekText.setText(String.valueOf(df.format((float)(wiek/ileNaBasenie))));
        }catch (ArithmeticException e){
            SymulacjaPanel.sredniWiekText.setText("0");
        }
    }

    synchronized public void redo(){
        this.ileNaBasenie = 0;
        basenRekProgres.setValue(ileNaBasenie);
        basenRekProgres.repaint();
    }
}
