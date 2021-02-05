package gui.symulacja;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SymulacjaPanel extends JPanel{

    public static BasenOlimpijskiPanel basenOlimpijskiSymulacja;
    public static BasenRekreacyjnyPanel basenRekreacyjnySymulacja;
    public static BrodzikPanel brodzikSymulacja;

    public static JProgressBar basenOlimProgres;
    public static JProgressBar basenRekProgres;
    public static JProgressBar brodzikProgres;

    public static JTextArea sredniWiekText;
    private JLabel sredniWiek;

    private JPanel logery;
    private JPanel progres;

    public SymulacjaPanel(){
        setBorder(new TitledBorder(new EtchedBorder(), "Symulacja"));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleFont(new Font("Arial", Font.BOLD,15));
        ((javax.swing.border.TitledBorder) this.getBorder()).setTitleColor(Color.WHITE);
        this.setBackground(new Color(30,30,30));
        this.setLayout(new BorderLayout());
        int width = 460;
        int height = 650;
        setPreferredSize(new Dimension(width, height));

        Font font1 = new Font("Calibri",Font.BOLD,20);
        logery = new JPanel();
        logery.setBackground(Color.BLACK);
        sredniWiek = new JLabel("Aktualny sredni wiek w basenie: ");sredniWiek.setForeground(Color.WHITE);sredniWiek.setFocusable(false);sredniWiek.setFont(font1);
        sredniWiekText = new JTextArea("0",1,2);sredniWiekText.setForeground(Color.WHITE);sredniWiekText.setBackground(Color.BLACK);sredniWiekText.setFocusable(false);sredniWiekText.setFont(font1);
        basenOlimpijskiSymulacja = new BasenOlimpijskiPanel();
        basenRekreacyjnySymulacja = new BasenRekreacyjnyPanel();
        brodzikSymulacja = new BrodzikPanel();
        logery.add(basenOlimpijskiSymulacja);logery.add(sredniWiek);logery.add(sredniWiekText);logery.add(basenRekreacyjnySymulacja);logery.add(brodzikSymulacja);

        progres = new JPanel();
        progres.setLayout(new BoxLayout(progres, BoxLayout.PAGE_AXIS));
        progres.setBackground(Color.BLACK);
        basenOlimProgres = new JProgressBar(JProgressBar.VERTICAL,0,35);
        basenOlimProgres.setForeground(Color.GREEN);
        basenOlimProgres.setString("Zapelnienie");
        basenOlimProgres.setPreferredSize(new Dimension(180,20));
        basenRekProgres = new JProgressBar(JProgressBar.VERTICAL,0,45);
        basenRekProgres.setForeground(Color.GREEN);
        basenRekProgres.setString("Zapelnienie");
        basenRekProgres.setPreferredSize(new Dimension(180,20));
        brodzikProgres = new JProgressBar(JProgressBar.VERTICAL,0,20);
        brodzikProgres.setForeground(Color.GREEN);
        brodzikProgres.setString("Zapelnienie");
        brodzikProgres.setPreferredSize(new Dimension(180,20));
        progres.add(basenOlimProgres);progres.add(basenRekProgres);progres.add(brodzikProgres);
        progres.setPreferredSize(new Dimension(25,580));

        this.add(progres,BorderLayout.LINE_START);
        this.add(logery,BorderLayout.CENTER);
    }


}
