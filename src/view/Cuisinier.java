package view;

import controller.Controller;
import model.CommandState;
import model.Commande;
import model.Plat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuisinier {
    private final Controller controller;
    private JList<Commande> orders;
    private JButton readyButton;
    private JPanel contentPane;
    public Cuisinier(Controller controller) {
        this.controller = controller;
        updateView();
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orders.getSelectedValue().state = CommandState.PRET;
                controller.saveModel();
                updateView();
            }
        });
    }
    public void updateView() {
        var listModel = new DefaultListModel<Commande>();
        orders.setModel(listModel);
        for (Commande commande : controller.model.commandes) {
            if (commande.state == CommandState.PREPARATION) {
                listModel.addElement(commande);
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cuisinier");
        frame.setContentPane(new Cuisinier(new Controller()).contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
