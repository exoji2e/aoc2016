import java.util.*;
public class A {
    private static class State {
        int x, y, k;
        boolean[] v;
        public State(int x, int y, boolean[] v, int k) {
            this.x = x; this.y = y; this.v = v; this.k = k;
        }
        public boolean[] copyB() {
            boolean x[] = new boolean[v.length];
            for(int i = 0; i<v.length; i++) {
                x[i] = v[i];
            }
            return x;
        }
        public boolean allT() {
            boolean ok = true;
            for(boolean b: v) {
                ok = ok && b;
            }
            return ok;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(x).append(',').append(y).append(',');
            for(boolean b: v) {
                sb.append(b?'1':'0');
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> set = new HashSet<>();
        int x0 = -1, y0 = -1;
        int nbrs = 0;
        LinkedList<String> inp = new LinkedList<>();
        while(sc.hasNext()) {
            inp.addLast(sc.next());
        }
        int sx = inp.size(), sy = inp.getFirst().length();
        int[][] board = new int[sx][sy];
        for(int i = 0; i<sx; i++) {
            for(int j = 0; j<sy; j++) {
                board[i][j] = -2;
            }
        }
        int id = 0;
        for(String s: inp) {
            for(int j = 0; j<s.length(); j++) {
                if(s.charAt(j) == '.') {
                    board[id][j] = -1;
                } else if(s.charAt(j) != '#') {
                    board[id][j] = s.charAt(j) - '0';
                    if(s.charAt(j) == '0') {
                        x0 = id;
                        y0 = j;
                    }
                    nbrs++;
                }
            }
            id++;
        }
        boolean[] visited = new boolean[nbrs];
        visited[0] = true;
        LinkedList<State> bfs = new LinkedList<>();
        bfs.addLast(new State(x0, y0, visited, 0));
        boolean printed = false;
        int dx[] = new int[]{-1, 1, 0, 0};
        int dy[] = new int[]{0, 0, -1, 1};

        while(!bfs.isEmpty()) {
            State s = bfs.removeFirst();
            String str = s.toString();
            if(set.contains(str)) continue;
            set.add(str);
            if(s.allT() && !printed) { //Part1
                printed = true;
                System.out.println(s.k);
            }
            if(s.allT() && s.x == x0 && s.y == y0) { //Part2
                System.out.println(s.k);
                return;
            }
            for(int i = 0; i<4; i++) {
                int x = s.x + dx[i], y = s.y + dy[i];
                if(x>=0 && x<sx && y>=0 && y<sy && board[x][y] > -2) {
                    boolean v[] = s.copyB();
                    if(board[x][y] > -1) 
                        v[board[x][y]] = true;
                    bfs.addLast(new State(x, y, v, s.k+1));
                }
            }
        }
    }
}
