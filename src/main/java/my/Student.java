package my;

import java.util.*;
import java.util.stream.Collectors;

public class Student {
    private static final NavigableMap<Integer, String> gradeLetters = new TreeMap<>();

    static {
        gradeLetters.put(90, "A");
        gradeLetters.put(80, "B");
        gradeLetters.put(70, "C");
        gradeLetters.put(60, "D");
        gradeLetters.put(50, "E");
        gradeLetters.put(0, "F");
    }

    private final String name;
    private final int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {
        return gradeLetters.floorEntry(score).getValue();
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ", " + score + "%, grade is " + getLetterGrade();
    }


    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                new Student("Fred", 71),
                new Student("Jim", 38),
                new Student("Sheila", 97),
                new Student("Weatherwax", 100),
                new Student("Ogg", 56),
                new Student("Rincewind", 28),
                new Student("Ridcully", 65),
                new Student("Magrat", 79));

        school.forEach(s -> System.out.println(s));

        Map<String, List<Student>> table = school.stream()
                .collect(Collectors.groupingBy(student -> student.getLetterGrade()));

        Comparator<Map.Entry<String, List<Student>>> entrySetAlphabetComparator =
                Map.Entry.comparingByKey();

        Comparator<Map.Entry<String, List<Student>>> entrySetAlphabetComparatorReversed =
                entrySetAlphabetComparator.reversed();

        table.entrySet().stream()
                .sorted(entrySetAlphabetComparator)
                .forEach(stringListEntry ->
                        System.out.println(stringListEntry.getKey() + " : " + stringListEntry.getValue()));

        System.out.println("----------------------------------");

        Map<String, Long> table2 = school.stream()
                .collect(Collectors.groupingBy(Student::getLetterGrade, Collectors.counting()));

        table2.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .forEach(stringLongEntry ->
                        System.out.println(stringLongEntry.getKey()
                                + " : "
                                + stringLongEntry.getValue()));

    }
}
