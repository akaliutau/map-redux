package mapredux.comparator;

import java.util.Comparator;
import java.util.Map;

public class ChainComparator {

    private Comparator<Map<String, Object>> comparator;

    public void add(Comparator<Map<String, Object>> segment) {
        if (comparator == null) {
            comparator = segment;
        } else {
            comparator = comparator.thenComparing(segment);
        }
    }

    public Comparator<Map<String, Object>> get() {
        return comparator;
    }
}
