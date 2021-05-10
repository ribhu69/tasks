import java.util.Scanner;

class basics
{

    static void nextGreater(int arr[], int n)
    {
        int next, i, j;
        for (i=0; i<n; i++)
        {
            next = -1;
            for (j = i+1; j<n; j++)
            {
                if (arr[i] < arr[j] && (arr[j]<next || next==-1))
                {
                    next = arr[j];
                }
            }
            System.out.print(next+" ");
        }
    }

    public static void main(String[] args) {
        int i,n;
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        int[] arr= new int[n];
        for(i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        nextGreater(arr,n);
    }
}
