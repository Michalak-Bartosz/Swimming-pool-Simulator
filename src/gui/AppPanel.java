package gui;

import gui.kasjer.KasjerPanel;
import gui.klient.KlientPanel;
import gui.parametryzacja.ParametryzacjaSymulacjaPanel;
import gui.symulacja.SymulacjaPanel;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {

    static SymulacjaPanel symulacjaPanel;
    static KasjerPanel kasjerPanel;
    static KlientPanel klientPanel;
    static ParametryzacjaSymulacjaPanel parametryzacjaSymulacjaPanel;

    public AppPanel(){
        this.setBackground(Color.BLACK);
        symulacjaPanel = new SymulacjaPanel();
        kasjerPanel = new KasjerPanel();
        klientPanel = new KlientPanel();
        parametryzacjaSymulacjaPanel = new ParametryzacjaSymulacjaPanel();
        add(kasjerPanel);
        add(klientPanel);
        add(symulacjaPanel);
        add(parametryzacjaSymulacjaPanel);
    }

    public static void redo(){
        symulacjaPanel.repaint();
        kasjerPanel.repaint();
        klientPanel.repaint();
        parametryzacjaSymulacjaPanel.repaint();
    }
}
