package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {

  private static final Pattern WORD_BREAK = Pattern.compile("\\W+");
  private static final Comparator<Map.Entry<String, Long>> valueOrder
      = Map.Entry.comparingByValue();

  public static Stream<String> lines(Path p) {
    try {
      return Files.lines(p);
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
  
  public static void main(String[] args) throws IOException {
    List<String> filenames = Arrays.asList("PrideAndPrejudice.txt", "Emma.txt", "SenseAndSensibility.txt");
    filenames.stream()
        .map(Paths::get)
//        .flatMap(Files::lines)
        .flatMap(Concordance::lines)
        .map(String::toLowerCase)
        .flatMap(WORD_BREAK::splitAsStream)
        .filter(s -> s.length() > 0)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .sorted(valueOrder.reversed())
        .limit(200)
        .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
        .forEach(System.out::println);
  }
}

