package shop_management.View.Modify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import shop_management.Controller.PersonneController;
import shop_management.Models.Personnes;

public class FournisseurModify {

    private JTextField fullNameField;
    private JTextField adresseTextField;
    private JTextField emailTextField;
    private JTextField telephoneTextField;
    private JButton modifyButton;
    private JButton deleteButton;
    private JTable fournisseurTable;
    private PersonneController controller;
    private JPanel panel;

    // Constructeur par défaut
    public FournisseurModify() {
        controller = new PersonneController();
        initializeFields();
    }

    // Constructeur avec ID fournisseur
    public FournisseurModify(int idPersonne) {
        controller = new PersonneController();
        initializeFields();
        trouverPersonneParId(idPersonne);
    }

    // Méthode d'initialisation des champs
    private void initializeFields() {
        fullNameField = new JTextField();
        adresseTextField = new JTextField();
        emailTextField = new JTextField();
        telephoneTextField = new JTextField();
    }

    public void FournisseurModifyContent(JPanel panel) {
        this.panel = panel;
        panel.setLayout(new BorderLayout());

        // Effacer le contenu précédent
        panel.removeAll();

        // Créer le panneau principal pour les champs
        JPanel contentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Ajouter les champs au formulaire
        contentPanel.add(createLabel("Nom complet:"));
        fullNameField = createTextField();
        contentPanel.add(fullNameField);

        contentPanel.add(createLabel("Adresse:"));
        adresseTextField = createTextField();
        contentPanel.add(adresseTextField);

        contentPanel.add(createLabel("Email:"));
        emailTextField = createTextField();
        contentPanel.add(emailTextField);

        contentPanel.add(createLabel("Téléphone:"));
        telephoneTextField = createTextField();
        contentPanel.add(telephoneTextField);

        // Ajouter le formulaire au panel principal
        panel.add(contentPanel, BorderLayout.NORTH);

        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        modifyButton.addActionListener(e -> modifyFournisseurAction());
        deleteButton.addActionListener(e -> deleteFournisseurAction());

        // Charger et afficher la table des fournisseurs
        updateTable(controller.getAllFournisseurs());

        panel.revalidate();
        panel.repaint();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        textField.setBorder(border);
        return textField;
    }

    private void modifyFournisseurAction() {
        int selectedRow = fournisseurTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idPersonne = (int) fournisseurTable.getValueAt(selectedRow, 0);
                String fullName = fullNameField.getText().trim();
                String adresse = adresseTextField.getText().trim();
                String email = emailTextField.getText().trim();
                String telephone = telephoneTextField.getText().trim();

                if (fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Le nom ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Personnes fournisseur = new Personnes(idPersonne, fullName, adresse, email, telephone);
                controller.modifierFournisseur(fournisseur);
                updateTable(controller.getAllFournisseurs());
                JOptionPane.showMessageDialog(panel, "Fournisseur modifié avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de la modification : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner un fournisseur à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteFournisseurAction() {
        int selectedRow = fournisseurTable.getSelectedRow();
        if (selectedRow >= 0) {
            int idPersonne = (int) fournisseurTable.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(panel, "Êtes-vous sûr de vouloir supprimer ce fournisseur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.supprimerPersonnes(idPersonne);
                updateTable(controller.getAllFournisseurs());
                JOptionPane.showMessageDialog(panel, "Fournisseur supprimé avec succès !");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Veuillez sélectionner un fournisseur à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateTable(List<Personnes> fournisseurs) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Personne", "Nom complet", "Adresse", "Email", "Telephonne"}, 0);

        for (Personnes fournisseur : fournisseurs) {
            model.addRow(new Object[]{fournisseur.getIdPersonne(), fournisseur.getFullName(), fournisseur.getPersonAdress(), fournisseur.getEmail(), fournisseur.getPhoneNumber()});
        }

        if (fournisseurTable == null) {
            fournisseurTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(fournisseurTable);
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            fournisseurTable.setModel(model);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void trouverPersonneParId(int idPersonne) {
        Personnes personne = controller.trouverPersonneParId(idPersonne);
        if (personne != null) {
            fullNameField.setText(personne.getFullName());
            adresseTextField.setText(personne.getPersonAdress());
            emailTextField.setText(personne.getEmail());
            telephoneTextField.setText(personne.getPhoneNumber());
        } else {
            JOptionPane.showMessageDialog(panel, "Fournisseur non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
