package functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NullChecks {
  public static void main(String[] args) {
    Map<String, Car> owners = new HashMap<>();
    owners.put("Sheila", Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"));
    owners.put("Librarian", Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"));
    owners.put("Ogg", Car.withGasColorPassengersAndTrunk(9, "Black", "Weatherwax", "Magrat"));
    
    String owner = "Ogg";
    Car c = owners.get(owner);
    List<String> trunkItems = c.getTrunkContents();
    System.out.println(owner + " has " + trunkItems + " in the car");
  }
}
