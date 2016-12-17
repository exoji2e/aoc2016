import java.util.Scanner;
import java.util.LinkedList;
public class A {
    private static class Room {
        int x, y;
        String passC;
        public Room(int x, int y, String passC) {
            this.x = x; this.y = y; this.passC = passC;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Room start = new Room(0, 0, sc.next());
        LinkedList<Room> bfs = new LinkedList<>();
        bfs.add(start);
        boolean found = false;
        int maxL = 0;
        while(!bfs.isEmpty()) {
            Room r = bfs.removeFirst();
            if(r.x == 3 && r.y == 3) {
                if(!found) {
                    System.out.println(r.passC.substring(start.passC.length()));
                    found = true;
                }
                if(r.passC.length() > maxL) {
                    maxL = r.passC.length();
                }
                continue;
            }
            String opens = MD5(r.passC);
            if(ok(opens.charAt(0)) && r.y != 0) {
                bfs.addLast(new Room(r.x, r.y - 1, r.passC + "U"));
            }
            if(ok(opens.charAt(1)) && r.y != 3) {
                bfs.addLast(new Room(r.x, r.y + 1, r.passC + "D"));
            }
            if(ok(opens.charAt(2)) && r.x != 0) {
                bfs.addLast(new Room(r.x - 1, r.y, r.passC + "L"));
            }
            if(ok(opens.charAt(3)) && r.x != 3) {
                bfs.addLast(new Room(r.x + 1, r.y, r.passC + "R"));
            }
        }
        System.out.println(maxL - start.passC.length());
    }

    public static boolean ok(char c) {
        return 'b'<= c && c <= 'f';
    }
    public static String MD5(String md5) {
       try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {}
        return null;
    }
}
