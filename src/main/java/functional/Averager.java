package functional;

import java.util.Spliterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;
  
  public void include(double d) {
    sum += d;
    count++;
  }
  
  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }
  
  public double get() {
    return sum / count;
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    Average av = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
        .parallel()
//        .unordered()
//        .limit(4_000_000_000L)
        .limit(200_000_000)
        .map(x -> Math.sin(x))
        .collect(
            () -> new Average(), 
            (r, d) -> r.include(d), 
            (r1, r2) -> r1.merge(r2));
    long end = System.nanoTime();
    
    double mean = av.get();
    System.out.println("Average is " + mean);
    System.out.println("Time taken: " + ((end - start) / 1_000_000) + " ms");
  }
}
