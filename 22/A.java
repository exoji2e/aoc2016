import java.util.*;
public class A {
    private static class Node { 
        int cap, used, x, y;
        int dist;
        public Node(int x, int y, int used, int cap) {
            this.x = x; this.y = y;
            this.used = used; this.cap = cap;
        }
    }
    private static class State {
        int xG, yG, xF, yF;
        int dist;
        public State(int xG, int yG, int xF, int yF, int dist) {
            this.xG = xG; this.yG = yG;
            this.xF = xF; this.yF = yF;
            this.dist = dist;
        }
        public boolean samePos() {
            return xF == xG && yF == yG;
        }
        public String toString() {
            return "" + xG + " " + yG + " " + xF + " " + yF;
        }
        public State copy() {
            return new State(xG, yG, xF, yF, dist+1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.nextLine();
        int sx = 35;
        int sy = 29;
        Node[][] b = new Node[sx][sy];
        Node free = null;
        for(int i = 0; i<sx; i++) {
            for(int j = 0; j<sy; j++) {
                sc.next(); //id
                String cap = sc.next();
                String used = sc.next();
                sc.next();
                sc.next();
                int c = Integer.parseInt(cap.substring(0, cap.length() - 1));
                int u = Integer.parseInt(used.substring(0, used.length() - 1));
                if(cap.charAt(cap.length() - 1) != 'T' || used.charAt(used.length() - 1) != 'T')
                    System.out.println(i + " " + j);
                Node nxt = (new Node(i, j, u, c));
                b[i][j] = nxt;
                if(u == 0) free = nxt;
            }
        }
        //Part1:
        int count = 0;
        for(int i = 0; i<sx*sy; i++) {
            for(int j = 0; j<sx*sy; j++) {
                if(i == j) continue;
                Node n = b[i%sx][i/sx];
                Node m = b[j%sx][j/sx];
                if(n.used != 0 && n.used < m.cap - m.used) count++;
            }
        }
        System.out.println(count);

        //Part2:
        LinkedList<State> bfs = new LinkedList<>();
        bfs.add(new State(sx-1, 0, free.x, free.y, 0));
        HashSet<String> dp = new HashSet<>();
        while(!bfs.isEmpty()) {
            State nxt = bfs.removeFirst();
            if(nxt.xG == 0 && nxt.yG == 0) {
                System.out.println(nxt.dist);
                return;
            }
            if(dp.contains(nxt.toString())) continue;
            dp.add(nxt.toString());
            if(nxt.xF > 0 && b[nxt.xF-1][nxt.yF].used < b[nxt.xF][nxt.yF].cap) {
                State mxt = nxt.copy();
                mxt.xF--;
                if(mxt.samePos()) {
                    mxt.xG++;
                }
                bfs.addLast(mxt);
            }
            if(nxt.xF < sx-1 && b[nxt.xF+1][nxt.yF].used < b[nxt.xF][nxt.yF].cap) {
                State mxt = nxt.copy();
                mxt.xF++;
                if(mxt.samePos()) {
                    mxt.xG--;
                }
                bfs.addLast(mxt);
            }
            if(nxt.yF > 0 && b[nxt.xF][nxt.yF-1].used < b[nxt.xF][nxt.yF].cap) {
                State mxt = nxt.copy();
                mxt.yF--;
                if(mxt.samePos()) {
                    mxt.yG++;
                }
                bfs.addLast(mxt);
            }
            if(nxt.yF < sy-1 && b[nxt.xF][nxt.yF+1].used < b[nxt.xF][nxt.yF].cap) {
                State mxt = nxt.copy();
                mxt.yF++;
                if(mxt.samePos()) {
                    mxt.yG--;
                }
                bfs.addLast(mxt);
            }
        }
    }
}
