package mapredux.reducer;

import mapredux.FieldType;
import mapredux.MapRedux;
import mapredux.comparator.StringColumnComparator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static mapredux.SelectClause.column;

public class MapReduxTest {

    private final List<Map<String, Object>> records = List.of(
            Map.of(
                    "name", "Alice",
                    "age", 25,
                    "region", "Europe",
                    "country", "Netherlands",
                    "hobbies", "swimming"
            ),
            Map.of(
                    "name", "Bob",
                    "age", 23,
                    "region", "North America",
                    "country", "US",
                    "hobbies", "reading"
            ),
            Map.of(
                    "name", "Eve",
                    "age", 29,
                    "region", "North America",
                    "country", "US",
                    "hobbies", "spying"
            ),
            Map.of(
                    "name", "Yuni",
                    "age", 31,
                    "region", "Asia",
                    "country", "South Korea",
                    "hobbies", "walking"
            ),
            Map.of(
                    "name", "X",
                    "age", 18,
                    "region", "us"
            )
    );

    @BeforeClass
    public static void init() {
    }


    @Test
    public void functionalTest() {
        MapRedux mr = new MapRedux.Builder()
                .select(
                        column("name", "count", "count"),
                        column("age", "avg_age", "avg"),
                        column("region"),
                        column("country"),
                        column("hobbies", "set")
                )
                .where(m -> (int) m.get("age") > 18)
                .groupBy("region", "country")
                .orderBy(new StringColumnComparator("region"))
                .build();
        List<Map<String, Object>> result = mr.reduce(records);
        System.out.println(result);
    }

}
