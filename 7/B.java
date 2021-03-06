import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.HashSet;
public class B {
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        int c = 0;
        while(sc.hasM()) {
            HashSet<String> aba = new HashSet<>();
            HashSet<String> bab = new HashSet<>();
            String s = sc.n();
            boolean has = false;
            int depth = 0;
            for(int i = 0; i<s.length()-2; i++) {
                if(s.charAt(i) == '[') depth++;
                if(s.charAt(i) == ']') depth--;
                if(isAba(s,i)) {
                    String x = s.substring(i,i+3);
                    String y = "" + x.charAt(1) + "" + x.charAt(0) + "" + x.charAt(1);
                    
                    if(depth == 0) {
                        if(bab.contains(y)) has = true;
                        aba.add(x);
                    }else {
                        if(aba.contains(y)) has = true;
                        bab.add(x);
                    }
                } 
            }

            if(has) c++;
        }

        System.out.println(c);

    }
    public static boolean isAba(String s, int i) {
        return c(s, i) == c(s, i+2) && c(s, i) != c(s, i+1) && c(s, i+1) != '[' && c(s, i+1) != ']';
    }
    private static char c(String s, int i) {
        return s.charAt(i);
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
