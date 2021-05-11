import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main
{
    static List<String> output = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string");
        String str= sc.nextLine();
        str=str.toLowerCase();
        int l=str.length();
        int i,j;

        for(i=0;i<l;i++)
        {
            for(j=l-1;j>i+1;j--)
            {
                String curr=str.substring(i,j);
                int len=j-i, f=0;
                for(int k=i+1;k+len<=l;k++)
                {
                    String tmp=str.substring(k,k+len);
                    if(tmp.equals(curr))
                    {
                        f=1;
                        break;
                    }
                }
                if(f!=0)
                {
                    output.add(curr);
                    break;
                }
            }
        }
        if(output.size()>1)
        {
            System.out.println(output);
        }
        else
        {
            System.out.println("none");
        }

    }
}
