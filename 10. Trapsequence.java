import java.util.*;

class basics {
    public static List<Integer> trapSeq = new ArrayList<>();

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

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int n1,n2,y;
        n1=sc.nextInt();
        n2=sc.nextInt();
        y=sc.nextInt();

        for(int i=n1;i<=n2;i++)
        {
            int x=i;
            while(String.valueOf(x).length()!=1)
                x = digitSum(x);

            if(y<3)
            {
                y=3;
            }
            if(x>0 && ((x==y) || multiple(x,y)))
            {
                trapSeq.add(i);
                y+=2;
            }
            else
            {
                y-=1;
            }
        }
        if(trapSeq.size()>0)
            System.out.println(trapSeq);
        else
            System.out.println("NONE");
    }
}
