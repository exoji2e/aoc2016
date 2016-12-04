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
        int sum = 0;
        while(sc.hasM()) {
            int[] freq = new int['z'+1];
            String s = sc.n();
            int i = 0;
            for(;;i++) {
                if(s.charAt(i) == '-') continue;
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9')break;
                freq[s.charAt(i)]++;
            }
            boolean ok = true;
            for(int j = 0; j<5; j++) {
                int max = freq['a'];
                char maxi = 'a';
                for(char c = 'b'; c<='z'; c++) {
                    if(freq[c] > max) {
                        max = freq[c];
                        maxi = c;
                    }
                }
                if(maxi != s.charAt(s.length() - 6 + j)) {
                    ok = false;
                    break;
                }
                freq[maxi] = 0;
            }
            if(ok) {
                sum += Integer.parseInt(s.substring(i,s.length()-7));
            }
        }
        System.out.println(sum);

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