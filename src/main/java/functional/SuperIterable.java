package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
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

  public void forEvery(Consumer<E> consumer) {
    for (E e : self) {
      consumer.accept(e);
    }
  }
  
  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
  
  public static void main(String[] args) {
    SuperIterable<String> strings = new SuperIterable<>(Arrays.asList(
        "LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen"));
    
    strings.forEvery(s -> System.out.println(s));
    
    System.out.println("Long: ------------------------");
    strings
        .filter(s -> s.length() > 4)
        .forEvery(s -> System.out.println(s));
    
    System.out.println("Original: ------------------------");
    strings.forEvery(s -> System.out.println(s));
  }
}
