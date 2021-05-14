import java.util.*;

public class bankAccounts {
    private List<userDetails> accounts = new ArrayList<>();

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

    public userDetails isAccountValid(String cardNumber, String pin)
    {
        for(userDetails i: accounts)
        {
            if(i.getCardNumber().equals(cardNumber) && String.valueOf(i.getPin()).equals(pin))
            {
                return new userDetails(i.getUserAccountName(),i.getUserAccountID(),i.savingAccountBalance(),i.getCardNumber(),i.getPin());
            }
        }
        System.out.println("Please Check your Details");
        return null;
    }

    public void completeTransaction(userDetails currentCustomer, String cardNumber)
    {
        updateAccBal(currentCustomer,cardNumber);
    }
    private void updateAccBal(userDetails currentCustomer, String cardNumber )
    {
        for(int i=0;i<accounts.size();i++)
        {
            if(accounts.get(i).getCardNumber().equals(cardNumber))
            {
                double newBalance = currentCustomer.savingAccountBalance();
                accounts.get(i).setUserAccountBalance(newBalance);
            }
        }
    }

}
