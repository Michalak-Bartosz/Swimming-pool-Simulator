package gui.parametryzacja;

import engine.Kasa;
import engine.Symulacja;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametryzacjaSymulacjaPanel extends JPanel{

    private JPanel opcjeSymulacjiKlient;
    private JPanel opcjeSymulacjiSerwis;

    private static JButton startSymulacja;
    private static JButton resetSymulacja;
    private static JButton stopSymulacja;
    private static JSpinner liczbaKlientow;
    private static JSpinner czasGenerowania;
    private static JSpinner czasWyswietlania;
    private static JSpinner czasSeriwisu;
    private static JComboBox szybkoscPlywania;
    private JLabel liczbaKlientowLabel;
    private JLabel czasGenerowaniaLabel;
    private JLabel czasWyswietlaniaLabel;
    private JLabel szybkoscPlywaniaLabel;
    private JLabel czasSerwisuLabel;
    private static JButton serwis;

    ///////////////////////////////////////////////////////////////

    private static JPanel wynikiLos;

    private static JSeparator separator1;

    private static JLabel ileMaxKlientow;
    private static JTextArea ileMaxKlientowWynik;
    private static JLabel ileWylosowano;
    private static JTextArea ileWylosowanoWynik;

    private static JSeparator separator2;

    private static JLabel klienciZwyczajni;
    private static JTextArea klienciZwyczajniWynik;

    private static JSeparator separator3;

    private static JLabel kDorosli;
    private static JTextArea kDorosliWynik;
    private static JLabel kDzieciDoroslych;
    private static JTextArea kDzieciDoroslychWynik;
    private static JLabel kDzieci;
    private static JTextArea kDzieciWynik;

    private static JSeparator separator4;

    private static JLabel klienciVIP;
    private static JTextArea klienciVIPWynik;

    private static JSeparator separator5;

    private static JLabel vDorosli;
    private static JTextArea vDorosliWynik;
    private static JLabel vDzieciDoroslych;
    private static JTextArea vDzieciDoroslychWynik;
    private static JLabel vDzieci;
    private static JTextArea vDzieciWynik;

    ///////////////////////////////////////////////////////////////

    private static JPanel wynikiObslugi;

    private static JLabel ileObsluzono;
    private static JTextArea ileObsluzonoWynik;

    private static JSeparator separator6;

    private static JLabel ilePrzyjeto;
    private static JTextArea getIlePrzyjetoWynik;

    private static JLabel ileOdrzucono;
    private static JTextArea ileOdrzuconoWynik;

    private static JSeparator separator7;

    private static JLabel licznikVip;
    private static JTextArea licznikVipWynik;

    ///////////////////////////////////////////////////////////////

    public ParametryzacjaSymulacjaPanel(){
        setBorder(new TitledBorder(new EtchedBorder(), "Opcje Symulacji"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        setLayout(new BorderLayout());
        this.setBackground(new Color(30,30,30));
        int width = 450;
        int height = 650;
        setPreferredSize(new Dimension(width, height));
        setGUI();
        addListeners();
        addGUI();
    }

    private void setGUI(){

        Font font1 = new Font("Arial",Font.BOLD,15);
        Font font2 = new Font("Calibri",Font.BOLD,30);
        Font font3 = new Font("Calibri",Font.BOLD,18);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        opcjeSymulacjiKlient = new JPanel();
        opcjeSymulacjiKlient.setBorder(new TitledBorder(new EtchedBorder(),"Opcje Losowania Klientów"));
        opcjeSymulacjiKlient.setLayout(new BorderLayout());
        ((javax.swing.border.TitledBorder) opcjeSymulacjiKlient.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) opcjeSymulacjiKlient.getBorder()).setTitleColor(Color.WHITE);
        opcjeSymulacjiKlient.setBackground(Color.BLACK);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel przyciski = new JPanel();
        przyciski.setBackground(Color.BLACK);
        przyciski.setLayout(new FlowLayout());
        przyciski.setPreferredSize(new Dimension(400,40));
        startSymulacja = new JButton("START");startSymulacja.setFont(font3);
        stopSymulacja = new JButton("STOP");stopSymulacja.setFont(font3);
        resetSymulacja = new JButton("RESET");resetSymulacja.setFont(font3);
        przyciski.add(startSymulacja,FlowLayout.LEFT);
        przyciski.add(stopSymulacja,FlowLayout.CENTER);
        przyciski.add(resetSymulacja,FlowLayout.RIGHT);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel spinnery = new JPanel();
        spinnery.setLayout(new FlowLayout());
        spinnery.setPreferredSize(new Dimension(400,120));
        spinnery.setBackground(Color.BLACK);

        SpinnerModel model1 = new SpinnerNumberModel(50,1,100,1);
        SpinnerModel model2 = new SpinnerNumberModel(100,0,500,1);
        SpinnerModel model3 = new SpinnerNumberModel(2,0,5,1);

        liczbaKlientow = new JSpinner(model1);
        liczbaKlientowLabel = new JLabel("Max liczba generowanych klientów");liczbaKlientowLabel.setFocusable(false);liczbaKlientowLabel.setFont(font1);liczbaKlientowLabel.setForeground(Color.WHITE);
        czasGenerowania = new JSpinner(model2);
        czasGenerowaniaLabel = new JLabel("Co jaki czas generować klientów (sek)");czasGenerowaniaLabel.setFocusable(false);czasGenerowaniaLabel.setFont(font1);czasGenerowaniaLabel.setForeground(Color.WHITE);
        czasWyswietlania = new JSpinner(model3);
        czasWyswietlaniaLabel = new JLabel("Czas wyswietlania opisu klienta (sek)");czasWyswietlaniaLabel.setFocusable(false);czasWyswietlaniaLabel.setFont(font1);czasWyswietlaniaLabel.setForeground(Color.WHITE);
        String[] plywanie = {"Zerowa","Realistycznie", "Połowa czasu", "Bardzo szybko"};
        szybkoscPlywania = new JComboBox(plywanie);
        szybkoscPlywania.setSelectedIndex(3);
        szybkoscPlywaniaLabel = new JLabel("Wybierz szybkosc plywania:");szybkoscPlywaniaLabel.setFocusable(false);szybkoscPlywaniaLabel.setFont(font1);szybkoscPlywaniaLabel.setForeground(Color.WHITE);

        spinnery.add(liczbaKlientowLabel);
        spinnery.add(liczbaKlientow);
        spinnery.add(czasGenerowaniaLabel);
        spinnery.add(czasGenerowania);
        spinnery.add(czasWyswietlaniaLabel);
        spinnery.add(czasWyswietlania);
        spinnery.add(szybkoscPlywaniaLabel);
        spinnery.add(szybkoscPlywania);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        startSymulacja.setEnabled(true);
        stopSymulacja.setEnabled(false);
        resetSymulacja.setEnabled(false);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addPanelWylosowani();
        addPanelWynikiObslugi();
        opcjeSymulacjiKlient.add(przyciski,BorderLayout.PAGE_START);
        opcjeSymulacjiKlient.add(spinnery,BorderLayout.PAGE_END);
        opcjeSymulacjiKlient.add(wynikiObslugi, BorderLayout.WEST);
        opcjeSymulacjiKlient.add(wynikiLos, BorderLayout.EAST);
        opcjeSymulacjiKlient.setPreferredSize(new Dimension(430,550));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        opcjeSymulacjiSerwis = new JPanel();
        opcjeSymulacjiSerwis.setBorder(new TitledBorder(new EtchedBorder(),"Opcje Symulacji Serwis"));
        opcjeSymulacjiSerwis.setLayout(new BorderLayout());
        ((javax.swing.border.TitledBorder) opcjeSymulacjiSerwis.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) opcjeSymulacjiSerwis.getBorder()).setTitleColor(Color.WHITE);
        opcjeSymulacjiSerwis.setBackground(Color.BLACK);

        serwis = new JButton("Serwis");serwis.setFont(font2);
        opcjeSymulacjiSerwis.add(serwis,BorderLayout.LINE_END);
        serwis.setEnabled(false);

        SpinnerModel model4 = new SpinnerNumberModel(10,1,30,1);
        czasSeriwisu = new JSpinner(model4);czasSeriwisu.setFont(font2);
        czasSerwisuLabel = new JLabel("Czas trwania serwisu (sek): ");czasSerwisuLabel.setFocusable(false);czasSerwisuLabel.setFont(font3);czasSerwisuLabel.setForeground(Color.WHITE);
        opcjeSymulacjiSerwis.add(czasSerwisuLabel,BorderLayout.LINE_START);opcjeSymulacjiSerwis.add(czasSeriwisu,BorderLayout.CENTER);

        opcjeSymulacjiSerwis.setPreferredSize(new Dimension(420,70));
    }

    public static void dodajWynikiObslugi(String obsluzono, String przyjeto, String odrzucono, String liczniVip) {

        ileObsluzonoWynik.setText(obsluzono);
        getIlePrzyjetoWynik.setText(przyjeto);
        ileOdrzuconoWynik.setText(odrzucono);
        licznikVipWynik.setText(liczniVip);

    }
    public static void wyczyscWynikiObslugi(){

        ileObsluzonoWynik.setText("0");
        getIlePrzyjetoWynik.setText("0");
        ileOdrzuconoWynik.setText("0");
        licznikVipWynik.setText("0");

    }

    private void addPanelWynikiObslugi(){

        Font font1 = new Font("Arial",Font.BOLD,18);
        Font font2 = new Font("Calibri",Font.BOLD,35);

        wynikiObslugi = new JPanel();
        wynikiObslugi.setBorder(new TitledBorder(new EtchedBorder(), "Wyniki obslugi Klientów"));
        wynikiObslugi.setLayout(new GridLayout(0,1,2,2));
        wynikiObslugi.setBackground(new Color(30,30,30));
        ((javax.swing.border.TitledBorder) wynikiObslugi.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) wynikiObslugi.getBorder()).setTitleColor(Color.WHITE);

        ileObsluzono = new JLabel("Obslużono:");ileObsluzono.setFont(font1);ileObsluzono.setForeground(new Color(51,153,255));ileObsluzono.setFocusable(false);
        ileObsluzonoWynik = new JTextArea(1,10);ileObsluzonoWynik.setFont(font2);ileObsluzonoWynik.setFocusable(false);
        wynikiObslugi.add(ileObsluzono);wynikiObslugi.add(ileObsluzonoWynik);

        separator6 = new JSeparator(SwingConstants.HORIZONTAL);separator6.setForeground(new Color(51,153,255));
        wynikiObslugi.add(separator6);

        ilePrzyjeto = new JLabel("Przyjęto:");ilePrzyjeto.setFont(font1);ilePrzyjeto.setForeground(new Color(51,153,255));ilePrzyjeto.setFocusable(false);
        getIlePrzyjetoWynik = new JTextArea(1,10); getIlePrzyjetoWynik.setFont(font2);getIlePrzyjetoWynik.setFocusable(false);
        wynikiObslugi.add(ilePrzyjeto);wynikiObslugi.add(getIlePrzyjetoWynik);

        ileOdrzucono = new JLabel("Odrzucono:");ileOdrzucono.setFont(font1);ileOdrzucono.setForeground(new Color(51,153,255));ileOdrzucono.setFocusable(false);
        ileOdrzuconoWynik = new JTextArea(1,10); ileOdrzuconoWynik.setFont(font2);ileOdrzuconoWynik.setFocusable(false);
        wynikiObslugi.add(ileOdrzucono);wynikiObslugi.add(ileOdrzuconoWynik);

        separator7 = new JSeparator(SwingConstants.HORIZONTAL);separator7.setForeground(new Color(51,153,255));
        wynikiObslugi.add(separator7);

        licznikVip = new JLabel("Aktualnie VIP:");licznikVip.setFont(font1);licznikVip.setForeground(Color.GREEN);licznikVip.setFocusable(false);
        licznikVipWynik = new JTextArea(1,10);licznikVipWynik.setFont(font2);licznikVipWynik.setFocusable(false);
        wynikiObslugi.add(licznikVip);wynikiObslugi.add(licznikVipWynik);

        wynikiObslugi.setPreferredSize(new Dimension(150,100));
    }

    public static void dodajWynikiLos(String maxKlient, String wylosowano, String nieVip, String kDorosli, String kDzieciDoroslych, String kDzieci, String VIP, String vDorosli, String vDzieciDoroslych, String vDzieci) {
        ileMaxKlientowWynik.setText(maxKlient);
        ileWylosowanoWynik.setText(wylosowano);

        klienciZwyczajniWynik.setText(nieVip);

        kDorosliWynik.setText(kDorosli);
        kDzieciDoroslychWynik.setText(kDzieciDoroslych);
        kDzieciWynik.setText(kDzieci);

        klienciVIPWynik.setText(VIP);

        vDorosliWynik.setText(vDorosli);
        vDzieciDoroslychWynik.setText(vDzieciDoroslych);
        vDzieciWynik.setText(vDzieci);
    }
    public static void wyczyscWynikiLos(){

        ileMaxKlientowWynik.setText("0");
        ileWylosowanoWynik.setText("0");

        klienciZwyczajniWynik.setText("0");

        kDorosliWynik.setText("0");
        kDzieciDoroslychWynik.setText("0");
        kDzieciWynik.setText("0");

        klienciVIPWynik.setText("0");

        vDorosliWynik.setText("0");
        vDzieciDoroslychWynik.setText("0");
        vDzieciWynik.setText("0");
    }

    private void addPanelWylosowani(){

        Font font1 = new Font("Arial",Font.BOLD,14);
        Font font2 = new Font("Calibri",Font.BOLD,20);

        wynikiLos = new JPanel();
        wynikiLos.setBorder(new TitledBorder(new EtchedBorder(), "Wyniki losowania Klientów"));
        wynikiLos.setLayout(new GridLayout(0,2,2,2));
        wynikiLos.setBackground(new Color(30,30,30));
        ((javax.swing.border.TitledBorder) wynikiLos.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) wynikiLos.getBorder()).setTitleColor(Color.WHITE);

        ileMaxKlientow = new JLabel("MAX KLIENTÓW:");ileMaxKlientow.setFocusable(false);
        ileMaxKlientow.setFont(font1);ileMaxKlientow.setForeground(Color.RED);
        ileMaxKlientowWynik = new JTextArea(1,10);ileMaxKlientowWynik.setFocusable(false);
        ileMaxKlientowWynik.setFont(font2);

        ileWylosowano = new JLabel("WYLOSOWANYCH:");ileWylosowano.setFocusable(false);
        ileWylosowano.setFont(font1);ileWylosowano.setForeground(Color.RED);
        ileWylosowanoWynik = new JTextArea(1,10);ileWylosowanoWynik.setFocusable(false);
        ileWylosowanoWynik.setFont(font2);

        wynikiLos.add(ileMaxKlientow);wynikiLos.add(ileWylosowano);wynikiLos.add(ileMaxKlientowWynik);wynikiLos.add(ileWylosowanoWynik);

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);separator1.setForeground(Color.RED);
        wynikiLos.add(separator1);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);separator2.setForeground(Color.RED);
        wynikiLos.add(separator2);

        klienciZwyczajni = new JLabel("Klienci zwyczajni:");klienciZwyczajni.setFocusable(false);
        klienciZwyczajni.setFont(font1);klienciZwyczajni.setForeground(Color.RED);
        klienciZwyczajniWynik = new JTextArea(1,10);klienciZwyczajniWynik.setFocusable(false);
        klienciZwyczajniWynik.setFont(font2);
        wynikiLos.add(klienciZwyczajni); wynikiLos.add(klienciZwyczajniWynik);

        kDorosli = new JLabel("Dorosli:");kDorosli.setFont(font1);kDorosli.setForeground(Color.RED);kDorosli.setFocusable(false);
        kDorosliWynik = new JTextArea(1,10);kDorosliWynik.setFont(font2);kDorosliWynik.setFocusable(false);
        kDzieciDoroslych = new JLabel("Dzieci Doroslych:");kDzieciDoroslych.setFont(font1);kDzieciDoroslych.setForeground(Color.RED);
        kDzieciDoroslychWynik = new JTextArea(1,10);kDzieciDoroslychWynik.setFont(font2);kDzieciDoroslychWynik.setFocusable(false);
        kDzieci = new JLabel("Dzieci:");kDzieci.setFont(font1);kDzieci.setForeground(Color.RED);kDzieci.setFocusable(false);
        kDzieciWynik = new JTextArea(1,10);kDzieciWynik.setFont(font2);kDzieciWynik.setFocusable(false);
        wynikiLos.add(kDorosli);wynikiLos.add(kDorosliWynik);wynikiLos.add(kDzieciDoroslych);wynikiLos.add(kDzieciDoroslychWynik);wynikiLos.add(kDzieci);wynikiLos.add(kDzieciWynik);

        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);separator3.setForeground(Color.RED);
        wynikiLos.add(separator3);
        JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);separator4.setForeground(Color.RED);
        wynikiLos.add(separator4);

        klienciVIP = new JLabel("Klienci VIP:");klienciVIP.setFocusable(false);
        klienciVIP.setFont(font1);klienciVIP.setForeground(Color.RED);
        klienciVIPWynik = new JTextArea(1,10);klienciVIPWynik.setFocusable(false);
        klienciVIPWynik.setFont(font2);
        wynikiLos.add(klienciVIP);wynikiLos.add(klienciVIPWynik);



        vDorosli = new JLabel("Dorosli:");vDorosli.setFont(font1);vDorosli.setForeground(Color.RED);vDorosli.setFocusable(false);
        vDorosliWynik = new JTextArea(1,10);vDorosliWynik.setFont(font2);vDorosliWynik.setFocusable(false);
        vDzieciDoroslych = new JLabel("Dzieci Doroslych:");vDzieciDoroslych.setFont(font1);vDzieciDoroslych.setForeground(Color.RED);vDzieciDoroslych.setFocusable(false);
        vDzieciDoroslychWynik = new JTextArea(1,10);vDzieciDoroslychWynik.setFont(font2);vDzieciDoroslychWynik.setFocusable(false);
        vDzieci = new JLabel("Dzieci:");vDzieci.setFont(font1);vDzieci.setForeground(Color.RED);vDzieci.setFocusable(false);
        vDzieciWynik = new JTextArea(1,10);vDzieciWynik.setFont(font2);vDzieciWynik.setFocusable(false);
        wynikiLos.add(vDorosli);wynikiLos.add(vDorosliWynik);
        wynikiLos.add(vDzieciDoroslych);wynikiLos.add(vDzieciDoroslychWynik);
        wynikiLos.add(vDzieci);wynikiLos.add(vDzieciWynik);

        wynikiLos.setPreferredSize(new Dimension(270,420));

    }

    private void addGUI(){
        this.add(opcjeSymulacjiKlient,BorderLayout.PAGE_START);
        this.add(opcjeSymulacjiSerwis,BorderLayout.PAGE_END);
    }

    private void addListeners(){
        startSymulacja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setStartSymulacja();
            }
        });

        stopSymulacja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setStopSymulacja();
            }
        });

        resetSymulacja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setResetSymulacja();
            }
        });

        szybkoscPlywania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String wynik = (String)cb.getSelectedItem();

                switch (wynik) {
                    case "Realistycznie":
                        Symulacja.CZAS_PLYWANIA = 1;
                        break;
                    case "Połowa czasu":
                        Symulacja.CZAS_PLYWANIA = 2;
                        break;
                    case "Bardzo szybko":
                        Symulacja.CZAS_PLYWANIA = 10;
                        break;
                    case "Zerowa":
                        Symulacja.CZAS_PLYWANIA = 0;
                        break;
                }
            }
        });

        serwis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Symulacja.CZAS_SERWISU = (int) czasSeriwisu.getValue();
                Symulacja.SYMULACJA_SERWIS = true;
            }
        });
    }

    public static void setStartSymulacja(){
        Symulacja.SYMULACJA_END = false;

        if(Symulacja.SYMULACJA_RESET){

            Symulacja.SYMULACJA_RESET = false;
            Symulacja.SYMULACJA_BREAK = false;

            czasGenerowania.setEnabled(false);
            czasWyswietlania.setEnabled(false);
            liczbaKlientow.setEnabled(false);
            szybkoscPlywania.setEnabled(false);
            resetSymulacja.setEnabled(true);

            Symulacja.ILE_KLIENTOW_POCZAT = (int) liczbaKlientow.getValue();
            Symulacja.ILE_KLIENTOW = (int) liczbaKlientow.getValue();
            Symulacja.CZAS_GENEROWANIA = (int) czasGenerowania.getValue();
            Symulacja.CZAS_WYSWIETLANIA = (int) czasWyswietlania.getValue();

            Symulacja.makeThreads();
            Symulacja.generatorKlient.start();
            Symulacja.kasjer.start();

        }

        Symulacja.SYMULACJA_BREAK = false;

        System.out.println( "\n///////////////////////////////////////////////////////////////////////" +
                "\n///////////////////////////////////////////////////////////////////////" +
                "\nRozpoczęto symulację!" +
                "\nSYMULACJA_BREAK: " + Symulacja.SYMULACJA_BREAK +
                "\nSYMULACJA_RESET: " + Symulacja.SYMULACJA_RESET +
                "\n///////////////////////////////////////////////////////////////////////" +
                "\n///////////////////////////////////////////////////////////////////////\n");

        startSymulacja.setEnabled(false);
        stopSymulacja.setEnabled(true);
        serwis.setEnabled(true);
    }

    public static void setStopSymulacja(){

        Symulacja.SYMULACJA_BREAK = true;

        System.out.println( "\n///////////////////////////////////////////////////////////////////////"+
                "\n///////////////////////////////////////////////////////////////////////" +
                "\nPrzerwano symulację! SYMULACJA_BREAK: " + Symulacja.SYMULACJA_BREAK +
                "\n///////////////////////////////////////////////////////////////////////" +
                "\n///////////////////////////////////////////////////////////////////////\n");

        stopSymulacja.setEnabled(false);
        startSymulacja.setEnabled(true);
    }

    public static void setResetSymulacja(){
        Symulacja.SYMULACJA_RESET = true;
        Symulacja.SYMULACJA_END = true;

        czasGenerowania.setEnabled(true);
        czasWyswietlania.setEnabled(true);
        liczbaKlientow.setEnabled(true);
        szybkoscPlywania.setEnabled(true);

        Symulacja.SYMULACJA_BREAK = false;
        Symulacja.SYMULACJA_SERWIS = false;

        Symulacja.stopThreads();
        Kasa.redo();
        Kasa.stanPoResecie();

        System.out.println( "\n///////////////////////////////////////////////////////////////////////" +
                "\n///////////////////////////////////////////////////////////////////////" +
                "\nZresetowano symulację! SYMULACJA_RESET: " + Symulacja.SYMULACJA_RESET +
                "\n///////////////////////////////////////////////////////////////////////" +
                "\n///////////////////////////////////////////////////////////////////////\n");

        stopSymulacja.setEnabled(false);
        startSymulacja.setEnabled(true);
        serwis.setEnabled(false);

        //AppPanel.redo();
    }
}
