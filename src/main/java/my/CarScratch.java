package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;


public class CarScratch {

    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp) {
        return x -> comp.compare(target, x);
    }

    public static <E> Predicate<E> compareGreater(ToIntFunction<E> comp) {
        return x -> comp.applyAsInt(x) < 0;
    }

    public static <E> void showAll(List<E> lc) {
        for (E e : lc) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> criterion) {
        List<E> output = new ArrayList<>();
        for (E c : in) {
            if (criterion.test(c)) {
                output.add(c);
            }
        }
        return output;
    }


    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr.Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Hy rum", "Locke", "Bonzo"),
                Car.withGasColorPassengers(5, "Red", "Vova")
        );


        Predicate<Car> isGreen = Car.getColorCriterion("Green");
        Predicate<Car> isFourPassengers = Car.getPassengersCriterion(4);
        Predicate<Car> isSixLiters = Car.getGasLevelCarCriterion(6);
        Predicate<Car> isColorBlack = Car.getColorCriterion("Black");

        Predicate<Car> greenFourPassengers = isGreen.and(isFourPassengers);
        showAll(getByCriterion(cars, greenFourPassengers));

        Predicate<Car> sixLitersOrBlack = isColorBlack.or(isSixLiters);
        showAll(getByCriterion(cars, sixLitersOrBlack));

        Car bert = Car.withGasColorPassengers(5, "Blue");

        ToIntFunction<Car> compareWithBert = compareWithThis(bert, Car.getGasComparator());
        for (Car c : cars) {
            System.out.println("comparing " + c + " with bert gives " +
                    compareWithBert.applyAsInt(c));
        }

        showAll(getByCriterion(cars, compareGreater(compareWithBert)));

    }
}
