package com.bank.classes;

public class InterestCharge {
    public double interestCharge(double amount)
    {
        if(amount<=100)
            return 0.02;
        else
            return 0.04;
    }
}
