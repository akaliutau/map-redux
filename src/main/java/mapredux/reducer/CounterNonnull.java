package mapredux.reducer;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterNonnull<T> implements Reducer<T, Integer> {

    private final AtomicInteger count = new AtomicInteger();
    private final String name;

    public CounterNonnull(String name) {
        this.name = name;
    }

    @Override
    public void reduce(T o) {
        if (o != null) {
            count.getAndIncrement();
        }
    }

    @Override
    public Integer get() {
        return count.get();
    }

    @Override
    public String name() {
        return name;
    }
}
