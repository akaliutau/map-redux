package mapredux.comparator;

import java.util.Comparator;
import java.util.Map;

public class StringColumnComparator implements Comparator<Map<String, Object>> {

    private final String colName;

    public StringColumnComparator(String colName) {
        this.colName = colName;
    }

    @Override
    public int compare(Map<String, Object> rec1, Map<String, Object> rec2) {
        if (!rec1.containsKey(colName) || !rec2.containsKey(colName)) {
            return 0;
        }
        return ((String) rec1.get(colName)).compareTo((String) rec2.get(colName));
    }
}
