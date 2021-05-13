public class cardTransaction {

    private double cashback;
    private double charges;
    private double transactionAmount;
    private double accBalance;
    private interestCharge bankInterest = new interestCharge();

    public void swipe(userDetails customer, double amount) {

        accBalance = customer.savingAccountBalance();

        charges = amount * bankInterest.interestCharge(amount);
        transactionAmount = amount + charges;
        cashback = amount * 0.01;

        if (accBalance - transactionAmount <= accBalance) {
            accBalance = accBalance - transactionAmount;
            accBalance += cashback;
            customer.updateAccountBalance(accBalance);
            customer.swipeTransactionInfo(cashback);
        }
    }

    public void withDraw(userDetails customer, int amount) {
        accBalance = customer.savingAccountBalance();
        if (accBalance < 100)
            System.out.println("Minimum Account Balance is insufficient for your transactions.");

        else if (amount % 5 != 0)
            System.out.println("Please correct the amount of value. It should be multiple of 5");

        else {
            charges = amount * bankInterest.interestCharge(amount);
            transactionAmount = amount + charges;
            if (accBalance - transactionAmount <= accBalance) {
                accBalance = accBalance - transactionAmount;
                customer.updateAccountBalance(accBalance);
                customer.withDrawTransactionInfo(amount, charges);
            }
        }
    }
}
