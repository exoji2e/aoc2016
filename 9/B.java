import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.PriorityQueue;
public class B {
    private static class Pair implements Comparable<Pair>{
        int pos;
        long fac;
        public Pair(int pos, long fac) {
            this.pos = pos; this.fac = fac;
        }
        public int compareTo(Pair p) {
            return pos - p.pos;
        }
    }
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        String s = sc.n();
        long count = 0;
        PriorityQueue<Pair> heap = new PriorityQueue<>();
        long factor = 1;
        for(int i = 0; i<s.length(); i++) {
            factor = reduceF(heap, i, factor);
            if(s.charAt(i)=='(') {
                int j = i + 1;
                for(; ; j++) {
                    factor = reduceF(heap, i, factor);
                    if(s.charAt(j) == ')') break;
                }
                String str = s.substring(i+1,j);
                String a[] = str.split("x");
                int jmp = Integer.parseInt(a[0]);
                int mult = Integer.parseInt(a[1]);
                factor *= mult;
                heap.add(new Pair(j+jmp+1, mult));
                i = j;
            }
            else count+=factor;
        }
        System.out.println(count);
    }
    private static long reduceF(PriorityQueue<Pair> heap, int i, long f) {
        while(!heap.isEmpty()) {
            if(heap.peek().pos != i) return f;
            else {
                Pair p = heap.poll();
                f /= p.fac;
            }
        }
        return f;
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