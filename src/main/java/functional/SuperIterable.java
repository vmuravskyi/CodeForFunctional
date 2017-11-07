package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;
  
  public SuperIterable(Iterable<E> toWrap) {
    self = toWrap;
  }
  
  public SuperIterable<E> filter(Predicate<E> pred) {
    List<E> rv = new ArrayList<>();
    
    for (E e : self) {
      if (pred.test(e)) {
        rv.add(e);
      }
    }
    return new SuperIterable<>(rv);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
  
  public static void main(String[] args) {
    SuperIterable<String> strings = new SuperIterable<>(Arrays.asList(
        "LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen"));
    
    for (String s : strings) {
      System.out.println("> " + s);
    }
    System.out.println("Long: ------------------------");
    for (String s : strings.filter(s -> s.length() > 4)) {
      System.out.println("> " + s);
    }
    System.out.println("Original: ------------------------");
    for (String s : strings) {
      System.out.println("> " + s);
    }
  }
}
