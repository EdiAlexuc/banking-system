package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    List<Account> accounts = new ArrayList<>();
    int totalCommissionCollected = 0;

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void collectCommission(int commission) {
        for (Account account: accounts) {
            try {
                totalCommissionCollected += account.deductCommission(commission);
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to deduct commission from Account ID " + account.getAccountId()
                                    + ". Reason: " +e.getMessage());
            }
        }
    }

    public void printReport(int comission) {
        System.out.println("============ Bank Report ============");
        System.out.println("Total commission collected by the bank: " + totalCommissionCollected);
        System.out.println("Commission value for each user: " + comission);
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println("--- User " + account.getAccountId() + " ---");
            System.out.println("Debit account balance: " + account.getDebitBalance());
            System.out.println("Credit account balance: " + account.getCreditBalance());
            int commissionDeducted = comission;
            if (comission > account.getDebitBalance()) {
                commissionDeducted = account.getDebitBalance();
            }
            System.out.println("Commission deducted: " + commissionDeducted);
        }
        System.out.println("========================");
    }
}
