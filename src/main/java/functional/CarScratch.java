package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PassengerCountOrder implements Comparator<Car> {
  @Override
  public int compare(Car o1, Car o2) {
    return o1.getPassengers().size() - o2.getPassengers().size();
  }
}

public class CarScratch {
  public static void showAll(List<Car> lc) {
    for (Car c : lc) {
      System.out.println(c);
    }
    System.out.println("-------------------------------------");
  }
  
  public static List<Car> getCarsByColor(Iterable<Car> lc, String color) {
    List<Car> rv = new ArrayList<>();
    for (Car c : lc) {
      if (c.getColor().equals(color)) {
        rv.add(c);
      }
    }
    return rv;
  }
  
  public static List<Car> getCarsByMinGasLevel(Iterable<Car> lc, int minGas) {
    List<Car> rv = new ArrayList<>();
    for (Car c : lc) {
      if (c.getGasLevel() >= minGas) {
        rv.add(c);
      }
    }
    return rv;
  }
  
  public static void main(String[] args) {
    List<Car> cars = Arrays.asList(
        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo"),
        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat")
    );
    showAll(cars);
    
    showAll(getCarsByColor(cars, "Red"));
    
    cars.sort(new PassengerCountOrder());
    showAll(cars);
  }
}
