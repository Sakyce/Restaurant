package view;

import controller.Controller;
import model.CommandState;
import model.Commande;
import model.Plat;

import javax.swing.*;

public class Serveur {
    private final Controller controller;
    private JButton ajouterPlatButton;
    private JButton envoyerCommandeButton;
    private JList<String> possiblePlats;
    private JList<String> currentCommandeList;
    private JButton annulerCommandeButton;
    private JPanel contentPane;
    private JTextField nTableTextField;
    private JSpinner nombrePlats;
    private JList<String> currentCommandesList;
    private JButton confirmDeliveryButton;
    private Commande currentCommande = new Commande();

    public Serveur(Controller controller) {
        this.controller = controller;

        updateView();

        annulerCommandeButton.addActionListener(e -> {
            currentCommande = new Commande();
            updateView();
        });
        envoyerCommandeButton.addActionListener(e -> {
            currentCommande.table = controller.model.getTableFromName(nTableTextField.getText());

            if (currentCommande.table == null) {
                JOptionPane.showMessageDialog(null, "Le numéro de la table n'est pas valide!");
                return;
            }
            currentCommande.state = CommandState.PREPARATION;

            this.controller.addCommande(currentCommande);
            this.controller.saveModel();
            currentCommande = new Commande();
            updateView();
        });
        ajouterPlatButton.addActionListener(e -> {
            for (String platName : possiblePlats.getSelectedValuesList()) {
                this.controller.model.getPlatFromName(platName).ifPresent(currentCommande::addPlat);
            }
            updateView();
        });
    }

    public void updateView() {
        var listModel = new DefaultListModel<String>();
        possiblePlats.setModel(listModel);
        for (Plat plat : controller.model.plats) {
            listModel.addElement(plat.name);
        }

        var listModel2 = new DefaultListModel<String>();
        currentCommandeList.setModel(listModel2);
        for (Plat plat : currentCommande.plats) {
            listModel2.addElement(plat.name);
        }

        var listModel3 = new DefaultListModel<String>();
        currentCommandesList.setModel(listModel3);
        for (Commande commande : controller.getCommandesOfState(CommandState.PRET)) {
            listModel3.addElement("Commande pour la table "+ commande.table.name);
        }

        envoyerCommandeButton.setText("Envoyer commande ("+currentCommande.totalPrice+"$)");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Serveur");
        frame.setContentPane(new Serveur(new Controller()).contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
