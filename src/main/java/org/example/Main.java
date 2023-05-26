package org.example;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        int commission = 100;

        Account account1 = new Account(123,500,100,500);
        bank.addAccount(account1);
        account1.deposit(200);
        account1.withdraw(500);
//        account1.checkBalance();

        Account account2 = new Account(1234,1000,200,2000);
        bank.addAccount(account2);

        Account account3 = new Account(12345,90, 400, 800);
        bank.addAccount(account3);

        Account account4 = new Account(134,0, 50, 100);
        bank.addAccount(account4);

        bank.collectCommission(commission);
        bank.printReport(commission);
    }
}