package gui.symulacja;
import gui.SmartScroller;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BrodzikPanel extends JPanel {

    private static JTextArea logPrompt3;

    public BrodzikPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new TitledBorder(new EtchedBorder(), "Brodzik dla dzieci: Max 20 osob"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(Color.BLACK);

        Font font1 = new Font("Calibri",Font.BOLD,15);
        logPrompt3 = new JTextArea(8, 15);
        logPrompt3.setFont(font1);

        logPrompt3.setEditable(false);
        JScrollPane scroll = new JScrollPane(logPrompt3);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        int WIDTH = 430;
        int HEIGHT = 200;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        new SmartScroller(scroll);
        add(scroll);
        setVisible(true);
    }
    public static synchronized void dodajOpisBasenuBro(String string) {
        logPrompt3.append(string + "\n");
    }

    public static synchronized void wyczyscOpisBasenuBro(){ logPrompt3.setText("");}
}
