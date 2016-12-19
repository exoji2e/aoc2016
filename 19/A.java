import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;
public class A {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        LinkedList<Integer> elves = new LinkedList<>();
        for(int i = 1; i<=size; i++) {
            elves.addLast(i);
        }
        boolean take = false;
        while(elves.size() != 1) {
            Iterator<Integer> itr = elves.iterator();
            while(itr.hasNext()) {
                itr.next();
                if(take) itr.remove();
                take = !take;
            }
        }
        System.out.println(elves.pollFirst());
    }
}
