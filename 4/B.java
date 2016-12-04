import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
public class B {
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        while(sc.hasM()) {
            String s = sc.n();
            int i = 0;
            for(;;i++) {
                if(s.charAt(i) == '-') continue;
                if(s.charAt(i) >= '0' && s.charAt(i) <= '9')break;
            }    
            int x = Integer.parseInt(s.substring(i,s.length()-7));
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<i; j++) {
                if(s.charAt(j) == '-') sb.append(' ');
                else {
                    char real = (char) ((x + s.charAt(j) - 'a')%26 + 'a');
                    sb.append(real);
                }
            }
            if(sb.toString().contains("north"))
                System.out.println(sb.toString() + x);
        }
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