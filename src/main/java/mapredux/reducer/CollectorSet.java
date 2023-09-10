package mapredux.reducer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectorSet<T> implements Reducer<T, List<T>> {

    private final Set<T> values = new HashSet<>();
    private final String name;

    public CollectorSet(String name) {
        this.name = name;
    }

    @Override
    public void reduce(T t) {
        if (t != null) {
            values.add(t);
        }
    }

    @Override
    public List<T> get() {
        return new ArrayList<>(values);
    }

    @Override
    public String name() {
        return name;
    }
}
