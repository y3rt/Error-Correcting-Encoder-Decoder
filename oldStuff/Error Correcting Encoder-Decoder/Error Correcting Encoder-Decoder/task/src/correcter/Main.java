package correcter;

import java.io.*;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        byte[] byteArr = readBytesFromFile("send.txt");
        System.out.println(new String(byteArr));
        byte[] hamArr = hamItUp(byteArr);
        System.out.println(new String(hamArr));
        writeToFileBytes(hamArr, "encoded.txt");

        send("encoded.txt", "received.txt");

        byte[] recArr = readBytesFromFile("received.txt");
        System.out.println(new String(recArr));

        byte[] finArr = hamItDown(recArr);
        System.out.println(new String(finArr));
        writeToFileBytes(finArr, "decoded.txt");
    }

    public static byte[] readBytesFromFile(String fileName){
        File myObj = new File(fileName);
        int arrLen = (int) myObj.length();
        byte[] content = new byte[arrLen];
        try (FileInputStream input = new FileInputStream(myObj)) {
            content = input.readAllBytes();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Input file not found:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR: Input file (see below) - some other exception occurred!");
            e.printStackTrace();
        }
        return content;
    }
    public static byte[] genBinErr(byte[] binArr){
        for (int i = 0; i < binArr.length; i++) {
            binArr[i] = flipOneRandomBitInByte(binArr[i]);
        }
        return binArr;
    }
    static byte flipOneRandomBitInByte(byte b) {
        Random rnd = new Random();
        return flipNthBitInByte(b, rnd.nextInt(8));
    }
    static byte flipNthBitInByte(byte b, int n) {
        return (byte) (b = (byte) (b ^ (1 << n)));
    }
    public static void writeToFileBytes(byte[] byteArr, String fileName){
        try {
            FileOutputStream fw = new FileOutputStream(fileName);
            fw.write(byteArr);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void send(String encodedFile, String receivedFile){
        byte[] byteArr = readBytesFromFile(encodedFile);
        byte[] errArr = genBinErr(byteArr);
        writeToFileBytes(errArr, receivedFile);
    }

    public static byte[] hamItUp(byte[] byteArr){
        String encodedByteStr = "";
        for (int i = 0; i < byteArr.length; i++) {
            String tempByte1 = "";
            int p1a;
            int p2a;
            int d3a = (int)(byteArr[i] >> (8-(1)) & 0x0001);
            int p4a;
            int d5a = (int)(byteArr[i] >> (8-(2)) & 0x0001);
            int d6a = (int)(byteArr[i] >> (8-(3)) & 0x0001);
            int d7a = (int)(byteArr[i] >> (8-(4)) & 0x0001);
//            System.out.print("." + "." + d3a + "." + d5a + d6a + d7a + ".");

            p1a = d3a ^ d5a ^ d7a;
            p2a = d3a ^ d6a ^ d7a;
            p4a = d5a ^ d6a ^ d7a;
            tempByte1 = "" + p1a + p2a + d3a + p4a + d5a + d6a + d7a + 0;
//            System.out.print("\n"+tempByte1);

// =========================================================

            String tempByte2 = "";
            int p1b;
            int p2b;
            int d3b = (int)(byteArr[i] >> (8-(5)) & 0x0001);
            int p4b;
            int d5b = (int)(byteArr[i] >> (8-(6)) & 0x0001);
            int d6b = (int)(byteArr[i] >> (8-(7)) & 0x0001);
            int d7b = (int)(byteArr[i] >> (8-(8)) & 0x0001);

//            System.out.print("\n"+"." + "." + d3b + "." + d5b + d6b + d7b + ".");

            p1b = d3b ^ d5b ^ d7b;
            p2b = d3b ^ d6b ^ d7b;
            p4b = d5b ^ d6b ^ d7b;
            tempByte2 = "" + p1b + p2b + d3b + p4b + d5b + d6b + d7b + 0;
//            System.out.println("\n"+tempByte2);

            encodedByteStr += tempByte1 + " " + tempByte2 + " ";
//            System.out.println(encodedByteStr+"\n");
        }
        String[] strArr = encodedByteStr.trim().split(" ");
        byte[] rtnArray = new byte[strArr.length];
        int pos = 0;
        for (String st : strArr) {
            rtnArray[pos] = (byte)Integer.parseInt(st, 2);
            pos++;
        }
        return rtnArray;
    }

    public static byte[] hamItDown(byte[] byteArr) {
        String decStr = "";
        for (int i = 0; i < byteArr.length; i++) {
            String tempByte1 = "";
            int p1a = (int) (byteArr[i] >> (7) & 0x0001);
            int p2a = (int) (byteArr[i] >> (6) & 0x0001);
            int d3a = (int) (byteArr[i] >> (5) & 0x0001);
            int p4a = (int) (byteArr[i] >> (4) & 0x0001);
            int d5a = (int) (byteArr[i] >> (3) & 0x0001);
            int d6a = (int) (byteArr[i] >> (2) & 0x0001);
            int d7a = (int) (byteArr[i] >> (1) & 0x0001);

            int bal1 = d3a ^ d5a ^ d7a;
            int bal2 = d3a ^ d6a ^ d7a;
            int bal4 = d5a ^ d6a ^ d7a;

            int chk1 = p1a ^ bal1;
            int chk2 = p2a ^ bal2;
            int chk4 = p4a ^ bal4;

            if (chk1 != 0 && chk2 != 0 && chk4 == 0) {
                if (d3a == 0) {
                    d3a = 1;
                } else {
                    d3a = 0;
                }
            }
            if (chk1 != 0 && chk2 == 0 && chk4 != 0) {
                if (d5a == 0) {
                    d5a = 1;
                } else {
                    d5a = 0;
                }
            }

            if (chk1 == 0 && chk2 != 0 && chk4 != 0) {
                if (d6a == 0) {
                    d6a = 1;
                } else {
                    d6a = 0;
                }
            }


            if (chk1 != 0 && chk2 != 0 && chk4 != 0) {
                if (d7a == 0) {
                    d7a = 1;
                } else {
                    d7a = 0;
                }
            }
            tempByte1 = "" + d3a + d5a + d6a + d7a;
            decStr += tempByte1;
            if (i % 2 != 0) {
                decStr += " ";
            }
        }
        String[] strArr = decStr.trim().split(" ");
        byte[] rtnArray = new byte[strArr.length];
        int pos = 0;
        for (String st : strArr) {
            rtnArray[pos] = (byte)Integer.parseInt(st, 2);
            pos++;
        }
        return rtnArray;
    }
}
