package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    public Model model;
    public Controller() {
        loadModel();
    }
    public void saveModel() {
        try {
            ResourceManager.save(model, "restaurant.save");
        } catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }
    private void loadModel() {
        try {
            model = (Model) ResourceManager.load("restaurant.save");
        }
        catch (Exception e) {
            model = new Model();
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }
    public ArrayList<Plat> addPlat(String name, float price) {
        model.addPlat(name, price);
        return model.plats;
    }
    public ArrayList<Plat> removePlat(String name) {
        model.removePlat(name);
        return model.plats;
    }

    public ArrayList<Commande> addCommande(Commande currentCommande) {
        model.commandes.add(currentCommande);
        System.out.println(currentCommande);
        return model.commandes;
    }

    public ArrayList<Table> addTable(String text) {
        model.addTable(text);
        return model.lestables;
    }

    public ArrayList<Table> removeTable(String selectedValue) {
        model.removeTable(selectedValue);
        return model.lestables;
    }

    public List<Commande> getCommandesOfState(CommandState state) {
        return model.commandes.stream().filter(p -> p.state.equals(state)).collect(Collectors.toList());
    }
}
