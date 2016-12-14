import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.TreeSet;

public class A {
    private static class MD implements Comparable<MD>{
        int i;
        int t;
        public MD(int i, int t) {
            this.i = i; this.t = t;
        }
        public int compareTo(MD m) {
            return i - m.i;
        }
    }
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        LinkedList<MD> que = new LinkedList<>();
        TreeSet<MD> keys = new TreeSet<>();
        String[] fives = new String[16];
        String[] threes = new String[16];
        for(int i = 0; i<16; i++) {
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k<5; k++) {
                if(k == 3) threes[i] = sb.toString();
                if(i>9) sb.append((char)('a' + (char)(i - 10)));
                else sb.append(i);
            }
            fives[i] = sb.toString();
        }
        String start = sc.n();
        int count = 0;
        int k = 0;
        for(; ; k++) {
            if(!que.isEmpty() && que.getFirst().i + 1000 < k) que.removeFirst();
            if(keys.size() == 64 && keys.last().i + 1000 < k) {
                System.out.println(keys.last().i);
                return;
            }
            String str = start + k;
            String MD5;
            if(args.length > 0) MD5 = MD5_2017(str);
            else  MD5 = MD5(str);

            for(int i = 0; i<16; i++) {
                if(MD5.contains(fives[i])) {
                    Iterator<MD> itr = que.iterator();
                    while(itr.hasNext()) {
                        MD m = itr.next();
                        if(m.t == i) {
                            itr.remove();
                            count++;
                            keys.add(m);
                            if(keys.size() > 64) keys.pollLast();
                        }
                    }
                    
                }
            }
            int first = -1;
            int ind = Integer.MAX_VALUE;
            for(int i = 0; i<16; i++) {
                int f = MD5.indexOf(threes[i]);
                if(f != -1 && f<ind) {
                    first = i;
                    ind = f;
                }
            }
            if(first != -1) {
                que.addLast(new MD(k, first));
            }
        }
    }
    public static String MD5_2017(String md5) {
        for(int i = 0; i<2017; i++) {
            md5 = MD5(md5);
        }
        return md5;
    }
    public static String MD5(String md5) {
       try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {}
        return null;
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
