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
        int wl = 8;
        int freq[][] = new int['z'+1][wl];
        while(sc.hasM()) {
            String s = sc.n();
            for(int i = 0; i<wl; i++) {
                freq[s.charAt(i)][i]++;
            }
        }
        for(int i = 0; i<wl; i++) {
            int max = 0;
            char maxc = ' ';
            for(char c = 'a'; c<='z'; c++) {
                if(freq[c][i] > max) {
                    max = freq[c][i];
                    maxc = c;
                }
            }
            System.out.print(maxc);
        }
        System.out.println();
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