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
    public BalancePage(int myMoney) {
        setTitle("ATM 잔액조회");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        money.setText(Integer.toString(myMoney));
        c.add(money);

        setSize(150,100);
        setVisible(true);
    }
}

class WithdrawPage extends JFrame {

    private JTextField withdrawMoney = new JTextField(20);
    private JButton enterButton = new JButton("확인");
    protected BankAccount bankAccount;
    public WithdrawPage(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        setTitle("출금");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("withdraw Amount "));
        c.add(withdrawMoney);

        enterButton.addActionListener(new EnterButtonActionListener());
        c.add(enterButton);

        setSize(300,250);
        setVisible(true);
    }
    private class EnterButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String withdrawMoneyData = withdrawMoney.getText();

            if(bankAccount.checkBalanceIsEnough(Integer.parseInt(withdrawMoneyData))){//잔액이 출금하려는 금액보다 큰지 확인
                bankAccount.withDraw(Integer.parseInt(withdrawMoneyData));
                new AfterLoginPage(bankAccount);
            }
            else{
                JOptionPane.showMessageDialog(null, "잔액이 부족합니다", "Message",JOptionPane.ERROR_MESSAGE );
                new AfterLoginPage(bankAccount);
                setVisible(false);
            }

        }
    }
}