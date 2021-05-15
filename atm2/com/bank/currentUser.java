package com.bank;


import java.util.List;

public class currentUser {


    interestCharge bankInterest = new interestCharge();
    cardTransaction cardTransaction = new cardTransaction();
    deposit depositAmt = new deposit();

    protected String userAccountName = "";
    protected long userAccountID = 0;
    protected double userAccountBalance = 0;
    protected String cardNumber = "0";
    protected int pin = 0;

    private double charges;
    private double cashback;


    public currentUser(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin) {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }


    //setters

    protected void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    protected void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    protected void setUserAccountBalance(double accountBalance) {
        this.userAccountBalance = accountBalance;
    }

    protected void setUserCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    protected String getUserAccountName() {
        return this.userAccountName;
    }

    public double getUserAccountBalance() {
        return this.userAccountBalance;
    }

    public double savingAccountBalance() {
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

        if (customerAccountBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else if (amount % 5 != 0)
            System.out.println("Please correct the amount of value. It should be multiple of 5");

        else if(customerAccountBalance< amount)
            System.out.println("Insufficient amount");
        else {
            charges = amount * bankInterest.interestCharge(amount);
            //invoke withDrawInfo
                withDrawInfo(cardTransaction.withDraw(customerAccountBalance,amount),charges);
            }
        }

    protected void swipe(double customerAccountBalance, double amount)
    {
        if (customerAccountBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else if(customerAccountBalance< amount)
            System.out.println("Insufficient amount");

        else
        {
            cashback = amount*0.01;
            //invoke swipeInfo
            swipeInfo(cardTransaction.swipe(customerAccountBalance,amount,cashback),cashback);
        }
    }

    public void deposit(double customerAccountBalance, double depositAmount) {

        this.userAccountBalance = depositAmt.deposit(customerAccountBalance,depositAmount);
        System.out.println("Deposited Amount: "+depositAmount+"\nAccount Balance: USD "+this.userAccountBalance);

    }

    //info methods
    private void withDrawInfo(double updatedBalance, double charges)
    {
        this.userAccountBalance = updatedBalance;
        System.out.println("Charges: "+ charges+"\nAccount Balance: USD "+this.userAccountBalance);
    }

    private void swipeInfo(double updateBalance, double cashback)
    {
        this.userAccountBalance= updateBalance;
        System.out.println("Cashback: "+ cashback+"\nAccount Balance: USD "+this.userAccountBalance);
    }

}
