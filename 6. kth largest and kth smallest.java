import java.util.*;

class basics
{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many Numbers");
        int n = sc.nextInt();
        Set<Integer> set1= new TreeSet<>();
        System.out.println("enter the numbers");
        for(int i=0;i<n;i++)
        {
            set1.add(sc.nextInt());
        }
        System.out.println("Enter kth value");
        n=set1.size();
        int k=sc.nextInt();
        if(k>n)
        {
            System.out.println("Invalid Input");
        }
        List<Integer> list1 = new ArrayList<>(set1);
        System.out.println("Kth smallest element is "+ list1.get(k-1));
        System.out.println("Kth largest element is "+ list1.get(n-k));

    }
}
