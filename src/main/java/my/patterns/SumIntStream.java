package my.patterns;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SumIntStream {
    public static void main(String[] args) {
        OptionalInt result = IntStream.iterate(1, i -> i + 3)
                .limit(10)
                .reduce((left, right) -> left + right);

        result.ifPresent(value -> System.out.println("Sum is: " + result));
    }
}
