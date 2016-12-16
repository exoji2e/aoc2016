import java.util.Scanner;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        int size = sc.nextInt();
        while(a.length() < size) {
            String b = copyinv(a);
            a = a + "0" + b;
        }
        a = a.substring(0,size);
        while(a.length()%2 == 0) {
            a = div(a);
        }
        System.out.println(a);
    }
    public static String copyinv(String a) {
        StringBuilder sb = new StringBuilder();
        for(int i = a.length() - 1; i>=0; i--) {
            if(a.charAt(i) == '0') sb.append('1');
            else sb.append('0');
        }
        return sb.toString();
    } 
    public static String div(String a) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<a.length(); i+=2) {
            if(a.charAt(i) == a.charAt(i+1)) sb.append(1);
            else sb.append(0);
        }
        return sb.toString();
    }
}
