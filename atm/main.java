import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("WELCOME TO XYZ BANK.\nEnter Card Number\n");
        String cardNumber = sc.nextLine();

        if(cardNumber.length()==12)
        {
            userAccount customer = new userAccount();
            System.out.println("Choose from below Services\n1) Balance Enquiry\n2)Withdraw Amount\n" +
                    "3)Deposit Amount\n4)Debit-Card Banking");
            int option=sc.nextInt();
            switch (option)
            {
                case 1:
                    customer.getAccountBalance();
                    break;

                case 2:
                    System.out.println("Enter Amount(in denominations of 5)");
                    customer.withdrawAmount(sc.nextInt());
                    break;

                case 3:
                    System.out.println("Enter amount to Deposit");
                    customer.depositAmount(sc.nextDouble());
                    break;

                case 4:
                    System.out.println("Enter Amount");
                    customer.swipe(sc.nextDouble());
                    break;

                default:
                    System.out.println("Enter a valid Choice");

            }
        }
        else
            System.out.println("Invalid Card Number");
    }
}
