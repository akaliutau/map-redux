package mapredux;

/**
 * Describes the field in Map-like data structure
 * <p>
 * name - the name of the field
 * <p>
 * type - type representing the value of this field
 * <p>
 * mode - REQUIRED - validation will fail if the field is null,
 * NULLABLE - any acceptable value, including null
 * REPEATABLE - the array of values of the same type
 */
public class FieldType<T> {
    final String name;
    final Class<T> type;
    final Mode mode;

    public FieldType(String name, Class<T> type, Mode mode) {
        this.name = name;
        this.type = type;
        this.mode = mode;
    }

    public enum Mode {
        REQUIRED,
        NULLABLE,
        REPEATABLE
    }

}
