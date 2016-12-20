import java.util.Scanner;
import java.util.TreeSet;
public class B {
    private static class segment implements Comparable<segment> {
        long s, e;
        public segment(long s, long e) {
            this.s= s; this.e = e;
        }
        public int compareTo(segment o) {
            if(e < o.s) return -1; 
            if(s > o.e) return 1;
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<segment> set = new TreeSet<>();
        while(sc.hasNext()) {
            String s = sc.next();
            String[] a = s.split("-");
            segment seg = new segment(Long.parseLong(a[0]), Long.parseLong(a[1]));
            segment rm = set.ceiling(seg);
            while(rm!= null && rm.compareTo(seg) == 0) {
                set.remove(rm);
                seg = new segment(Math.min(seg.s, rm.s), Math.max(seg.e,rm.e));
                rm = set.ceiling(seg);
            }
            set.add(seg);
        }
        long corr = 1L<<32;
        for(segment s: set) {
            corr -= (s.e - s.s + 1);    
        }
        System.out.println(corr);
    }
}
