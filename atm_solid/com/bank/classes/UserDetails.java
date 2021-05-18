package com.bank.classes;


public class UserDetails {

    private String userAccountName="0";
    private long userAccountID=0;
    private double userAccountBalance=0;
    private String cardNumber="0";
    private int pin=0;

    public UserDetails(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin)
    {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    protected String getCardNumber()
    {
        return this.cardNumber;
    }

    protected int getPin()
    {
        return this.pin;
    }

    public double getUserAccountBalance()
    {
        return this.userAccountBalance;
    }

    public String getUserAccountName()
    {
        return this.userAccountName;
    }

    protected void setUserAccountBalance(double accountBalance)
    {
        this.userAccountBalance = accountBalance;
    }

}
