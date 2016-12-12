import java.util.Scanner;
import java.util.ArrayList;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(sc.hasNext()) {
            list.add(sc.nextLine());
        }
        String[] sa = list.toArray(new String[0]);
        int[] reg = new int[4];
        reg[2] = 1; //Part2 input
        String cpy = "cpy", jnz = "jnz", inc = "inc", dec = "dec";

        for(int i = 0; i<sa.length; i++) {
            String[] a = sa[i].split(" ");
            if(a[0].equals(cpy)) {
                int r = ch0(a[1]);
                if(r>= 0 && r <= 3) {
                     reg[ch0(a[2])] = reg[r];
                 } else {
                    int cp = Integer.parseInt(a[1]);
                    reg[ch0(a[2])] = cp;
                 }

            } else if(a[0].equals(inc))
                reg[ch0(a[1])]++;

            else if(a[0].equals(dec))
                reg[ch0(a[1])]--;

            else if(a[0].equals(jnz)) {
                int r = ch0(a[1]);
                if(r<= 3 && r>=0) {
                    if(reg[r] != 0)  i += Integer.parseInt(a[2]) - 1;
                } else if(Integer.parseInt(a[1]) != 0)
                    i += Integer.parseInt(a[2]) - 1;
            }
        }
        System.out.println(reg[0]);
    }
    private static int ch0(String s) {
        return s.charAt(0) - 'a';
    }
}
