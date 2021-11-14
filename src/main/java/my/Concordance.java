package my;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance {
    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("\\W+");

        Comparator<Map.Entry<String, Long>> compareByValue =
                (o1, o2) -> o1.getValue().compareTo(o2.getValue());
        Comparator<Map.Entry<String, Long>> compareByValueReversed =
                compareByValue.reversed();


        try {
            Files.lines(Paths.get("src/main/resources/pride&prejudice.txt"))
                    .flatMap(pattern::splitAsStream)
                    .filter(s -> s.length() > 0)
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .sorted(compareByValueReversed)
                    .limit(200)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
