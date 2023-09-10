package mapredux.reducer;

import java.util.ArrayList;
import java.util.List;

public class CollectorList<T> implements Reducer<T, List<T>> {

    private final List<T> values = new ArrayList<>();
    private final String name;

    public CollectorList(String name) {
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
        return values;
    }

    @Override
    public String name() {
        return name;
    }
}
