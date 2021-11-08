package my;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private final Iterable<E> self;

    public SuperIterable(Iterable<E> s) {
        self = s;
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> f) {
        List<F> result = new ArrayList<>();
        self.forEach(e -> f.apply(e).forEach(result::add));
        return new SuperIterable<>(result);
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> result = new ArrayList<>();
        self.forEach(e -> result.add(op.apply(e)));
        return new SuperIterable<>(result);
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) results.add(e);
        });
        return new SuperIterable<>(results);
    }

    public void forEvery(Consumer<E> cons) {
        self.forEach(cons);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen")
        );

        strings.forEach(s -> System.out.println("> " + s));

        SuperIterable<String> upperCase =
                strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("----------------------------------------");
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("----------------------------------------");
        strings.forEach(s -> System.out.println("> " + s));

        System.out.println("----------------------------------------");
        strings
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .map(String::toUpperCase)
                .forEach(s -> System.out.println("> " + s));



        System.out.println("----------------------------------------");
        SuperIterable<Car> carIter = new SuperIterable<>(
                Arrays.asList(Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr.Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Hy rum", "Locke", "Bonzo"),
                        Car.withGasColorPassengers(5, "Red", "Vova")));


        carIter.filter(car -> car.getGasLevel() >= 6)
                .map(car -> car.getPassengers().get(0)
                        + " is driving a "
                        + car.getColor() + " car, with gas level of: "
                        + car.getGasLevel())
                .forEach(System.out::println);


        System.out.println("----------------------------------------");

//        carIter.map(car -> Car.withGasColorPassengers(
//                car.getGasLevel() + 3,
//                car.getColor(),
//                car.getPassengers().toArray(new String[]{})
//                        ))
//                .forEach(System.out::println);

        carIter.map(car -> car.addGas(3))
                .forEach(System.out::println);

        System.out.println("----------------------------------------");
        carIter.forEach(System.out::println);

        System.out.println("----------------------------------------");
        carIter
                .filter(car -> car.getPassengers().size() >= 3)
                .flatMap(car -> new SuperIterable<>(car.getPassengers()))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
