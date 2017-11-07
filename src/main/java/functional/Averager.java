package functional;

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
    Average av = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
        .limit(1_000)
        .collect(() -> new Average(), (r, d) -> r.include(d), (r1, r2) -> r1.merge(r2));
    double mean = av.get();
    System.out.println("Average is " + mean);
  }
}
