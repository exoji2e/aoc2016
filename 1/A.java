import java.util.Scanner;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String a[] = s.split(", ");
        int dir = 1;
        int x = 1000;
        int y = 1000;
        int[][] v = new int[2001][2001];
        v[x][y] = 1;
        for(int i = 0; i<a.length; i++) {
            if(a[i].charAt(0) == 'R') {
                dir = (dir + 3)%4;
            } else {
                dir = (dir + 1)%4;
            }
            int diff = Integer.parseInt(a[i].substring(1));
            if(dir == 0) x += diff;
            else if(dir == 1) y += diff;
            else if(dir == 2) x -= diff;
            else y -= diff;
        }
        print(x,y);
    }
    public static void print(int x, int y) {
        System.out.println(Math.abs(x-1000)  + Math.abs(y-1000));
    }
}
