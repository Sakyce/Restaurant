package view;

import controller.Controller;
import model.Plat;
import model.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gestionnaire {
    private JList<String> platsListe;
    private JButton ajouterPlatButton;
    private JButton retirerPlatButton;
    private JTextField nomField;
    private JPanel contentPane;
    private JSpinner priceSpinner;
    private JList<String> tablesListe;
    private JTextField nomTableField;
    private JButton retirerTableButton;
    private JButton ajouterTableButton;

    public Controller controller;

    public Gestionnaire(Controller controller) {
        this.controller = controller;
        updatePlats(controller.model.plats);
        updateTables(controller.model.lestables);

        ajouterPlatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlats(controller.addPlat(nomField.getText(),(float)(int)priceSpinner.getValue()));
                controller.saveModel();
            }
        });
        retirerPlatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlats(controller.removePlat(nomField.getText()));
                controller.saveModel();
            }
        });
        retirerTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables(controller.removeTable(tablesListe.getSelectedValue()));
                controller.saveModel();
            }
        });
        ajouterTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables(controller.addTable(nomTableField.getText()));
                controller.saveModel();
            }
        });
    }

    public void updateTables(ArrayList<Table> tables) {
        var listModel = new DefaultListModel<String>();
        tablesListe.setModel(listModel);
        for (Table table : tables) {
            listModel.addElement(table.name);
        }
    }

    public void updatePlats(ArrayList<Plat> plats) {
        var listModel = new DefaultListModel<String>();
        platsListe.setModel(listModel);
        for (Plat plat : plats) {
            listModel.addElement(plat.name + " (" + plat.price +"$)");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestionnaire");
        frame.setContentPane(new Gestionnaire(new Controller()).contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
