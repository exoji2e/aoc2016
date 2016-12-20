import java.util.Scanner;
import java.util.TreeSet;
public class A {
    private static class segment implements Comparable<segment> {
        long s, e;
        public segment(long s, long e) {
            this.s= s; this.e = e;
        }
        public int compareTo(segment o) {
            if(s != o.s) return Long.compare(s, o.s);
            return Long.compare(e, o.e);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<segment> set = new TreeSet<>();
        while(sc.hasNext()) {
            String s = sc.next();
            String[] a = s.split("-");
            set.add(new segment(Long.parseLong(a[0]), Long.parseLong(a[1])));
        }
        long canidate = 0;
        for(segment s: set) {
            if(canidate >= s.s && canidate < s.e) {
                canidate = s.e + 1;
            } 
        }
        System.out.println(canidate);
    }
}
