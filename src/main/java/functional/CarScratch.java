package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface CarCriterion {
  boolean test(Car c);
}

public class CarScratch {
  public static void showAll(List<Car> lc) {
    for (Car c : lc) {
      System.out.println(c);
    }
    System.out.println("-------------------------------------");
  }
  
  
  public static List<Car> getCarsByCriterion(Iterable<Car> lc, CarCriterion criterion) {
    List<Car> rv = new ArrayList<>();
    for (Car c : lc) {
      if (criterion.test(c)) {
        rv.add(c);
      }
    }
    return rv;
  }

  public static void main(String[] args) {
    List<Car> cars = Arrays.asList(
        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
    );
    showAll(cars);
    
    showAll(getCarsByCriterion(cars, new RedCarCriterion()));
    
    showAll(getCarsByCriterion(cars, new GasLevelCarCriterion(7)));
    
  }
}
