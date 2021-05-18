package com.bank.interfaces.bankAccounts;

import com.bank.classes.UserDetails;

public interface BankAccountsInterface {
    UserDetails getUserAccount(String cardNumber, int pin);

    void setUserAccountBalance(double accountBalance, String cardNumber);
}
