package com.bank.classes;

public class WarningMessage extends ExitMessage {

    public void warnMessage()
    {
        super.showExitMessage();
        System.out.println("Beware of Fraud Calls");
    }
}
