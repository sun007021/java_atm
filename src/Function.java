import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Function {
}

class BalancePage extends JFrame {
    private JLabel money = new JLabel();
    private JButton exitBtn = new JButton("EXIT");
    public BalancePage(int myMoney) {
        setTitle("Account balance inquiry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        money.setText(Integer.toString(myMoney));
        c.add(money);
        exitBtn.addActionListener(new ExitActionListener());
        c.add(exitBtn);
        setSize(150, 100);
        setVisible(true);
    }

    private class ExitActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}

class WithdrawPage extends JFrame {

    private JTextField withdrawMoney = new JTextField(20);
    private JButton submitButton = new JButton("submit");
    protected BankAccount bankAccount;

    public WithdrawPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("Withdraw");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("withdraw Amount "));
        c.add(withdrawMoney);

        submitButton.addActionListener(new EnterButtonActionListener());
        c.add(submitButton);

        setSize(300, 250);
        setVisible(true);
    }

    private class EnterButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawMoneyData = withdrawMoney.getText();

            if (bankAccount.checkBalanceIsEnough(Integer.parseInt(withdrawMoneyData))) {//잔액이 출금하려는 금액보다 큰지 확인
                bankAccount.withDraw(Integer.parseInt(withdrawMoneyData));
                new LoginedPage(bankAccount);
            } else {
                JOptionPane.showMessageDialog(null, "balance is not enough", "Message", JOptionPane.ERROR_MESSAGE);
                new LoginedPage(bankAccount);
                setVisible(false);
            }

        }
    }
}