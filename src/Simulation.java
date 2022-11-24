import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Simulation implements Runnable {
    TextArea ta;

    public static void simulate(BufferedReader reader, TextArea ta) {
        String str;
        String account, password;
        String command;
        String amount;
        try {
            while ((str = reader.readLine()) != null) {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //input 출력
                ta.append("Input:" + str + "\n");

                String[] split = str.split(" ");
                account = split[0];
                password = split[1];
                command = split[2];
                BankAccount bankAccount = Bank.findAccount(account);

                if(!bankAccount.isCorrectPassword(password)){// 패스워드 일치하지 않으면 해당 명령 실행 안함
                    ta.append("Output: Wrong password\n\n");
                    continue;
                }
                if (command.equals("balance")) {
                    String balance = Integer.toString(bankAccount.getBalance());
                    ta.append("Output: " + balance + "\n");
                } else if (command.equals("deposit")) {
                    amount = split[3];
                    bankAccount.deposit(Integer.parseInt(amount));
                    ta.append("Output: " + account + "deposit" + amount + "success\n");
                } else if (command.equals("withdraw")) {
                    amount = split[3];
                    if(bankAccount.checkBalanceIsEnough(Integer.parseInt(amount))){
                        bankAccount.withDraw(Integer.parseInt(amount));
                        ta.append("Output: " + account + "withdraw" + amount + "success\n");
                    }
                    else{//잔액이 부족한 경우
                        ta.append("Output: you don't have enough balance\n");
                    }
                }

                ta.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Simulation(TextArea ta) {
        this.ta = ta;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\simulation.txt"));
            simulate(reader, ta);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
    }
}
