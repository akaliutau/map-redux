package mapredux.reducer;

public interface Reducer<U, V> {

    void reduce(U u);

    V get();

    String name();

    enum Type {
        IDENTITY,
        COUNT,
        COUNT_NONNULL,
        AVG,
        LIST,
        SET
    }
}
