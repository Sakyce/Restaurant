package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class Commande implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public final ArrayList<Plat> plats = new ArrayList<>();
    public float totalPrice = 0;
    public Table table;
    public CommandState state = CommandState.ATTENTE;
    public void addPlat(Plat plat) {
        plats.add(plat);
        totalPrice += plat.price;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "plats=" + plats +
                ", totalPrice=" + totalPrice +
                ", table=" + table +
                ", state=" + state +
                '}';
    }
}
