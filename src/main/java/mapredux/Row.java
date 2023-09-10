package mapredux;

import mapredux.reducer.Reducer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Row extends HashMap<String, Reducer> {
    public Map<String, Object> toMap() {
        return entrySet().stream().collect(Collectors.toMap(k -> k.getValue().name(), k -> k.getValue().get()));
    }
}
