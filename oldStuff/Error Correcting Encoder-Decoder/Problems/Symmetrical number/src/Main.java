import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int number = s.nextInt();
        String val = String.valueOf(number); // Get the string.
        StringBuilder sb = new StringBuilder(val);
        if (val.equals(sb.reverse().toString())) {
            System.out.println("1");
        } else {
            System.out.println("3");
        }
    }
}
