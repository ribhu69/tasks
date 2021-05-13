import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<userDetails> userList = new ArrayList<>();
        userDetails customer1 = new userDetails();
        userDetails customer2 = new userDetails();
        userDetails customer3 = new userDetails();
        userDetails customer4 = new userDetails();

        userDetails customer = new userDetails();

        customer1.userDetail("Manoj Prasad", 10112525, 250, "111111111111");

        customer2.userDetail("Vijay Devganj", 10112526, 500, "22222222222");

        customer3.userDetail("Harish Bajaj", 10112527, 750, "333333333333");

        customer4.userDetail("Kartar Singh", 10112528, 1000, "444444444444");

        userList.add(customer1);
        userList.add(customer2);
        userList.add(customer3);
        userList.add(customer4);

//        c.swipe(customer1, 50);
//        c.withDraw(customer1,50);

//
//        System.out.println("Enter Card Number");
//        long cardNumber = sc.nextLong();
//        for(int i=0;i<userList.size();i++)
//        {
//            if(String.valueOf(cardNumber).length()==12 && (String.valueOf(cardNumber).equals(userList.get(i).getCardNumber())))
//            {
//                customer = userList.get(i);
//                break;
//            }
//        }
//        System.out.println("Welcome "+customer.getUserAccountName());
//        System.out.println("Welcome "+customer.getAccountId());

        cardTransaction newCardTransaction = new cardTransaction();
        System.out.println("Choose from below Services\n1) Balance Enquiry\n2)Withdraw Amount\n" +
                "3)Deposit Amount\n4)Debit-Card Banking");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                customer1.getAccountBalance();
                break;

            case 2:
                System.out.println("Enter Amount(in denominations of 5)");
                newCardTransaction.withDraw(customer1, sc.nextInt());
                break;

            case 3:
                deposit newDeposit = new deposit();
                System.out.println("Enter Amount");
                newDeposit.deposit(customer1, sc.nextDouble());
                break;

            case 4:
                System.out.println("Enter Amount");

                System.out.println("Enter amount");
                newCardTransaction.swipe(customer1, sc.nextDouble());
                break;

            default:
                System.out.println("Enter a valid Choice");

        }

    }
}
