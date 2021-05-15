package com.bank;

public class interestCharge {

    public double interestCharge(double amount)
    {
        if(amount<=100)
            return 0.02;
        else
            return 0.04;
    }
}
