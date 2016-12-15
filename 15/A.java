public class A {
    public static void main(String args[]) {
        int[] mod, start;
        if(args.length > 0){
            mod = new int[]{7, 13, 3, 5, 17, 19, 11};
            start = new int[]{0, 0, 2, 2, 0, 7, 0};
        } else {
            mod = new int[]{7, 13, 3, 5, 17, 19};
            start = new int[]{0, 0, 2, 2, 0, 7};
        }
        for(int i = 0; ; i++) {
            boolean ok = true;
            for(int k = 0; k<mod.length; k++) {
                ok = ok && (start[k] + i + k + 1)%mod[k] == 0;
            }
            if(ok) {
                System.out.println(i);
                return;
            }
        }
    }
}