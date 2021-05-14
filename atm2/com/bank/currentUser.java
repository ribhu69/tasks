package com.bank;

import java.util.ArrayList;
import com.bank.interestCharge;
import java.util.List;

public class currentUser {


    interestCharge bankInterest = new interestCharge();

    protected String userAccountName = "";
    protected long userAccountID = 0;
    protected double userAccountBalance = 0;
    protected String cardNumber = "0";
    protected int pin = 0;

    private double accBalance;
    private double charges;
    private double transactionAmount;
    private double cashback;


    public currentUser(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin) {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    protected void welcome() {
        System.out.println("Hello ");
    }

    protected int getPin() {
        return this.pin;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    public void setUserAccountBalance(double accountBalance) {
        this.userAccountBalance = accountBalance;
    }

    public void setUserCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    protected String getCardNumber() {
        return this.cardNumber;
    }

    protected String getUserAccountName() {
        return this.userAccountName;
    }

    public double savingAccountBalance() {
        return this.userAccountBalance;
    }

    public double getUserAccountBalance() {
        return this.userAccountBalance;
    }

    public void accountBalance() {
        System.out.println("Account Balance " + this.userAccountBalance);
    }

    protected void completeTransaction(List<userDetails> accounts, String cardNumber) {
        for (userDetails i : accounts) {
            if (i.getCardNumber() == cardNumber) {
                i.setUserAccountBalance(this.userAccountBalance);

            }
        }
    }


    protected void withDraw(double customerAccountBalance, int amount) {


        accBalance = customerAccountBalance;
        if (accBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else if (amount % 5 != 0)
            System.out.println("Please correct the amount of value. It should be multiple of 5");

        else {
            charges = amount * bankInterest.interestCharge(amount);
            transactionAmount = amount + charges;
            if (accBalance - transactionAmount <= accBalance) {
                accBalance = accBalance - transactionAmount;
                this.userAccountBalance = accBalance;
                withDrawInfo();
            }
        }
    }

    protected void swipe(double customerAccountBalance, double amount)
    {
        accBalance=customerAccountBalance;
        charges = amount * bankInterest.interestCharge(amount);
        cashback = 0.01*amount;
        transactionAmount = amount + charges;
        if (accBalance - transactionAmount <= accBalance) {
            accBalance = accBalance - transactionAmount;
            accBalance += cashback;
            this.userAccountBalance = accBalance;
            swipeInfo();
        }
    }

    public void deposit(double customerAccountBalance, double depositAmount) {
        accBalance = customerAccountBalance;
        accBalance += depositAmount;
        this.userAccountBalance = accBalance;
        System.out.println("Deposited Amount: "+depositAmount+"\nAccount Balance: USD "+this.userAccountBalance);

    }

    private void withDrawInfo()
    {
        System.out.println("Charges: "+ this.charges+"\nAccount Balance: USD "+this.userAccountBalance);
    }

    private void swipeInfo()
    {
        System.out.println("Cashback: "+ this.cashback+"\nAccount Balance: USD "+this.userAccountBalance);
    }

}
