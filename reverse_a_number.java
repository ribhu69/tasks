import java.util.Scanner;

public class basics {

    static int reverseNumber(int number)
    {
        int rev=0, temp;
        while(number>0)
        {
            temp = number % 10;
            rev= rev * 10 + temp ;
            number /= 10;
        }
        return rev;
    }

    static double reverseNumber (double number)
    {
        int flag=0, decimalCount, reverseCount , lengthNumber ;
        if(number<0) {
            number *= -1;
            flag = 1;
        }

        //String implementation
//        String floatingNum = String.valueOf(number);
//        StringBuilder floatingNumber = new StringBuilder(floatingNum);
//        floatingNumber = floatingNumber.reverse();
//        floatingNum = floatingNumber.toString();
//        number = Double.parseDouble(floatingNum);

        String floatingNum = String.valueOf(number);
        lengthNumber = floatingNum.length();
        decimalCount = (floatingNum.length()-floatingNum.indexOf("."))-1;
        reverseCount = decimalCount;
        while(decimalCount>0)
        {
            number*=10;
            decimalCount--;
        }
        number=Math.round(number);
        number = reverseNumber((int)number);
        reverseCount = lengthNumber-reverseCount;
        reverseCount-=1;
        while(reverseCount>0)
        {
            number/=10;
            reverseCount--;
        }

        if(flag==1)
        {
            number *= -1;
        }

        return number;
    }
    public static void main(String[] args)
    {
        int number, option;
        double floatingNumber;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the type of number to convert");
        System.out.println("1. Integer \n2. Float");
        option = sc.nextInt();

        switch(option)
        {
            case 1:
                System.out.println("Enter the number");
                number=sc.nextInt();
                if(number>0)
                {
                    System.out.println(reverseNumber(number));
                }
                else
                {
                    number *= -1;
                    System.out.println(-1 * (reverseNumber(number)));

                }
                break;
            case 2:

                System.out.println("Enter the number");
                floatingNumber = sc.nextDouble();
                System.out.println(reverseNumber(floatingNumber));
                break;

            default:
                System.out.println("Enter a Valid Input");
        }

    }
}






