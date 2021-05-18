package com.bank.classes;

import com.bank.interfaces.bankAccounts.BankAccountsInterface;

import java.util.ArrayList;
import java.util.List;

public class BankAccounts implements BankAccountsInterface {

    private List<UserDetails> accounts = new ArrayList<>();

    public void initializeAllAccounts()
    {
        accounts.add(new UserDetails("Manoj Prasad", 10112525, 250, "101",1212));
        accounts.add(new UserDetails("Vijay Devganj", 10112526, 500, "102",1313));
        accounts.add(new UserDetails("Harish Bajaj", 10112527, 750, "103",1414));
        accounts.add(new UserDetails("Kartar Singh", 10112528, 1000, "104",1515));

    }

    @Override
    public UserDetails getUserAccount(String cardNumber, int pin) {
        for(UserDetails i: accounts)
        {

            if(i.getCardNumber().equals(cardNumber) && i.getPin()==pin)
            {
                return i;
            }
        }
        return null;
    }

    @Override
    public void setUserAccountBalance(double accountBalance,String cardNumber) {
        for(UserDetails i: accounts)
        {

            if(i.getCardNumber().equals(cardNumber))
            {
                i.setUserAccountBalance(accountBalance);
                break;
            }
        }
    }

}
