package mapredux;

import mapredux.reducer.Reducer;

public class SelectClause {
    final String name;
    final String asName;
    final String reducer;

    private SelectClause(String name, String as, String reducer) {
        this.name = name;
        this.asName = as;
        this.reducer = reducer;
    }

    public static SelectClause column(String name) {
        return new SelectClause(name, name, Reducer.Type.IDENTITY.toString().toLowerCase());
    }

    public static SelectClause column(String name, String reducer) {
        return new SelectClause(name, name, reducer);
    }

    public static SelectClause column(String name, String as, String reducer) {
        return new SelectClause(name, as, reducer);
    }

}
