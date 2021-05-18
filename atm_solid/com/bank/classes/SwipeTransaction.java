package com.bank.classes;
import com.bank.interfaces.transactions.*;
public class SwipeTransaction implements SwipeTransactionInterface {

    InterestCharge bankInterest = new InterestCharge();
    SwipeInfo swipeInfo = new SwipeInfo();

    @Override
    public double swipeTransaction(double customerAccountBalance, double amount) {
        double accBalance = customerAccountBalance;
        double charges = amount * bankInterest.interestCharge(amount);
        double cashback = 0.01 * amount;
        double transactionAmount = amount + charges;
        if (accBalance - transactionAmount <= accBalance) {
            accBalance = accBalance - transactionAmount;
            accBalance += cashback;
            swipeInfo.swipeTransactionInfo(accBalance, cashback);
            return accBalance;
        }
        return 0;
    }


}
