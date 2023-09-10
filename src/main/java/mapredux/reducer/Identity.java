package mapredux.reducer;

public class Identity<U> implements Reducer<U, U> {

    private final String name;
    private U u;

    public Identity(String name) {
        this.name = name;
    }

    @Override
    public void reduce(U u) {
        this.u = u;
    }

    @Override
    public U get() {
        return u;
    }

    @Override
    public String name() {
        return name;
    }
}
