import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashSet;
public class A {
    static int n;
    private static class Pair {
        int x, y, steps;
        public Pair(int x, int y, int steps) {
            this.x = x; this.y = y; this.steps = steps;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        HashSet<String> set = new HashSet<>();
        LinkedList<Pair> bfs = new LinkedList<>();
        bfs.add(new Pair(1, 1, 0));
        while(!bfs.isEmpty()) {
            Pair p = bfs.removeFirst();
            if(!valid(p.x, p.y) || set.contains("" + p.x + "," + p.y)) continue;
            if(p.x == 31 && p.y == 39) {
                System.out.println(p.steps);
                return;
            }
            set.add("" + p.x + "," + p.y);
            bfs.addLast(new Pair(p.x, p.y + 1, p.steps + 1));
            bfs.addLast(new Pair(p.x + 1, p.y, p.steps + 1));
            bfs.addLast(new Pair(p.x - 1, p.y, p.steps + 1));
            bfs.addLast(new Pair(p.x, p.y - 1, p.steps + 1));
        }
    }
    public static boolean valid(int x, int y) {
        if(x<0 || y < 0) return false;
        int nbr = x*x + 3*x + 2*x*y + y + y*y + n;
        return Integer.bitCount(nbr)%2 == 0;
    }
}
