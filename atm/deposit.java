public class deposit {
    private double accBalance;

    public void deposit(userDetails customer, double depositAmount) {
        accBalance = customer.savingAccountBalance();
        accBalance += depositAmount;
        customer.updateAccountBalance(accBalance);
    }
}
