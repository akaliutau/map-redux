package mapredux.reducer;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CalculateAvg implements Reducer<java.lang.Integer, java.lang.Double> {

    private final AtomicLong total = new AtomicLong();
    private final AtomicInteger count = new AtomicInteger();
    private final String name;

    public CalculateAvg(String name) {
        this.name = name;
    }

    @Override
    public void reduce(Integer o) {
        if (o != null) {
            total.getAndAdd(o);
            count.getAndIncrement();
        }
    }

    @Override
    public Double get() {
        return count.get() == 0 ? 0.0d : total.doubleValue() / count.doubleValue();
    }

    @Override
    public String name() {
        return name;
    }
}
