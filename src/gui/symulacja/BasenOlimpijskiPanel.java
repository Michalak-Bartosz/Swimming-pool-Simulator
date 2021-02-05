package gui.symulacja;
import gui.SmartScroller;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BasenOlimpijskiPanel extends JPanel {

    private static JTextArea logPrompt1;

    public BasenOlimpijskiPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new TitledBorder(new EtchedBorder(), "Basen Olimpijski: Max 35 osob"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(Color.BLACK);

        Font font1 = new Font("Calibri",Font.BOLD,15);
        logPrompt1 = new JTextArea(8, 15);
        logPrompt1.setFont(font1);

        logPrompt1.setEditable(false);
        JScrollPane scroll = new JScrollPane(logPrompt1);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        int WIDTH = 430;
        int HEIGHT = 200;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        new SmartScroller(scroll);
        add(scroll);
        setVisible(true);
    }
    public static synchronized void dodajOpisBasenuOlimp(String string) {
        logPrompt1.append(string + "\n");
    }

    public static synchronized void wyczyscOpisBasenuOlimp(){ logPrompt1.setText("");}
}
