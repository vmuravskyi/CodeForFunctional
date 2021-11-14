package my;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance2 {

//    public static <E, F> Function<E, Optional<F>> wrap(ExceptionFunction<E, F> op) {
//        return e -> {
//            try {
//                return Optional.of(op.apply(e));
//            } catch (Throwable t) {
//                return Optional.empty();
//            }
//        };
//    }

//    public static Optional<Stream<String>> lines(Path path) {
//        try {
//            return Optional.of(Files.lines(path));
//        } catch (IOException e) {
//            System.err.println("File read failed: " + e.getMessage());
//            return Optional.empty();
//        }
//    }


    private static final Pattern pattern = Pattern.compile("\\W+");

    private static final Comparator<Map.Entry<String, Long>> compareByValue =
            (o1, o2) -> o1.getValue().compareTo(o2.getValue());
    private static final Comparator<Map.Entry<String, Long>> compareByValueReversed =
            compareByValue.reversed();

    private static final Comparator<Map.Entry<String, Long>> compareByWord = Map.Entry.comparingByKey();

    public static void main(String[] args) {

        List<String> filePaths = Arrays.asList(
                "src/main/resources/Emma by Jane Austen.txt",
                "src/main/resources/pride&prejudice.txt",
                "src/main/resources/Sense and Sensibility by Jane Austen.txt");
        filePaths.stream()
                .map(Paths::get)
                .map(Either.wrap(Files::lines))
                .peek(e -> e.handle(System.out::println))
                .filter(either -> either.success())
                .flatMap(Either::get)
                .flatMap(pattern::splitAsStream)
                .filter(s -> s.length() > 0)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(compareByValueReversed)
                .limit(200)
//                .sorted(compareByWord)
                .forEach(l -> System.out.printf("%10s : %5d\n", l.getKey(), l.getValue()));
    }

}
