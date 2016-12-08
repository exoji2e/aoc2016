import java.util.Scanner;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean[][] screen = new boolean[50][6];
        while(sc.hasNext()) {
            String s = sc.nextLine();
            String a[] = s.split(" ");
            if(a.length == 2) {
                String a2[] = a[1].split("x");
                int x = Integer.parseInt(a2[0]);
                int y = Integer.parseInt(a2[1]);
                for(int i = 0; i<x; i++) {
                    for(int j = 0; j<y; j++) {
                        screen[i][j] = true;
                    }
                }
            } else if(a[2].charAt(0) == 'x'){
                boolean col[] = new boolean[6];
                int colid = Integer.parseInt(a[2].substring(2));
                int rot = Integer.parseInt(a[4]);
                for(int i = 0; i<6; i++) {
                    col[(i + rot)%6] = screen[colid][i];
                }
                for(int i = 0; i<6; i++) {
                    screen[colid][i] = col[i]; 
                }
            } else {
                boolean row[] = new boolean[50];
                int rowid = Integer.parseInt(a[2].substring(2));
                int rot = Integer.parseInt(a[4]);
                for(int i = 0; i<50; i++) {
                    row[(i + rot)%50] = screen[i][rowid];
                }
                for(int i = 0; i<50; i++) {
                    screen[i][rowid] = row[i]; 
                }
            }
        }
        int c = 0;
        StringBuilder sb = new StringBuilder();
        
        for(int j = 0; j<6; j++) {
            for(int i = 0; i<50; i++) {
                if(screen[i][j]) {
                    sb.append('*');
                    c++;
                }
                else sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(c);
        System.out.println(sb);
    }
}
