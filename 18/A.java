import java.util.Scanner;
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        boolean[][] b = new boolean[s.length() + 2][n];
        int count = 0;
        for(int i = 0; i<s.length(); i++) {
            b[i+1][0] = s.charAt(i) == '^';
            if(!b[i+1][0]) count++;
        }
        for(int j = 1; j<n; j++) {
            for(int i = 1; i<=s.length(); i++) {
                b[i][j] = trap(b[i-1][j-1], b[i][j-1], b[i+1][j-1]);        
                if(!b[i][j]) count++;
            }
        }
        System.out.println(count);
    }
    public static boolean trap(boolean l, boolean c, boolean r) {
        return (l && c && !r) || (!l && c && r) || (l && !c && !r) || (!l && !c && r);
    }
}
