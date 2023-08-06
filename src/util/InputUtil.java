package util;

import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {
    public static String stringInput(String title){
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);
        return  scanner.nextLine();
    }
    public static byte byteInput(String title){
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);
        return  scanner.nextByte();
    }
    public static int intInput(String title){
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);
        return  scanner.nextInt();
    }
}
