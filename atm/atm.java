import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        bankAccounts user = new bankAccounts();
        userDetails customer = new userDetails("0",0,0,"0",0);
        user.initiateAccounts();
        System.out.println("Enter Card Number");
        String cardNumber = sc.nextLine();
        System.out.println("Enter pin");
        customer = user.isAccountValid(cardNumber,sc.nextLine());
        System.out.println("Welcome "+customer.getUserAccountName());
//
      cardTransaction newCardTransaction = new cardTransaction();
        System.out.println("Choose from below Services\n1) Balance Enquiry\n2)Withdraw Amount\n" +
                "3)Deposit Amount\n4)Debit-Card Banking");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                customer.getAccountBalance();
                break;

            case 2:
                System.out.println("Enter Amount(in denominations of 5)");
                newCardTransaction.withDraw(customer, sc.nextInt());
                user.completeTransaction(customer,cardNumber);
                break;

            case 3:
                deposit newDeposit = new deposit();
                System.out.println("Enter Amount");
                newDeposit.deposit(customer, sc.nextDouble());
                user.completeTransaction(customer,cardNumber);
                break;

            case 4:
                System.out.println("Enter Amount");

                System.out.println("Enter amount");
                newCardTransaction.swipe(customer, sc.nextDouble());
                user.completeTransaction(customer,cardNumber);
                break;

            default:
                System.out.println("Enter a valid Choice");

        }

    }
}
