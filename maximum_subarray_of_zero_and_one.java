import java.util.Scanner;
class basics {
    static void findMaxSubArray(int arr[], int n)
    {
        int sum = 0, maxSize = -1, startIndex = 0, endIndex = 0;
        for (int i = 0; i < n - 1; i++) {
            if(arr[i]==1)
            {
                sum=1;
            }
            else
            {
                sum=-1;
            }
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == 0)
                    sum += -1;
                else
                    sum += 1;

                if (sum == 0 && (maxSize < j - i + 1)) {
                    maxSize = j - i + 1;
                    startIndex = i;
                }
            }
        }
        endIndex = startIndex + maxSize - 1;
        if (maxSize == -1)
            System.out.println("No sub-array found");
        else
            System.out.println(startIndex + " to " + endIndex);

    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("How many Numbers");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {

            arr[i]=sc.nextInt();
        }
        int size = arr.length;
        findMaxSubArray(arr, size);
    }
}
