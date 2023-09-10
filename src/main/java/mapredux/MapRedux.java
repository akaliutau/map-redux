package mapredux;

import mapredux.comparator.ChainComparator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapRedux {

    private final List<Comparator<Map<String, Object>>> comparators = new LinkedList<>();

    private SelectClause[] selects;

    private Predicate<Map<String, Object>> filter = f -> true;
    private GroupingKey groupingKey = new GroupingKey("_default");
    private ReducerFactory reducerFactory = ReducerFactory.getFactory();
    private int limit = Integer.MAX_VALUE;

    private Row getRow() {
        Row row = new Row();
        for (SelectClause select : selects) {
            row.put(
                    select.name,
                    reducerFactory.get(
                            select.asName,
                            select.reducer
                    )
            );
        }
        return row;
    }

    public List<Map<String, Object>> reduce(List<Map<String, Object>> records) {
        final Map<String, Row> accumulator = new LinkedHashMap<>();

        records.stream().filter(filter).forEach(record -> {
            Row row = accumulator.computeIfAbsent(groupingKey.getKey(record), _k -> getRow());
            for (SelectClause select : selects) {
                Object value = record.get(select.name);
                row.get(select.name).reduce(value);
            }
        });

        List<Map<String, Object>> ret = accumulator.values().stream().map(Row::toMap).collect(Collectors.toList());
        // sort rows in accordance with comparators
        if (!comparators.isEmpty()) {
            ChainComparator chainComparator = new ChainComparator();
            comparators.forEach(chainComparator::add);
            ret.sort(chainComparator.get());
        }
        return ret.stream().limit(limit).toList();
    }

    public static class Builder {

        private final MapRedux mapRedux;

        public Builder() {
            this.mapRedux = new MapRedux();
        }

        public Builder withReducers(ReducerFactory reducerFactory) {
            mapRedux.reducerFactory = reducerFactory;
            return this;
        }


        public Builder select(SelectClause... selects) {
            mapRedux.selects = selects;
            return this;
        }

        public Builder where(Predicate<Map<String, Object>> filter) {
            mapRedux.filter = filter;
            return this;
        }

        public Builder groupBy(String... groupBy) {
            mapRedux.groupingKey = new GroupingKey(groupBy);
            return this;
        }

        public Builder orderBy(Comparator<Map<String, Object>> comparator) {
            mapRedux.comparators.add(comparator);
            return this;
        }


        public Builder limit(int limit) {
            mapRedux.limit = limit;
            return this;
        }


        public MapRedux build() {
            // validations
            if (mapRedux.selects == null || mapRedux.selects.length == 0)
                throw new IllegalArgumentException("select clause must contain at least one column");
            return mapRedux;
        }
    }
}
