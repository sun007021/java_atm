import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JTextField bankAccount = new JTextField(20);
    private JTextField password = new JTextField(20);
    private JButton enterButton = new JButton("확인");
    public LoginPage() {
        setTitle("ATM Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("bank account "));
        c.add(bankAccount);
        c.add(new JLabel("password "));
        c.add(password);

        enterButton.addActionListener(new EnterButtonActionListener());
        c.add(enterButton);

        setSize(300,250);
        setVisible(true);
    }
    private class EnterButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String bankAccountData = bankAccount.getText();
            String passwordData = password.getText();

            if(Bank.login(bankAccountData, passwordData)){// login 성공시
                new AfterLoginPage();
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다", "Message",JOptionPane.ERROR_MESSAGE );
            }
        }
    }
}

class AfterLoginPage extends JFrame {
    private JButton balanceButton = new JButton("Balance");
    private JButton withdrawButton = new JButton("Withdraw");
    private JButton depositButton = new JButton("Deposit");

    public AfterLoginPage() {
        setTitle("ATM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER,10,80));

        balanceButton.addActionListener(new BalanceButtonActionListener());
        withdrawButton.addActionListener(new WithdrawButtonActionListener());
        depositButton.addActionListener(new DepositButtonActionListener());

        c.add(balanceButton);
        c.add(withdrawButton);
        c.add(depositButton);

        setSize(300,250);
        setVisible(true);
    }
    private class BalanceButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //잔액 불러오는 기능 사용
            //10000자리에 잔액 넣으면 됨(파일에서 불러와서)
            new BalancePage("10000");
        }
    }
    private class WithdrawButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new BeforeWithdrawPage();
            setVisible(false);
        }
    }
    private class DepositButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new DepositPage();
            setVisible(false);
        }
    }
}
 class BeforeWithdrawPage extends JFrame {
    private JTextField password = new JTextField(20);
    private JButton enterButton = new JButton("확인");
    public BeforeWithdrawPage() {
        setTitle("타이틀.. 수정ㄱㄱ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("password "));
        c.add(password);

        enterButton.addActionListener(new EnterButtonActionListener());
        c.add(enterButton);

        setSize(300,250);
        setVisible(true);
    }
    private class EnterButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String passwordData = password.getText();

            //AfterLoginPage에 저장된 비번과 일치하면 아래 코드 실행
            new WithdrawPage();
            setVisible(false);
            //틀릴 경우
            //위 두줄은 실행 X, 오류메시지 띄우기
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class DepositPage extends JFrame {
    private JTextField depositMoney = new JTextField(15);
    private JButton enterButton = new JButton("확인");

    public DepositPage() {
        setTitle("타이틀.. 수정ㄱㄱ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Please put in cash or check"));
        c.add(depositMoney);

        enterButton.addActionListener(new EnterButtonActionListener());
        c.add(enterButton);

        setSize(300,250);
        setVisible(true);
    }
    private class EnterButtonActionListener implements ActionListener{
        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            String withdrawMoneyData = depositMoney.getText();

            //출금하는 기능 사용

            //잔액이 부족할 경우 아래 주석처리 코드까지 실행
            new AfterLoginPage();
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new DepositPage();
    }
}