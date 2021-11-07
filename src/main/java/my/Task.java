package my;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task {
    public static void main(String[] args) {
        String str = "Hello";
        System.out.println(reversed3(str));
    }

    private static String reversed(String str) {
        char[] charArr = str.toCharArray();
        String result = "";
        for (int i = 0; i < charArr.length; i++) {
            result = result.concat(String.valueOf(charArr[charArr.length - i - 1]));
        }
        return result;
    }

    private static String reversed2(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static String reversed3(String str) {
        char[] chars = str.toCharArray();
        return String.valueOf(IntStream.range(0, chars.length)
                .mapToObj(value -> chars[(chars.length - 1) - value])
                .collect(Collectors.toList()));
    }
}
