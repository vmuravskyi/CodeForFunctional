package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static my.Car.getColorCriterion;


public class CarScratch {

    public static <E> void showAll(List<E> lc) {
        for (E e : lc) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Criterion<E> criterion) {
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


        Criterion<Car> isGreen = Car.getColorCriterion("Green");
        Criterion<Car> isFourPassengers = Car.getPassengersCriterion(4);
        Criterion<Car> isSixLiters = Car.getGasLevelCarCriterion(6);
        Criterion<Car> isColorBlack = Car.getColorCriterion("Black");

        Criterion<Car> greenFourPassengers = isGreen.and(isFourPassengers);
        showAll(getByCriterion(cars, greenFourPassengers));

        Criterion<Car> sixLitersOrBlack = isColorBlack.or(isSixLiters);
        showAll(getByCriterion(cars, sixLitersOrBlack));

    }
}
