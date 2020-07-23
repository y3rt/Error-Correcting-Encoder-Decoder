import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        int n = s.nextInt();

        int x = 0;

        for (int i = a; i <= b; i++) {
            if (i % n == 0) {
                x++;
            }
        }
        System.out.println(x);
    }
}
