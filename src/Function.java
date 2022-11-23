import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Function {
}
class BalancePage extends JFrame {
    private JLabel money = new JLabel();
    public BalancePage(String myMoney) {
        setTitle("타이틀.. 수정ㄱㄱ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        money.setText(myMoney);
        c.add(money);

        setSize(150,100);
        setVisible(true);
    }
}

class WithdrawPage extends JFrame {

    private JTextField withdrawMoney = new JTextField(20);
    private JButton enterButton = new JButton("확인");
    public WithdrawPage() {
        setTitle("타이틀.. 수정ㄱㄱ");
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

            //출금하는 기능 사용

            //잔액이 부족할 경우 아래 주석처리 코드까지 실행
            //new withdrawError();
            new AfterLoginPage();
            setVisible(false);

        }
    }

    public static void main(String[] args) {
        new WithdrawPage();
    }
}