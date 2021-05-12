import java.util.*;

class basics {
    public static List<Integer> trapSeq = new ArrayList<>();
    static int x,y;

    static boolean multiple(int x, int y)
    {
        int a,b;
        if(x>y) {
            a = x;
            b = y;
        }
        else {
            a=y;
            b=x;
        }
        if(a%b!=0)
            return false;

        return true;

    }
    static int digitSum(int n)
    {
        int sum=0,temp;
        while(n>0)

        {
            temp= n%10;
            sum+=temp;
            n/=10;

        }
        return sum;
    }

    static boolean questionCondition(int x,int y) {
        if (x == y || multiple(x, y))
            return true;
        else
            while (String.valueOf(x).length() != 1) {
                x = digitSum(x);
                if (multiple(x, y))
                    return true;
            }

            return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n1, n2;
        n1=sc.nextInt();
        n2=sc.nextInt();
        y=sc.nextInt();
        for(int i=n1;i<=n2;i++)
        {
            x=i;
            if(y<3)
                y=3;

            if(questionCondition(x,y))
            {
                trapSeq.add(i);
                y+=2;
            }
            else
                y-=1;

        }
        if(trapSeq.size()>0)
            System.out.println(trapSeq);
        else
            System.out.println("NONE");
    }
}
