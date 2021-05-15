package com.bank;

public class cardTransaction {

    private double accBalance;
    private double amount;
    private double charges;
    private double cashback;
    private double transactionAmount;

    interestCharge bankInterest = new interestCharge();


    protected double swipe(double customerAccountBalance, double amount, double cashback)
    {
        accBalance=customerAccountBalance;
        charges = amount * bankInterest.interestCharge(amount);
        cashback = 0.01*amount;
        transactionAmount = amount + charges;
        if (accBalance - transactionAmount <= accBalance) {
            accBalance = accBalance - transactionAmount;
            accBalance += cashback;

//            swipeInfo();
        }
        return accBalance;
    }

    protected double withDraw(double customerAccountBalance, int amount) {


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

            }
        }
        return accBalance;
    }
}
