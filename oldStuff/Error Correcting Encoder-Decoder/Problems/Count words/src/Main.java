import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        reader.close();
        if (str.trim().length() > 0) {
            String[] strArr = str.trim().split("\\s+");
            System.out.println(strArr.length);
        } else {
            System.out.println(0);
        }
    }
}
