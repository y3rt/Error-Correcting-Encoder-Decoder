import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean go = true;
        int total = 0;
        while (go) {
            int x = s.nextInt();
            total += x;
            if (x == 0) {
                go = false;
                System.out.println(total);
            }
        }
    }
}
