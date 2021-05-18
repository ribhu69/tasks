package com.bank.interfaces.currentUser;

public interface CurrentUserInterface {
    void swipe(double amount);
    void withdraw(int amount);
    void getAccountBalance();
    String getAccountName();
    void depositAmount(double amount);
}
