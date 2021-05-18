package com.bank.classes;
import com.bank.interfaces.transactions.*;
public class WithdrawTransaction implements WithdrawTransactionInterface {

    InterestCharge bankInterest = new InterestCharge();
    WithdrawInfo withdrawInfo = new WithdrawInfo();

    @Override
    public double withdrawTransaction(double customerAccountBalance, int amount) {
        double accBalance = customerAccountBalance;
        double charges = amount * bankInterest.interestCharge(amount);
        double transactionAmount = amount + charges;
            if (accBalance - transactionAmount <= accBalance) {
                accBalance = accBalance - transactionAmount;
                withdrawInfo.withdrawTransactionInfo(accBalance, charges);
                return accBalance;
            }
            return 0;
        }
    }

