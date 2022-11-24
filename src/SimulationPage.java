import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimulationPage extends JFrame {
    private JLabel doing = new JLabel();
    private JButton exitBtn = new JButton("EXIT");
    TextArea ta = new TextArea();
    public SimulationPage() {
        setTitle("Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());


        // simulation 실행
        Simulation simulation = new Simulation(ta);
        Thread thread = new Thread(simulation);
        thread.start();
        c.add(ta);

        exitBtn.addActionListener(new SimulationPage.ExitActionListener());
        c.add(exitBtn);

        setSize(800, 500);
        setVisible(true);
    }

    private class ExitActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}