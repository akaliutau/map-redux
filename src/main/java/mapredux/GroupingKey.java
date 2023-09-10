package mapredux;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingKey {
    private final String[] names;

    public GroupingKey(String... names) {
        if (names == null || names.length == 0)
            throw new IllegalArgumentException("composite key cannot be empty");
        this.names = names;
    }

    public String getKey(final Map<String, Object> record) {
        return Arrays.stream(names).map(record::get).map(String::valueOf).collect(Collectors.joining("#"));
    }


    public String describe() {
        return String.join("#", names);
    }
}
