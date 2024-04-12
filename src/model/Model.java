package model;

import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Optional;

public class Model implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public final ArrayList<Plat> plats = new ArrayList<>();
    public final ArrayList<Commande> commandes = new ArrayList<>();
    public final ArrayList<Table> lestables = new ArrayList<>();

    public Model() {}

    public Optional<Plat> getPlatFromName(String name) {
        for (Plat plat : plats) {
            if (plat.name.equals(name)) {
                return Optional.of(plat);
            }
        }
        return Optional.empty();
    }
    public Table getTableFromName(String name) {
        for (Table table : lestables) {
            if (table.name.equals(name)) {
                return table;
            }
        }
        return null;
    }

    public void addPlat(String name, float price) {
        if (getPlatFromName(name).isPresent()) {
            System.out.println("Y a déjà un plat ayant ce nom");
            return;
        }
        plats.add(new Plat(name, price));
    }
    public void addTable(String name) {
        if (getTableFromName(name) != null) {
            System.out.println("Y a déjà une table ayant ce nom");
            return;
        }
        lestables.add(new Table(name));
    }
    public void removePlat(String name) {
        getPlatFromName(name).ifPresent(plats::remove);
    }
    public void removeTable(String name) {
        Optional.of(getTableFromName(name)).ifPresent(lestables::remove);
    }

    public void addCommande(Commande commande) {
        commandes.add(commande);
    }
}
