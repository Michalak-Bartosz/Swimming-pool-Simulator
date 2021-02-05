package gui.symulacja;
import gui.SmartScroller;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BasenRekreacyjnyPanel extends JPanel {

    private static JTextArea logPrompt2;

    public BasenRekreacyjnyPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new TitledBorder(new EtchedBorder(), "Basen Rekreacyjny: Max 45 osob"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(Color.BLACK);

        Font font1 = new Font("Calibri",Font.BOLD,15);
        logPrompt2 = new JTextArea(8, 15);
        logPrompt2.setFont(font1);

        logPrompt2.setEditable(false);
        JScrollPane scroll = new JScrollPane(logPrompt2);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        int WIDTH = 430;
        int HEIGHT = 180;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        new SmartScroller(scroll);
        add(scroll);
        setVisible(true);
    }
    public static synchronized void dodajOpisBasenuRek(String string) {
        logPrompt2.append(string + "\n");
    }

    public static synchronized void wyczyscOpisBasenuRek(){ logPrompt2.setText("");}
}
