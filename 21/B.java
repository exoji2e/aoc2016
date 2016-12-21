import java.util.Scanner;
import java.util.LinkedList;
public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "fbgdceah";
        LinkedList<String> input = new LinkedList<>();
        while(sc.hasNext()) {
            input.addFirst(sc.nextLine());
        }
        for(String cmd: input) {
            String[] a = cmd.split(" ");
            if(a[0].equals("swap")) {
                if(a[1].equals("position")) {
                    s = swapp(s, Integer.parseInt(a[2]), Integer.parseInt(a[5]));
                } else {
                    s = swapl(s, a[2].charAt(0), a[5].charAt(0));
                }
            } else if(a[0].equals("rotate")) {
                if(a[1].equals("right")) {
                    s = rotatel(s, Integer.parseInt(a[2]));
                } else if(a[1].equals("left")) {
                    s = rotater(s, Integer.parseInt(a[2]));                   
                } else {
                    s = rotatex(s, a[6].charAt(0));
                }
            } else if(a[0].equals("move")) {
                s = move(s, Integer.parseInt(a[5]), Integer.parseInt(a[2]));
            } else {
                s = reverse(s, Integer.parseInt(a[2]), Integer.parseInt(a[4]));
            }
        }
        System.out.println(s);
    }
    public static String swapp(String s, int x, int y) {
        int min = Math.min(x,y);
        int max = Math.max(x,y);
        return s.substring(0,min) + s.charAt(max) + s.substring(min+1, max) + s.charAt(min) + s.substring(max + 1);
    }
    public static String swapl(String s, char a, char b) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) == a) sb.append(b);
            else if(s.charAt(i) == b) sb.append(a);
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static String rotater(String s, int rot) {
        rot = rot%s.length();
        String x = s.substring(s.length() - rot);
        String y = s.substring(0, s.length() - rot);
        return x + y;
    }
    public static String rotatel(String s, int rot) {
        rot = rot%s.length();
        String x = s.substring(0, rot);
        String y = s.substring(rot);
        return y + x;
    }
    public static String rotatex(String s, char x) {
        int i = s.indexOf(x);
        for(int a = 0; a<s.length(); a++) {
            if(newindex(a, s.length()) == i) return rotater(s, s.length() + a - i);
        }
        return null;
    }
    public static int newindex(int i, int l) {
        return (2*i + ((i>3)?2:1))%l;
    }
    public static String move(String s, int x, int y) {
        String small = s.substring(0,x) + s.substring(x+1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<small.length(); i++) {
            if(i == y) sb.append(s.charAt(x));
            sb.append(small.charAt(i));
        }
        if(y == small.length()) sb.append(s.charAt(x));
        return sb.toString();
    }
    public static String reverse(String s, int x, int y) {
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(min, max+1));
        return s.substring(0,min) + sb.reverse().toString() + s.substring(max+1);
    }
}
