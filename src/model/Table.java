package model;

import java.io.Serial;
import java.io.Serializable;

public class Table implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String name;
    public Table(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Table{" + name + "}";
    }
}
