package com.bank.classes;

public class WithdrawInfo implements com.bank.interfaces.transactionInfo.WithdrawInfo {
    @Override
    public void withdrawTransactionInfo(double accountBalance, double charges) {
        System.out.println("Charges: "+charges+"\nAccount Balance: USD "+accountBalance);

    }
}
