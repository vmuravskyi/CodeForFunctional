package my;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamVersion {
    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen");

        strings.forEach(s -> System.out.println("> " + s));

        Stream<String> upperCase =
                strings.stream().filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("----------------------------------------");
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("----------------------------------------");
        strings.forEach(s -> System.out.println("> " + s));

        System.out.println("----------------------------------------");
        strings.stream()
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .map(String::toUpperCase)
                .forEach(s -> System.out.println("> " + s));


        System.out.println("----------------------------------------");
        List<Car> carList =
                Arrays.asList(Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr.Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Hy rum", "Locke", "Bonzo"),
                        Car.withGasColorPassengers(5, "Red", "Vova"));


        carList.stream()
                .filter(car -> car.getGasLevel() >= 6)
                .map(car -> car.getPassengers().get(0)
                        + " is driving a "
                        + car.getColor() + " car, with gas level of: "
                        + car.getGasLevel())
                .forEach(System.out::println);


        System.out.println("----------------------------------------");
        carList.stream().map(car -> car.addGas(3))
                .forEach(System.out::println);


        System.out.println("----------------------------------------");
        carList.forEach(System.out::println);
//
        System.out.println("----------------------------------------");
        carList.stream()
                .filter(car -> car.getPassengers().size() >= 3)
                .flatMap(car -> car.getPassengers().stream())
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("----------------------------------------");
        carList.stream()
                .flatMap(car -> car.getPassengers().stream()
                        .map(p -> p + " is riding in " + car.getColor() + " car"))
                .forEach(System.out::println);


        IntStream intStream = IntStream.range(1, 100);

        List<String> result = intStream.mapToObj(value -> String.valueOf(value))
                .collect(Collectors.toList());

        result.stream().filter(s -> Integer.parseInt(s) % 2 == 0)
                .forEach(System.out::println);
        System.out.println(result);
    }
}