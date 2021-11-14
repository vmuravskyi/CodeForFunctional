package my;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Averager {
    private double total;
    private long count;

    public Averager() {
    }

    public void include(double d) {
        total += d;
        count++;
    }

    public void merge(Averager other) {
        total += other.total;
        count += other.count;
    }

    public double getTotal() {
        return total / count;
    }
}

public class CollectAverage {
    public static void main(String[] args) {

        long start = System.nanoTime();

        Averager result = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .parallel()
                .unordered()
                .limit(200_000_000L)
                .map(operand -> Math.sin(operand))
                .collect(() -> new Averager(),
                        (averager, value) -> averager.include(value),
                        (averager, averager2) -> averager.merge(averager2));

        long end = System.nanoTime();
        System.out.println("Average is " + result.getTotal() + " computation took " + ((end - start) / 1_000_000) + " ms");

    }
}
