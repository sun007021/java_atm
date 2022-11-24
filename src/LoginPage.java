import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JTextField bankAccount = new JTextField(20);
    private JTextField password = new JTextField(20);
    private JButton submitButton = new JButton("submit");

    public LoginPage() {
        setTitle("ATM Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Account "));
        c.add(bankAccount);
        c.add(new JLabel("password "));
        c.add(password);

        submitButton.addActionListener(new SubmitButtonActionListener());
        c.add(submitButton);

        setSize(300, 300);
        setVisible(true);
    }

    private class SubmitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String bankAccountData = bankAccount.getText();
            String passwordData = password.getText();

            if (Bank.login(bankAccountData, passwordData)) {// login 성공시
                new LoginedPage(Bank.findAccount(bankAccountData));
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class LoginedPage extends JFrame {
    private JButton balanceButton = new JButton("Balance");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton depositButton = new JButton("Deposit");
    protected BankAccount bankAccount;

    public LoginedPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("ATM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 80));

        balanceButton.addActionListener(new BalanceButtonActionListener());
        withdrawButton.addActionListener(new WithdrawButtonActionListener());
        depositButton.addActionListener(new DepositButtonActionListener());

        c.add(balanceButton);
        c.add(withdrawButton);
        c.add(depositButton);

        setSize(300, 300);
        setVisible(true);
    }

    private class BalanceButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new BalancePage(bankAccount.getBalance());
        }
    }

    private class WithdrawButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new WithdrawPasswordPage(bankAccount);
            setVisible(false);
        }
    }

    private class DepositButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new DepositPage(bankAccount);
            setVisible(false);
        }
    }
}

class WithdrawPasswordPage extends JFrame {
    private JTextField password = new JTextField(20);
    private JButton submitButton = new JButton("submit");
    protected BankAccount bankAccount;

    public WithdrawPasswordPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("Password");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("password "));
        c.add(password);

        submitButton.addActionListener(new SubmitButtonActionListener());
        c.add(submitButton);

        setSize(300, 300);
        setVisible(true);
    }

    private class SubmitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String passwordData = password.getText();

            if (bankAccount.isCorrectPassword(passwordData)) {
                new WithdrawPage(bankAccount);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

class DepositPage extends JFrame {
    private JTextField depositAmount = new JTextField(20);
    private JButton submitButton = new JButton("submit");
    protected BankAccount bankAccount;

    public DepositPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("Deposit");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Please put cash"));
        c.add(depositAmount);

        submitButton.addActionListener(new SubmitButtonActionListener());
        c.add(submitButton);

        setSize(300, 300);
        setVisible(true);
    }

    private class SubmitButtonActionListener implements ActionListener {
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            String depositMoneyData = depositAmount.getText();
            try {//3초 기다림
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            bankAccount.deposit(Integer.parseInt(depositMoneyData));

            new LoginedPage(bankAccount);
            setVisible(false);
        }
    }
}