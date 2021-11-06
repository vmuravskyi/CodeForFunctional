package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static my.Car.getColorCriterion;


public class CarScratch {

    public static <E> Criterion<E> negate(Criterion<E> criterion) {
        return e -> !criterion.test(e);
    }

    public static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
        return e -> first.test(e) && second.test(e);
    }

    public static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
        return e -> first.test(e) || second.test(e);
    }

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


//        showAll(getByCriterion(cars, getColorCriterion("OctaRIne", "GREEN")));

//        Criterion<Car> level7 = Car.getGasLevelCarCriterion(7);
//        showAll(getByCriterion(cars, level7));
//        Criterion<Car> notLevel7 = CarScratch.negate(level7);
//        showAll(getByCriterion(cars, notLevel7));

        Criterion<Car> isGreen = Car.getColorCriterion("Green");
        Criterion<Car> fourPassengers = Car.getPassengersCriterion(4);

        Criterion<Car> redFourPassengers = and(isGreen, fourPassengers);
        showAll(getByCriterion(cars, redFourPassengers));



//        List<String> colors = Arrays.asList("Red", "Blue", "Yellow", "Pink", "Green", "White");
//        showAll(getByCriterion(colors, st -> st.length() > 4));
//        showAll(getByCriterion(colors, st -> Character.isUpperCase(st.charAt(0))));
//
//        LocalDate today = LocalDate.now();
//        List<LocalDate> dates = Arrays.asList(today, today.plusDays(1), today.plusWeeks(1),
//                today.minusDays(1)
//        );
//        showAll(getByCriterion(dates, localDate -> localDate.isAfter(today)));

    }
}
