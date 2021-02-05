package gui.klient;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KlientPanel extends JPanel {

    private JPanel opisKlienta;
    private JLabel oplata;
    private JLabel zloty;

    private static JTextArea opisText1;
    private static JTextArea opisText2;
    private static JTextArea opisText3;
    private static JTextArea opisText4;
    private static JTextArea opisText5;
    private static JTextArea opisText6;
    private static JTextArea opisText7;
    private static JTextArea opisText8;
    private static JTextArea opisText9;
    private static JTextArea opisText10;
    private static JTextArea opisText11;
    private static JTextArea opisText12;
    private static JTextArea opisText13;

    private static JTextArea cena;

    public KlientPanel(){
        setBorder(new TitledBorder(new EtchedBorder(), "Panel Klienta"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(new Color(30,30,30));
        setLayout(new FlowLayout());
        int width = 380;
        int height = 650;
        setPreferredSize(new Dimension(width, height));
        setGUI();
        addGUI();
    }

    private void setGUI() {

        Font font1 = new Font("Arial",Font.BOLD,20);
        Font font2 = new Font("Calibri",Font.BOLD,25);
        Font font3 = new Font("Calibri",Font.BOLD,17);

        opisKlienta = new JPanel();
        opisKlienta.setBorder(new TitledBorder(new EtchedBorder(),"Opis Wylosowanego Klienta"));
        ((javax.swing.border.TitledBorder) opisKlienta.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) opisKlienta.getBorder()).setTitleColor(Color.WHITE);
        opisKlienta.setLayout(new GridLayout(13,0,2,3));
        opisKlienta.setBackground(Color.BLACK);

        opisText1 = new JTextArea(1,28);opisText1.setFocusable(false);opisText1.setLineWrap(true);opisText1.setFont(font2);opisText1.setForeground(Color.ORANGE);
        opisText2 = new JTextArea(1,28);opisText2.setFocusable(false);opisText2.setLineWrap(true);opisText2.setFont(font1);
        opisText3 = new JTextArea(1,28);opisText3.setFocusable(false);opisText3.setLineWrap(true);opisText3.setFont(font1);
        opisText4 = new JTextArea(1,28);opisText4.setFocusable(false);opisText4.setLineWrap(true);opisText4.setFont(font1);
        opisText5 = new JTextArea(1,28);opisText5.setFocusable(false);opisText5.setLineWrap(true);opisText5.setFont(font1);
        opisText6 = new JTextArea(1,28);opisText6.setFocusable(false);opisText6.setLineWrap(true);opisText6.setFont(font1);
        opisText7 = new JTextArea(1,28);opisText7.setFocusable(false);opisText7.setLineWrap(true);opisText7.setFont(font1);
        opisText8 = new JTextArea(2,28);opisText8.setFocusable(false);opisText8.setLineWrap(true);opisText8.setFont(font3);opisText8.setForeground(Color.ORANGE);
        opisText9 = new JTextArea(1,28);opisText9.setFocusable(false);opisText9.setLineWrap(true);opisText9.setFont(font1);
        opisText10 = new JTextArea(1,28);opisText10.setFocusable(false);opisText10.setLineWrap(true);opisText10.setFont(font1);
        opisText11 = new JTextArea(2,28);opisText11.setFocusable(false);opisText11.setLineWrap(true);opisText11.setFont(font3);opisText11.setForeground(Color.ORANGE);
        opisText12 = new JTextArea(1,28);opisText12.setFocusable(false);opisText12.setLineWrap(true);opisText12.setFont(font1);
        opisText13 = new JTextArea(1,28);opisText13.setFocusable(false);opisText13.setLineWrap(true);opisText13.setFont(font1);

        opisKlienta.add(opisText1);
        opisKlienta.add(opisText2);
        opisKlienta.add(opisText3);
        opisKlienta.add(opisText4);
        opisKlienta.add(opisText5);
        opisKlienta.add(opisText6);
        opisKlienta.add(opisText7);
        opisKlienta.add(opisText8);
        opisKlienta.add(opisText9);
        opisKlienta.add(opisText10);
        opisKlienta.add(opisText11);
        opisKlienta.add(opisText12);
        opisKlienta.add(opisText13);

        opisKlienta.setPreferredSize(new Dimension(370,580));

        oplata = new JLabel("Do zaplacenia: ");oplata.setFont(font2);oplata.setForeground(new Color(51,153,255));oplata.setFocusable(false);
        cena = new JTextArea(1,4);cena.setFocusable(false);cena.setFont(font1);
        zloty = new JLabel("zł");zloty.setFont(font2);zloty.setForeground(new Color(51,153,255));zloty.setFocusable(false);

        this.add(oplata);
        this.add(cena);
        this.add(zloty);


    }

    private void addGUI() {
        this.add(opisKlienta);
    }

    public static void dodajCene(String cen){ cena.setText(cen); }
    public static void wyczyscCene() { cena.setText(""); }
    public static void dodajOpis(String nDorosly, String wDorosly, String czasDorosly, String bDorosly, String vip, String oplata, String liczbaDzieci, String nDziecko1, String wDziecko1, String pDziecko1, String nDziecko2, String wDziecko2, String pDziecko2) {

        opisText1.setText(nDorosly);
        opisText2.setText(wDorosly);
        opisText3.setText(czasDorosly);
        opisText4.setText(bDorosly);
        opisText5.setText(vip);
        opisText6.setText(oplata);
        opisText7.setText(liczbaDzieci);
        opisText8.setText(nDziecko1);
        opisText9.setText(wDziecko1);
        opisText10.setText(pDziecko1);
        opisText11.setText(nDziecko2);
        opisText12.setText(wDziecko2);
        opisText13.setText(pDziecko2);

    }

    public static void wyczyscOpis() {

        opisText1.setText("");
        opisText2.setText("");
        opisText3.setText("");
        opisText4.setText("");
        opisText5.setText("");
        opisText6.setText("");
        opisText7.setText("");
        opisText8.setText("");
        opisText9.setText("");
        opisText10.setText("");
        opisText11.setText("");
        opisText12.setText("");
        opisText13.setText("");

    }

}
