package com.bank.classes;

public class SwipeInfo implements com.bank.interfaces.transactionInfo.SwipeInfo {
    @Override
    public void swipeTransactionInfo(double accountBalance, double cashback) {
        System.out.println("Cashback: "+cashback+"\nAccount Balance: USD "+accountBalance);
    }
}
