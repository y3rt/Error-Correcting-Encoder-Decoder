import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Scanner s = new Scanner(System.in);

        System.out.println(alphabet.contains(s.nextLine()));
    }
}
