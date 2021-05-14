import com.bank.userDetails;

import java.util.List;
import java.util.Scanner;
import com.bank.bankAccounts;
import com.bank.*;
public class atm extends currentUser{

    public atm(String userAccountName, long userAccountId, double userAccountBalance, String cardNumber, int pin) {
        super(userAccountName, userAccountId, userAccountBalance, cardNumber, pin);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        atm customer = new atm("0",0,0,"0",0);
        //accounts of type bankAccounts
        bankAccounts accounts = new bankAccounts("0",0,0,"0",0);
        accounts.initiateAccounts();


        List<userDetails> allAccounts = accounts.getAccountsTotal();

        System.out.println("Enter Card Number");
        String cardNumber = sc.nextLine();
        System.out.println("Enter pin");

        userDetails u =  accounts.isAccountValid(cardNumber,sc.nextLine());

        if(u!=null)
        {
            customer.setUserAccountName(u.getUserAccountName());
            customer.setUserAccountBalance(u.getUserAccountBalance());
            customer.setUserAccountID(u.getUserAccountID());
            customer.setUserCardNumber(u.getUserCardNumber());
            u=null;


            System.out.println("Welcome "+customer.getUserAccountName());
            //customer fixed

            int option;
            do {

                System.out.println("Choose from below Services\n1) Balance Enquiry\n2)Withdraw Amount\n" +
                        "3)Deposit Amount\n4)Debit-Card Banking\n5) Exit");
                option = sc.nextInt();

                switch (option) {
                    case 1: //balance enquiry
                        customer.accountBalance();
                        break;

                    case 2: //withdraw amount
                        System.out.println("Enter Amount(in denominations of 5)");
                        customer.withDraw(customer.savingAccountBalance(), sc.nextInt());
                        break;

                    case 3: //deposit amount
                        System.out.println("Enter Amount");
                        customer.deposit(customer.getUserAccountBalance(),sc.nextDouble());
                        break;

                    case 4: //debit card banking
                        System.out.println("Enter Amount");
                        customer.swipe(customer.savingAccountBalance(), sc.nextDouble());
//                    user.completeTransaction(customer,cardNumber);
                        break;

                    case 5: //exit
                        System.out.println("Thank You for Banking with Us!");
                        break;

                    default:
                        System.out.println("Enter a valid Choice");

                }

            }while(option!=5);

            //update the account details once exited.
                customer.completeTransaction(allAccounts,cardNumber);
                accounts.updateUserDetails(customer.getUserAccountBalance(),cardNumber);
                //setting values to null
                customer=null;
        }
        else
        {
            System.out.println("Please check your details");
        }

        }
    }
