package controller;

import model.Commande;
import model.Model;
import model.Plat;

import java.util.ArrayList;

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

    public void addCommande(Commande currentCommande) {

    }
}
