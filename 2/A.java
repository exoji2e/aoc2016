import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
public class A {
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        int x = 0;
        int y = 0;
        while(sc.hasM()) {
            String s = sc.n();
            for(int i = 0; i<s.length(); i++) {
                if(s.charAt(i) == 'U' && y >= 0)  y--;
                if(s.charAt(i) == 'D' && y <= 0) y++;
                if(s.charAt(i) == 'L' && x >= 0) x--;
                if(s.charAt(i) == 'R' && x <= 0) x++;
            }
            System.out.print(x + 2 + 3*(y+1));        
        }
        System.out.println();
    }
    public static boolean accept(int x, int y) {
        return y!=2 && !(y ==1 && abs(x) ==1) && !(y == 0 && abs(x) ==2);
    }
    public static int abs(int a) {
        return Math.abs(a);
    }
}
class Sc {
    public Sc(InputStream i) {
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasM() {
        return peekToken() != null;
    }

    public int nI() {
        return Integer.parseInt(nextToken());
    }

    public double nD() { 
        return Double.parseDouble(nextToken());
    }

    public long nL() {
        return Long.parseLong(nextToken());
    }

    public String n() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null) 
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}