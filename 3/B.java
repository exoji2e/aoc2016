import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.Arrays;
public class B {
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        long c = 0;
        int sz = 10000; //should be enough to contain file
        int c1[] = new int[sz];
        int c2[] = new int[sz];
        int c3[] = new int[sz];
        int count = 0;
        while(sc.hasM()) {
            c1[count] = sc.nI();
            c2[count] = sc.nI();
            c3[count++] = sc.nI();
        }

        for(int i = 0; i<count; i+=3) {
            if(triangle(c1,i)) c++;
            if(triangle(c2,i)) c++;
            if(triangle(c3,i)) c++;
        }
        System.out.println(c);
    }
    public static boolean triangle(int[] c, int i) {
        int x[] = new int[3];
        x[0] = c[i];
        x[1] = c[i+1];
        x[2] = c[i+2];
        Arrays.sort(x);
        return x[0] + x[1] > x[2];
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