public class userDetails {

    private String userAccountName;
    private long userAccountID;
    private double userAccountBalance;
    private String cardNumber;

    public void userDetail(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber) {
        this.userAccountName = userAccountName;
        this.userAccountID = userAccountId;
        this.userAccountBalance = userAccountBalance;
        this.cardNumber = cardNumber;
    }

    public void getAccountBalance() {
        System.out.println("Account Balance : " + this.userAccountBalance);
    }

    public double savingAccountBalance() {
        return this.userAccountBalance;
    }

    public void swipeTransactionInfo(double cashback) {
        swipeTransaction(cashback);
    }

    public void withDrawTransactionInfo(int withdrawAmt, double charges) {
        withDrawTransaction(withdrawAmt, charges);
    }

    private void withDrawTransaction(int withdrawAmt, double charges) {
        System.out.println("Withdrawn Amount: " + withdrawAmt + "\nCharges :" + charges + "\nBalance USD: " + this.userAccountBalance);
    }

    private void swipeTransaction(double cashback) {
        System.out.println("Cashback :" + cashback + "\nBalance USD: " + this.userAccountBalance);
    }

    public void updateAccountBalance(double updatedBalance) {
        this.userAccountBalance = updatedBalance;
        System.out.println("Account Balance :" + this.userAccountBalance);
    }
}
