import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int xCount = scanner.nextInt();
        int s = 0;
        int f = 0;
        int r = 0;
        for (int i = 0; i < xCount; i++) {
            int x = scanner.nextInt();
            switch (x) {
                case -1:
                    r++;
                    break;
                case 0:
                    s++;
                    break;
                case 1:
                    f++;
                    break;
                default:
                    break;
            }
        }
        System.out.println(s + " " + f + " " + r);
    }
}
