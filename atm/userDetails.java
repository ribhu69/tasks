public class userDetails {

    private String userAccountName;
    private long userAccountID;
    private double userAccountBalance;
    private String cardNumber;
    private int pin;


    public userDetails(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin) {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
        this.pin=pin;

    }

    public void getAccountBalance() {
        System.out.println("Account Balance : " + this.userAccountBalance);
    }



    public String getCardNumber()
    {
        return this.cardNumber;
    }

    public String getUserAccountName()
    {
        return this.userAccountName;
    }

    public long getUserAccountID()
    {
        return this.userAccountID;
    }

    public int getPin()
    {
        return this.pin;
    }

    public double savingAccountBalance() {
        return this.userAccountBalance;
    }

    public void swipeTransactionInfo(double cashback) {
        swipeTransaction(cashback);
    }

    private void swipeTransaction(double cashback) {
        System.out.println("Cashback :" + cashback + "\nBalance USD: " + this.userAccountBalance);
    }

    public void withDrawTransactionInfo(int withdrawAmt, double charges) {
        withDrawTransaction(withdrawAmt, charges);
    }

    private void withDrawTransaction(int withdrawAmt, double charges) {
        System.out.println("Withdrawn Amount: " + withdrawAmt + "\nCharges :" + charges + "\nBalance USD: " + this.userAccountBalance);
    }

    public void updateAccountBalance(double updatedBalance) {
        this.userAccountBalance = updatedBalance;
        System.out.println("Account Balance :" + this.userAccountBalance);
    }

    public void setUserAccountBalance(double newBalance)
    {
        this.userAccountBalance= newBalance;
    }

}
