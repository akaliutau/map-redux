package mapredux.reducer;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ReducerTest {

    @Test
    public void testCounter(){
        Counter<String> counter = new Counter<>("count");
        List<String> values = List.of("str_1", "str_2", "str_3");
        values.forEach(counter::reduce);
        Assertions.assertEquals(3, counter.get());
        Assertions.assertEquals("count", counter.name());
    }
}
