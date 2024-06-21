package data.util;

public class HelperFunctions {
    public static int getNumbersFromMixedString(String str) {
        System.out.println("CONFLICT MAKER");
        System.out.println("CONFLICT");
        return Integer.parseInt(str.replaceAll("[^\\d.%]", "").replace("%", ""));
    }
}
