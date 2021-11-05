package my;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static Criterion<Car> getGasLevelCarCriterion(final int threshold) {
        return c -> c.getGasLevel() >= threshold;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    // CountPassengers
    public static Comparator<Car> getCountPassengersComparator() {
        return countPassengersComparator;
    }

    public static Criterion<Car> getColorCriterion(String... colors) {
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));
        colorSet = colorSet.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        Set<String> finalColorSet = colorSet;
        return car -> finalColorSet.contains(car.getColor().toLowerCase());
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
