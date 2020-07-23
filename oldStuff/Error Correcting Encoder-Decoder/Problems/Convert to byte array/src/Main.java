import java.io.CharArrayWriter;

class Converter {
    public static char[] convert(String[] words) throws IOException {
        CharArrayWriter charWriter = new CharArrayWriter();
        for (String word : words) {
            charWriter.write(word);
        }
        return charWriter.toCharArray();

    }
}
