import java.util.LinkedList;
public class B {
    public static void main(String[] args) {
        LinkedList<Integer> que1 = new LinkedList<>();
        LinkedList<Integer> que2 = new LinkedList<>();
        int size = Integer.parseInt(args[0]);
        for(int i = 1; i<=size; i++) {
            if(i<=size/2) que1.addLast(i);
            else que2.addLast(i);
        }

        while(que1.size() + que2.size() != 1) {
            int x = que1.pollFirst();
            if(que1.size() == que2.size()) {
                que1.pollLast();
            }else {
                que2.pollFirst();
            }
            que2.addLast(x);
            int a = que2.pollFirst();
            que1.addLast(a);
        }
        System.out.println(que1.pollFirst());
    }
}
