package mapredux;

import mapredux.reducer.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static mapredux.reducer.Reducer.Type.*;

public class ReducerFactory {

    private final Map<String, Function<String, Reducer<?, ?>>> reducers = new HashMap<>();

    private ReducerFactory() {
        reducers.put(IDENTITY.toString().toLowerCase(), Identity::new);
        reducers.put(LIST.toString().toLowerCase(), CollectorList::new);
        reducers.put(SET.toString().toLowerCase(), CollectorSet::new);
        reducers.put(COUNT.toString().toLowerCase(), Counter::new);
        reducers.put(COUNT_NONNULL.toString().toLowerCase(), CounterNonnull::new);
        reducers.put(AVG.toString().toLowerCase(), CalculateAvg::new);
    }

    static public ReducerFactory getFactory() {
        return new ReducerFactory();
    }

    public void register(String name, Function<String, Reducer<?, ?>> reducer) {
        reducers.put(name, reducer);
    }

    public Reducer<?, ?> get(String name, String reducerType) {
        return reducers.get(reducerType).apply(name);
    }


}
