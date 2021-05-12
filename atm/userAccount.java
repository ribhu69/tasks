public class userAccount {

    public static double accountBalance=250;
    public static double transactionAmt=0;
    public static double cashback=0;
    public static double charges=0;

    public void getAccountBalance()
    {
        System.out.println("Balance : USD "+accountBalance);
    }

    private void validTransaction(double transactionAmount ,double accountBalance,double charges)
    {
        if(accountBalance - (transactionAmount)  <= accountBalance)
        {
            userAccount.accountBalance = accountBalance - (transactionAmt+charges);
            System.out.println("Charges "+charges +"\nBalance USD "+ userAccount.accountBalance);
        }
        else {
            System.out.println("Insufficient balance");
        }
    }

    private void validTransaction(double transactionAmount, double accountBalance,double charges, double cashback)
    {

        if((accountBalance - (transactionAmount+charges))  <= accountBalance)
        {
            userAccount.accountBalance = accountBalance - (transactionAmt+charges);

            userAccount.accountBalance += cashback;
            System.out.println("Cashback "+cashback +"\nBalance USD "+ userAccount.accountBalance);
        }
        else {
            System.out.println("Insufficient balance");
        }
    }
    public void withdrawAmount(int amount)
    {
        try
        {
            if(accountBalance<100)
            {
                System.out.println("Minimum Account Balance is insufficient for your transactions.");
            }
            else if(amount%5!=0)
            {
                System.out.println("please correct the amount of value. It should be multiple of 5");
            }
            else
            {
                if(amount<=100)
                {
                    charges= amount*0.02;
                    transactionAmt = amount + charges ;
                }

                if(amount>100)
                {
                    charges=amount*0.04;
                    transactionAmt = amount+charges;

                }

                validTransaction(transactionAmt,userAccount.accountBalance,charges);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid Amount");
        }
    }

    public void swipe (double amount)
    {

            if(amount<=100)
            {
                charges= 0.02*amount;
                transactionAmt = amount ;
                cashback = amount * 0.01;
            }

            if(amount>100)
            {
                charges =0.04 * amount;
                transactionAmt = amount;
                cashback = amount * 0.01;

            }

            validTransaction(transactionAmt,userAccount.accountBalance, charges, cashback);

    }
    public void depositAmount(double amount)
    {
        userAccount.accountBalance += amount;
        System.out.println("Balance : USD "+userAccount.accountBalance);
    }
}
