package com.bank.classes;

import com.bank.interfaces.currentUser.CurrentUserInterface;

public class CurrentUser implements CurrentUserInterface {

    private String userAccountName="";
    private double userAccountBalance=0;
    private String cardNumber="0";

    BankAccounts currentAccount = new BankAccounts();

    @Override
    public void swipe(double amount) {
        SwipeTransaction swipeAmount = new SwipeTransaction();
        if (this.userAccountBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else
        {
            double newBalance = swipeAmount.swipeTransaction(this.userAccountBalance,amount);
            if(newBalance!=0)
                this.userAccountBalance = newBalance;
            currentAccount.setUserAccountBalance(userAccountBalance,cardNumber);
        }

    }

    @Override
    public void withdraw(int amount) {
        WithdrawTransaction withdrawAmount = new WithdrawTransaction();
        if (this.userAccountBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else if (amount % 5 != 0)
            System.out.println("Please correct the amount of value. It should be multiple of 5");
        else
        {
            double newBalance = withdrawAmount.withdrawTransaction(this.userAccountBalance,amount);
            if(newBalance!=0)
                this.userAccountBalance = newBalance;
            currentAccount.setUserAccountBalance(userAccountBalance,cardNumber);
        }
    }

    @Override
    public void getAccountBalance() {
        System.out.println("Account Balance is: "+this.userAccountBalance);

    }

    @Override
    public String getAccountName() {
        return this.userAccountName;
    }

    @Override
    public void depositAmount(double amount) {
        this.userAccountBalance = this.userAccountBalance + amount;
        currentAccount.setUserAccountBalance(userAccountBalance,cardNumber);
        System.out.println("Account Balance: USD "+ this.userAccountBalance);

    }

    protected boolean isAccountValid(String cardNumber, int pin)
    {
        UserDetails existingUser = currentAccount.getUserAccount(cardNumber,pin);

        if(existingUser!=null) {

            this.cardNumber = existingUser.getCardNumber();
            this.userAccountBalance = existingUser.getUserAccountBalance();
            this.userAccountName = existingUser.getUserAccountName();
            return true;
        }
        return false;
    }

    protected void initializeAccounts()
    {
        currentAccount.initializeAllAccounts();
    }


}
