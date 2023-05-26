package org.example;

public class Account {

    private int accountId;
    private int debitBalance;
    private int creditBalance;
    private int creditLimit;

    public Account(int accountId,int debitBalance, int creditBalance, int creditLimit) {
        if (debitBalance < 0 || creditBalance < 0 || creditLimit < 0) {
            throw new IllegalArgumentException("Invalid account balances or credit limit");
        }
        this.debitBalance = debitBalance;
        this.creditBalance = creditBalance;
        this.creditLimit = creditLimit;
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(int debitBalance) {
        this.debitBalance = debitBalance;
    }

    public int getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
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
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid deposit amount");
        }
        debitBalance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        if (debitBalance >= amount) {
            debitBalance -= amount;
        } else {
            int remaining = amount - debitBalance;
            debitBalance = 0;
            if (creditBalance + remaining <= creditLimit) {
                creditBalance += remaining;
            } else {
                throw new IllegalArgumentException("Unable to withdraw. Exceeds credit limit!");
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
