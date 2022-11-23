
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPage extends JFrame {
    private JButton loginButton = new JButton("Login");
    private JButton simulationButton = new JButton("Simulation");
    public MainPage() {
        setTitle("ATM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER,30,80));

        loginButton.addActionListener(new LoginButtonActionListener());
        simulationButton.addActionListener(new SimulationButtonActionListener());

        c.add(loginButton);
        c.add(simulationButton);

        setSize(300,250);
        setVisible(true);

    }
    private class LoginButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginPage();
            setVisible(false);
        }
    }
    private class SimulationButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //  ^^ 알아서 잘 하기~
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }

}
