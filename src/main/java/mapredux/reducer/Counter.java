package mapredux.reducer;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter<T> implements Reducer<T, java.lang.Integer> {

    private final AtomicInteger count = new AtomicInteger();
    private final String name;

    public Counter(String name) {
        this.name = name;
    }

    @Override
    public void reduce(T o) {
        count.getAndIncrement();
    }

    @Override
    public java.lang.Integer get() {
        return count.get();
    }

    @Override
    public String name() {
        return name;
    }
}
