import java.util.*;

class basics
{

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        List<Character> list1=new ArrayList<>();
        int n = sc.nextInt();
        for(int i=0;i<n;i++)
        {
            list1.add(sc.next().charAt(0));
        }
        Set<Character> set1 = new HashSet<>();
        for(int i=0;i<n;i++)
        {
            if(!set1.contains(list1.get(i)))
            {
                int frequency = Collections.frequency(list1, list1.get(i));
                for(int j=0;j<frequency;j++)
                {
                    System.out.print(list1.get(i).toString()+" ");
                }
                set1.add(list1.get(i));
            }
        }
    }
}
