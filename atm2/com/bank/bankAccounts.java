package com.bank;

import java.util.*;

public class bankAccounts extends userDetails{

    private List<userDetails> accounts = new ArrayList<>();

    public bankAccounts(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin) {
        super(userAccountName, userAccountId, userAccountBalance, cardNumber, pin);
    }


    public List<userDetails> getAccountsTotal()
    {
        return accounts;
    }
    public void initiateAccounts()
    {
        getAccounts();
    }

    public List<userDetails> getAccounts()

    {
        accounts.add(new userDetails("Manoj Prasad", 10112525, 250, "101",1212));
        accounts.add(new userDetails("Vijay Devganj", 10112526, 500, "102",1313));
        accounts.add(new userDetails("Harish Bajaj", 10112527, 750, "103",1414));
        accounts.add(new userDetails("Kartar Singh", 10112528, 1000, "104",1515));

        return accounts;

    }

    public void updateUserDetails(double accountBalance, String cardNumber)
    {
        for(userDetails i: accounts)
        {
            if(i.getCardNumber().equals(cardNumber))
            {
                i.setUserAccountBalance(accountBalance);
            }
        }
    }

    public userDetails isAccountValid(String cardNumber, String pin)
    {
        for(userDetails i: accounts)
        {
            if(i.getCardNumber().equals(cardNumber) && String.valueOf(i.getPin()).equals(pin))
            {

                return i;

            }
        }
        return null;
    }


}

