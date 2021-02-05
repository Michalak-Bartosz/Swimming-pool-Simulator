import engine.Symulacja;
import gui.AppPanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public static AppPanel appPanel;
    private static Symulacja symulacja;

    public App(){

        this.setBackground(Color.BLACK);
        appPanel = new AppPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PW-15");

        add(appPanel);
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        symulacja = new Symulacja();
        symulacja.start();
    }

    public static void main(String[] args)
    {
        new App();
    }
}
