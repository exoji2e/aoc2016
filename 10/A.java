import java.util.Scanner;
import java.util.TreeSet;
public class A {
    private static class Bot implements Comparable<Bot> {
        int lo = -1;
        int hi = -1;
        int items = 0;
        int id;
        int orgid;
        int loTo = -1;
        int hiTo = -1;
        public Bot(int i){
            id = i;
            orgid = i;
        }
        public void insert(int x) {
            if(lo == -1) lo = x;
            else if(lo<x) hi = x;
            else {
                hi = lo;
                lo = x;
            }
            items++;
        }
        public int compareTo(Bot b) {
            if(items != b.items) return items - b.items;
            return id - b.id;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Bot[] bots = new Bot[300];
        for(int i = 0; i<300; i++) {
            bots[i] = new Bot(i);
        }

        while(sc.hasNext()) {
            String s = sc.nextLine();
            String a[] = s.split(" ");
            if(s.charAt(0) == 'v') {
                int v = Integer.parseInt(a[1]);
                int b = Integer.parseInt(a[5]);
                bots[b].insert(v);
            } else {
                int b = Integer.parseInt(a[1]);
                int loTo = Integer.parseInt(a[6]);
                int hiTo = Integer.parseInt(a[11]);
                if(a[5].equals("bot")) bots[b].loTo = loTo;
                else bots[b].loTo = -loTo-1;
                if(a[10].equals("bot")) bots[b].hiTo = hiTo;
                else bots[b].hiTo = -hiTo-1;
            }
        }
        TreeSet<Bot> set = new TreeSet<>();
        for(int i = 0; i<300; i++) {
            set.add(bots[i]);
        }
        int minid = 0;
        long prod = 1;
        while(set.last().items == 2) {
            Bot b = set.pollLast();
            if(b.lo == 17 && b.hi == 61)
                System.out.println(b.lo + " " + b.hi + " id: " + b.orgid);
            boolean ok = true;
            if(b.loTo > -1) {
                ok = ok && bots[b.loTo].items < 2;
            }
            if(b.hiTo > -1) {
                ok = ok && bots[b.hiTo].items < 2;
            }
            if(!ok) {
                b.id = minid--;
                set.add(b);
                continue;
            }
            if(b.loTo >= 0) {
                Bot lo = bots[b.loTo];
                set.remove(lo);
                lo.insert(b.lo);
                set.add(lo);
            } else if(b.loTo >= -3) {
                prod *= b.lo;
            }
            if(b.hiTo >= 0) {
                Bot hi = bots[b.hiTo];
                set.remove(hi);
                hi.insert(b.hi);
                set.add(hi);
            } else if(b.hiTo >= -3) {
                prod *= b.hi;
            }
            b.lo = -1;
            b.hi = -1;
            b.items = 0;
            set.add(b);
        }
        System.out.println("product: " + prod);
    }
}
