import java.util.*;

class basics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        StringBuffer stringValue = new StringBuffer();
        stringValue.append(sc.nextLine());
        stringValue.reverse();
        System.out.println("Reversed String: " + stringValue.toString());
    }
}
