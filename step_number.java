import java.util.Scanner;

public class basics
{
    static int getFirstNumber(int n)
    {
        while(n>=10)
            n/=10;
        return n;
    }
    static boolean isStepNumber (int n)
    {
        String l= String.valueOf(n);
        int prevDigit = -1;
        if(l.length()==1)
        {
            return true;
        }
        int lastDigit = n%10;
        int firstDigit = getFirstNumber(n);
        while (n > 0)
        {
            int currDigit = n % 10;
            if(Math.abs(firstDigit-lastDigit)!=1)
            {
                return false;
            }
            if (prevDigit != -1)
            {
                if (Math.abs(currDigit-prevDigit) != 1)
                    return false;
            }
            n /= 10;
            prevDigit = currDigit;
        }
        return true;
    }

    static void displayNum(int m,int n)
    {
        int c=0;
        for (int i = m; i <= n; i++) {
            if (isStepNumber(i))
            {
                System.out.print(i + " ");
                c += 1;
            }
        }
        if(c==0)
            System.out.println("No stepping numbers");
    }
  
    public static void main(String[] args) {
        int m,n;
        Scanner sc=new Scanner(System.in);
        System.out.println("enter a range for stepping numbers");
        m=sc.nextInt();
        n=sc.nextInt();
        displayNum(m,n);
    }
}
