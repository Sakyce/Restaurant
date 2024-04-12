package model;

import java.io.Serial;
import java.io.Serializable;

public class Plat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public final String name;
    public final float price;

    public Plat(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plat{ " + name + " ("+ price + "$) }";
    }
}
