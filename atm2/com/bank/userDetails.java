package com.bank;

public class userDetails {
    private String userAccountName="";
    private long userAccountID=0;
    private double userAccountBalance=0;
    private String cardNumber="0";
    private int pin=0;

    public userDetails(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin)
    {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }


    protected int getPin(){
        return this.pin;
    }


    public void setUserAccountBalance(double accountBalance)
    {
        this.userAccountBalance= accountBalance;
    }

    protected String getCardNumber()
    {
        return this.cardNumber;
    }

    public String getUserAccountName()
    {
        return this.userAccountName;
    }

    protected double savingAccountBalance() {
        return this.userAccountBalance;
    }

    public double getUserAccountBalance()
    {
        return this.userAccountBalance;
    }

    public String getUserCardNumber() {return this.cardNumber;}

    public long getUserAccountID()
    {
        return this.userAccountID;
    }
}
