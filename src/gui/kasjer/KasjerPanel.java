package gui.kasjer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class KasjerPanel extends JPanel {

    private JPanel bledy;
    private static JTextArea bledyText1;
    private static JTextArea bledyText2;
    private static JTextArea bledyText3;
    private static JTextArea bledyText4;
    private JPanel regulamin;

    private JTextArea regulaminText;


    public KasjerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new TitledBorder(new EtchedBorder(), "Panel Kasjera"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(new Color(30,30,30));
        int width = 420;
        int height = 650;
        setPreferredSize(new Dimension(width, height));
        setGUI();
        addGUI();
        czyszczenieBledu();
    }

    private void setGUI() {

        Font font1 = new Font("Arial",Font.BOLD,20);

        bledy = new JPanel();
        bledy.setLayout(new GridLayout(4,0,3,0));
        bledy.setBorder(new TitledBorder(new EtchedBorder(),"Odstepstwa od Regulaminu Plywalni: "));
        ((javax.swing.border.TitledBorder) bledy.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) bledy.getBorder()).setTitleColor(Color.WHITE);
        bledy.setBackground(Color.BLACK);bledy.setFocusable(false);

        bledyText1 = new JTextArea();bledyText1.setFont(font1);bledyText1.setForeground(new Color(153,0,0));bledyText1.setFocusable(false);
        bledyText2 = new JTextArea();bledyText2.setFont(font1);bledyText2.setForeground(new Color(153,0,0));bledyText2.setFocusable(false);
        bledyText3 = new JTextArea();bledyText3.setFont(font1);bledyText3.setForeground(new Color(153,0,0));bledyText3.setFocusable(false);
        bledyText4 = new JTextArea();bledyText4.setFont(font1);bledyText4.setForeground(new Color(153,0,0));bledyText4.setFocusable(false);

        bledy.add(bledyText1);bledy.add(bledyText2);bledy.add(bledyText3);bledy.add(bledyText4);

        regulamin = new JPanel();
        regulamin.setBorder(new TitledBorder(new EtchedBorder(),"Regulamin Pływalni"));
        regulamin.setLayout(new BoxLayout(regulamin,BoxLayout.PAGE_AXIS));
        ((javax.swing.border.TitledBorder) regulamin.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,28));
        ((javax.swing.border.TitledBorder) regulamin.getBorder()).setTitleColor(new Color(51,153,255));
        regulamin.setBackground(Color.BLACK);

        Font font2 = new Font("Calibri",Font.BOLD,20);
        regulaminText = new JTextArea(1,18);regulaminText.setFocusable(false);regulaminText.setLineWrap(true);regulaminText.setBackground(Color.BLACK);regulaminText.setForeground(new Color(51,153,255));
        regulaminText.append(   "\n+ Tylko osoby pełnoletnie moga korzystać\n   z basenu olimpijskiego." +
                                "\n+ Dzieci poniżej 10 roku życia nie mogą\n   przebywać na basenie bez opieki osoby\n  dorosłej." +
                                "\n+ W brodziku kąpac mogą się jedynie dzieci\n   do 5 roku zycia i ich opiekunowie." +
                                "\n+ Dzieci do 3 roku życia muszą pływać\n   w Pampersach J." +
                                "\n+ Średnia wieku w basenie rekreacyjnym nie\n   może być wyższa niż 40 lat." +
                                "\n+ Noszenie czepków pływackich nie jest\n   obowiązkowe." +
                                "\n\nCENNIK: \nDorosly:15zł/h \tDziecko: 12zł/h");//Dorosły za min=0,25zł  Dziecko za min=0,2zł

        regulaminText.setFont(font2);
        regulamin.add(regulaminText);
        regulamin.setPreferredSize(new Dimension(420,325));
    }

    private void addGUI() {
        this.add(bledy);
        this.add(regulamin);
    }

    public static void obslugaBledu(String blad){ bledyText1.setText(blad); }
    public static void czyszczenieBledu(){
        bledyText1.setText("");
        bledyText2.setText("");
        bledyText3.setText("");
        bledyText4.setText("");
    }
}
