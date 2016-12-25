import java.util.*;
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(sc.hasNext()) {
            list.add(sc.nextLine());
        }
        Inst[] program = new Inst[list.size()];
        String cpy = "cpy", jnz = "jnz", inc = "inc", dec = "dec", add = "add", out = "out";

        for(int i = 0; i<program.length; i++) {
            String[] a = list.get(i).split(" ");
            if(a[0].equals(cpy)) {
                int x = ch0(a[1]);
                boolean valx = false;
                if(!isRegister(x)) {
                     x = Integer.parseInt(a[1]);
                     valx = true;
                }
                program[i] = new cpy(x, valx, ch0(a[2]), false);

            } else if(a[0].equals(inc))
                program[i] = new inc(ch0(a[1]));

            else if(a[0].equals(dec))
                program[i] = new dec(ch0(a[1]));

            else if(a[0].equals(jnz)) {
                int x = ch0(a[1]);
                int y = ch0(a[2]);
                boolean valx = false;
                boolean valy = false;
                if(!isRegister(x)) {
                    x = Integer.parseInt(a[1]);
                    valx = true;
                }
                if(!isRegister(y)){
                    y = Integer.parseInt(a[2]);
                    valy = true;
                }
                program[i] = new jnz(x, valx, y, valy);

            }else if(a[0].equals(out))
                program[i] = new out(ch0(a[1]));
            else if(a[0].equals(add))
                program[i] = new add(Integer.parseInt(a[1]), ch0(a[2]));
            else
                program[i] = new fix();
        }
        for(int in = 0; ;in++) {
            int[] reg = new int[]{in, 0, 0, 0};
            LinkedList<Integer> outl = new LinkedList<>();
            int i = 0;
            while(i<program.length && outl.size() < 100) { 
                Inst inst = program[i];
                inst.exec(reg, program, i, outl);
                i = inst.next(reg, i);
            }
            boolean ok = true;
            int ro = 0;
            for(int x: outl) {
                ok = ok && ro == x;
                ro = (ro==1)?0:1;
            }
            if(ok) {
                System.out.println(in);
                break;
            }
        }
    }
    public static int ch0(String s) {
        return s.charAt(0) - 'a';
    }
    public static boolean isRegister(int x) {
        return x>=0 && x<=3;
    }
}
abstract class Inst {
    abstract void exec(int[] reg, Inst[] prog, int id, LinkedList<Integer> out);
    int next(int[] reg, int id) {
        return id + 1;
    }
}

class fix extends Inst{
    public fix() {}
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        reg[0] = reg[1]/2;
        reg[2] = (reg[1]+1)%2 + 1;
    }
    
}

class out extends Inst{
    int x;
    public out(int x) {
        this.x = x;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        out.add(reg[x]);
    }
}
class cpy extends Inst{
    int x;
    boolean valx;
    int y;
    boolean valy;
    public cpy(int x, boolean valx, int y, boolean valy) {
        this.x = x; this.valx = valx;
        this.y = y; this.valy = valy;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        if(valy) return;
        
        if(valx) reg[y] = x;
        else reg[y] = reg[x]; 
    }
}
class inc extends Inst{
    int x;
    public inc(int x) {
        this.x = x;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        reg[x]++;
    }
}
class add extends Inst{
    int x;
    int to;
    public add(int x, int to) {
        this.x = x; this.to = to;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        reg[to] += x;
    }

}
class dec extends Inst{
    int x;
    public dec(int x) {
        this.x = x;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
        reg[x]--;
    }
}
class jnz extends Inst{
    int x;
    boolean valx;
    int y;
    boolean valy;
    public jnz(int x, boolean valx, int y, boolean valy) {
        this.x = x; this.valx = valx;
        this.y = y; this.valy = valy;
    }
    void exec(int reg[], Inst[] prog, int id, LinkedList<Integer> out) {
    }
    int next(int[] reg, int id) {
        if((valx && x!= 0) || (!valx && reg[x] != 0)) {
            if(valy) {
                return id + y;
            } else {
                return id + reg[y];
            }
        }
        return id + 1;
    }
}
