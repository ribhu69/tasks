import com.bank.classes.CurrentUser;


import java.util.Scanner;

public class Atm extends CurrentUser {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Card Number");
        String cardNumber = sc.nextLine();
        System.out.println("Enter pin");
        Atm customer = new Atm();
        customer.initializeAccounts();
        if(customer.isAccountValid(cardNumber,sc.nextInt()))
        {
            System.out.println("Welcome "+ customer.getAccountName());

            int option;
            do {

                System.out.println("Choose from below Services\n1) Balance Enquiry\n2)Withdraw Amount\n" +
                        "3)Deposit Amount\n4)Debit-Card Banking\n5) Exit");
                option = sc.nextInt();

                switch (option) {
                    case 1: //balance enquiry
                        customer.getAccountBalance();
                        break;

                    case 2: //withdraw amount
                        System.out.println("Enter Amount(in denominations of 5)");
                        customer.withdraw(sc.nextInt());
                        break;

                    case 3: //deposit amount
                        System.out.println("Enter Amount");
                        customer.depositAmount(sc.nextDouble());
                        break;

                    case 4: //debit card banking
                        System.out.println("Enter Amount");
                        customer.swipe(sc.nextDouble());
                        break;

                    case 5: //exit
                        System.out.println("Thank You for Banking with Us!");
                        break;

                    default:
                        System.out.println("Enter a valid Choice");

                }

            }while(option!=5);
        }
        else
            System.out.println("Please check your credentials");

    }
}
