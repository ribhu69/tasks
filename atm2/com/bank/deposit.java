package com.bank;

public class deposit {

    public double deposit(double customerBalance, double amount)
    {
        customerBalance += amount;
        return customerBalance;
    }
}
