package org.example;

public class Account {

    int debitBalance;
    int creditBalance;
    int creditLimit;

    public Account(int debitBalance, int creditBalance, int creditLimit) {
        this.debitBalance = debitBalance;
        this.creditBalance = creditBalance;
        this.creditLimit = creditLimit;
    }

    public int deductCommission(int commission) {
        int commissionDeducted = 0;

        if (debitBalance >= commission) {
            debitBalance -= commission;
            commissionDeducted = commission;
        } else {
            commissionDeducted = debitBalance;
            commission -= debitBalance;
            debitBalance = 0;
            if (creditBalance + commission <= creditLimit) {
                creditBalance += commission;
                commissionDeducted += commission;
            } else {
                commissionDeducted += creditLimit - creditBalance;
                creditBalance = creditLimit;
            }
        }

        return commissionDeducted;
    }

    public void deposit(int amount) {
        debitBalance += amount;
    }

    public void withdraw(int amount) {
        if (debitBalance >= amount) {
            debitBalance -= amount;
        } else {
            int remaining = amount - debitBalance;
            debitBalance = 0;
            if (creditBalance + remaining <= creditLimit) {
                creditBalance += remaining;
            } else {
                System.out.println("Unable to withdraw. Exceeds credit limit!");
            }
        }
    }

    public void checkBalance() {
        System.out.println("Debit Balance: " + debitBalance);
        System.out.println("Credit Balance: " + creditBalance);
    }

    public void printAccountDetails(int accountId, int commissionCollected) {
        System.out.println("Account ID: " + accountId
                            + ", Debit Balance: " + debitBalance
                            + ", Credit Balance: " + creditBalance
                            + ", Credit Limit: " + creditLimit
                            + ", Remaining Commission: " + commissionCollected);
    }
}
