import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if ((b >= a && c <= a) || (c >= a && b <= a)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}