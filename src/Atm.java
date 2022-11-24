import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String account;
    private String password;
    int balance=0; // 계좌 잔액

    //이부분 알아서 생성자에서 값 받는식 말고 setAccount 함수같은거 만들어서 하든지 변형해도됨
    BankAccount(String account, String password){ // 객체 생성시 입력
        this.account = account;
        this.password = password;
    }

    Boolean isCorrectPassword(String password){// 비밀번호 맞게 입력했는지 확인
        return (this.password.equals(password));
    }

    Boolean checkBalanceIsEnough(int amount){
        if(balance>=amount){
            return true;
        }
        else{
            return false;
        }
    }

    void withDraw(int amount){
        balance-=amount;// 출금
        try{// transactions 기록
            FileWriter fw = new FileWriter(new File("C:\\Temp\\transactions.txt"), true);
            FileWriter fileWrite=new FileWriter("C:\\Temp\\transactions.txt",true);
            // 계좌번호
            fileWrite.write("\n"); //버퍼에 개행 삽입
            fileWrite.write(account);

            // 현재 잔고
            fileWrite.write(" ");
            fileWrite.write(Integer.toString(balance));

            // 거래날짜
            fileWrite.write(" ");
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatedNow = date.format(formatter);
            fileWrite.write(formatedNow);

            // 거래내역
            fileWrite.write(" ");
            fileWrite.write("withdraw");

            // 거래금액
            fileWrite.write(" ");
            fileWrite.write(Integer.toString(amount));
            fileWrite.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getAccount() {
        return account;
    }

    public int getBalance(){
        return balance;
    }
}

class Bank {
    static List<BankAccount> accountList = new ArrayList<>();

    public static BankAccount findAccount(String account) {
        for (int i = 0; i < accountList.size(); i++) {
            if (account.equals(accountList.get(i).getAccount())) {
                return accountList.get(i);
            }
        }
        return null;
    }

    public static boolean login(String account, String password) {
        for (int i = 0; i < accountList.size(); i++) {
            if (account.equals(accountList.get(i).getAccount())) {// 나중에 findAccount 이용해서하도록 변경
                if (accountList.get(i).isCorrectPassword(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false; // 일치하는거 하나도 없으면 false
    }

    public static int printBalance(String account) { // 인자로 받는 계좌번호의 객체에 있는 잔액 출력
        BankAccount bankAccount = findAccount(account);
        return bankAccount.getBalance();
    }

    public void setAccountList(BufferedReader readBankAccount) {// 계좌 파일에서 값 읽어와서 accountList에 객체들로 만들어 저장
        String str;
        String account, password;
        try {
            while ((str = readBankAccount.readLine()) != null) {
                String[] split = str.split(" ");
                account = split[0];
                password = split[1];

                accountList.add(new BankAccount(account, password));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

public class Atm {
    public static void main(String[] args) {
        try {
            BufferedReader readBankAccount = new BufferedReader(new FileReader("C:\\Temp\\bankaccount.txt"));
            Bank bank = new Bank();
            bank.setAccountList(readBankAccount);
            new MainPage();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
