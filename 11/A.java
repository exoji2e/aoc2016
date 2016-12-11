import java.util.LinkedList;
public class A {
    static int[] M;
    static int lastid;
    private static class FuncCall{
        int[] n;
        int k;
        int e;
        public FuncCall(int n[], int k, int e) {
            this.n = new int[n.length];
            for(int i = 0; i<n.length; i++) this.n[i] = n[i];
            this.k = k; this.e = e;
        }
    }
    // Run with java -Xmx8G A, takes 30s to execute on my machine.
    public static void main(String args[]) {
        Sc sc = new Sc(System.in);
        // Input description: 
        // for every even i; 
        // n[i] = placement of microship_i,
        // n[i+1] = placement of generator_i.
        int[] n;
        if(args.length > 0)
            n = new int[]{2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}; // input part2
        else
            n = new int[]{2, 1, 1, 1, 1, 1, 0, 0, 0, 0}; //input part1
        lastid = (1<<2*(n.length + 1))-1;
        M = new int[lastid+1]; 
        for(int i = 0; i<M.length; i++) {
            M[i] = Integer.MAX_VALUE;
        }

        LinkedList<FuncCall> bfs = new LinkedList<>();
        bfs.add(new FuncCall(n, 0, 0));
        while(!bfs.isEmpty()) {
            FuncCall f = bfs.removeFirst();
            if(f.e<0 || f.e>3) continue;
            int inx = getindx(f.n, f.e);
            if(!ok(f.n)) continue;
            if(M[inx] <= f.k) continue;
            M[inx] = f.k;
            if(inx == lastid) break;

            for(int i = 0; i<f.n.length; i++) {
                if(f.n[i] != f.e) continue;
                f.n[i]--;
                bfs.addLast(new FuncCall(f.n, f.k+1, f.e-1));
                f.n[i]+=2;
                bfs.addLast(new FuncCall(f.n, f.k+1, f.e+1));
                f.n[i]--;
                
                for(int j = i+1; j<f.n.length; j++) {
                    if(f.n[j] != f.e) continue;
                    f.n[i]--;
                    f.n[j]--;
                    bfs.addLast(new FuncCall(f.n, f.k+1, f.e-1));
                    f.n[i]+=2;
                    f.n[j]+=2;
                    bfs.addLast(new FuncCall(f.n, f.k+1, f.e+1));
                    f.n[i]--;
                    f.n[j]--;
                }
            }
        }
        System.out.println(M[lastid]);
    }
    public static boolean ok(int []n) {
        for(int i = 0; i<n.length; i+=2) 
            if(n[i] != n[i+1]) 
                for(int j = 1; j<n.length; j+=2) 
                    if(n[j] == n[i])  
                        return false;

        return true;
    }
    public static int getindx(int n[], int e) {
        int sum = e;
        int fac = 4;
        for(int i = 0; i<n.length; i++) {
            sum += fac*n[i];
            fac *= 4;
        }
        return sum;
    }
}
