import java.util.*;

class basics {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String value = sc.nextLine();
        System.out.print("Reversed String is: ");
        for(int i = value.length()-1;i>=0;i--)
            System.out.print(value.charAt(i));
    }
}
