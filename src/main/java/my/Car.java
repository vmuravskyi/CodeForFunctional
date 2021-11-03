package my;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car {
    private static final Criterion<Car> FOUR_PASSENGERS_CRITERION = c -> c.getPassengers().size() == 4;
    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");
    private static final Comparator<Car> gasComparator =
            (o1, o2) -> o1.getGasLevel() - o2.getGasLevel();
    private static final Comparator<Car> countPassengersComparator =
            (o1, o2) -> o1.getPassengers().size() - o2.getPassengers().size();
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, null);
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
    }

    // FourPassengers
    public static Criterion<Car> getFourPassengersCriterion() {
        return FOUR_PASSENGERS_CRITERION;
    }

    // RedCar
    public static Criterion<Car> getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    // GasComparator
    public static Comparator<Car> getGasComparator() {
        return gasComparator;
    }

    // GasLevel
    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
        return c -> c.getGasLevel() >= threshold;
    }

    // CountPassengers
    public static Comparator<Car> getCountPassengersComparator() {
        return countPassengersComparator;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                (trunkContents == null ? ", trunkContents=" + trunkContents : "no trunk") + '}';
    }

}
