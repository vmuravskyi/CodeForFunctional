package my;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static my.Car.*;


public class CarScratch {
    public static <E> void showAll(List<E> lc) {
        for (E e : lc) {
            System.out.println(e);
        }
        System.out.println("-------------------------------------------------------------");
    }

//    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion carCriterion) {
//        List<Car> output = new ArrayList<>();
//        for (Car car : in) {
//            if (carCriterion.test(car)) {
//                output.add(car);
//            }
//        }
//        return output;
//    }

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
        showAll(cars);

        showAll(getByCriterion(cars, getRedCarCriterion()));
        showAll(getByCriterion(cars, getGasLevelCarCriterion(6)));

        showAll(cars);

        showAll(getByCriterion(cars, getColorCriterion("red", "green")));



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
