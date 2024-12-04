package shop_management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import shop_management.Controller.CommandeController;
import shop_management.Controller.PersonneController;
import shop_management.Controller.ProductController;

import shop_management.View.AddDettes;
import shop_management.View.AddFactures;

import shop_management.View.AddProduct;
import shop_management.View.Select.DettesSelecte;
import shop_management.View.Select.FactureSelecte;

public class Shop_management extends JFrame {

    private JPanel contentPanel;
    AddProduct addProductView = new AddProduct();
    AddFactures addFactures = new AddFactures();
    AddDettes addDettes = new AddDettes();
    DettesSelecte debtTableView = new DettesSelecte();
    FactureSelecte facturesTableView = new FactureSelecte();

    public Shop_management() {
        super("Shop Management");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);

        this.setJMenuBar(createMenuBar());
        JPanel contentPane = (JPanel) getContentPane();

        // Create the JTree
        JTree projectTree = createProjectTree();

        //Les couleurs dans le tree
        projectTree.setBackground(new java.awt.Color(37, 138, 196)); // Bleu : #258AC4

        // Customize text color using DefaultTreeCellRenderer
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setTextNonSelectionColor(java.awt.Color.BLACK); // Blanc pour le texte non sélectionné
        renderer.setTextSelectionColor(java.awt.Color.WHITE); // Noir pour le texte sélectionné
        renderer.setBackgroundNonSelectionColor(new java.awt.Color(37, 138, 196)); // Bleu pour fond non sélectionné
        renderer.setBackgroundSelectionColor(java.awt.Color.BLUE); // Couleur jaune pour la sélection
        projectTree.setCellRenderer(renderer);

        this.add(new JScrollPane(projectTree));
        JScrollPane scrollPane = new JScrollPane(projectTree);
        projectTree.setPreferredSize(new Dimension(150, 0));

        // Setup editor panel
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

// Créer le JScrollPane avec contentPanel
        JScrollPane editorScrollPane = new JScrollPane(contentPanel);

// Définir également la couleur de fond du JScrollPane en blanc
        editorScrollPane.getViewport().setBackground(Color.WHITE);
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, editorScrollPane);
        contentPane.add(mainSplitPane, BorderLayout.CENTER);

        // Add TreeSelectionListener
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    handleNodeSelection(selectedNode);
                }
            }
        });

        // Window closing confirmation
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int clickedButton = JOptionPane.showConfirmDialog(Shop_management.this,
                        "Êtes-vous sûr de vouloir quitter?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                if (clickedButton == JOptionPane.YES_OPTION) {
                    Shop_management.this.dispose();
                }
            }
        });
    }

    private JTree createProjectTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Gestion du magasin");

        // Create nodes
        DefaultMutableTreeNode addCategory = new DefaultMutableTreeNode("Ajouter");
        addCategory.add(new DefaultMutableTreeNode("Produits"));
        addCategory.add(new DefaultMutableTreeNode("Clients"));
        addCategory.add(new DefaultMutableTreeNode("Fournisseurs"));
        addCategory.add(new DefaultMutableTreeNode("Factures"));
        addCategory.add(new DefaultMutableTreeNode("Commandes"));
        addCategory.add(new DefaultMutableTreeNode("Dettes"));

        DefaultMutableTreeNode selectCategory = new DefaultMutableTreeNode("Affichage");
        selectCategory.add(new DefaultMutableTreeNode("Produits"));
        selectCategory.add(new DefaultMutableTreeNode("Clients"));
        selectCategory.add(new DefaultMutableTreeNode("Fournisseurs"));
        selectCategory.add(new DefaultMutableTreeNode("Facture"));
        selectCategory.add(new DefaultMutableTreeNode("Commandes"));
        selectCategory.add(new DefaultMutableTreeNode("Dette"));

        DefaultMutableTreeNode modifyCategory = new DefaultMutableTreeNode("Modification");
        modifyCategory.add(new DefaultMutableTreeNode("Produits"));
        modifyCategory.add(new DefaultMutableTreeNode("Factures"));
        modifyCategory.add(new DefaultMutableTreeNode("Dettes"));

        DefaultMutableTreeNode deleteCategory = new DefaultMutableTreeNode("Suppression");
        deleteCategory.add(new DefaultMutableTreeNode("Clients"));
        deleteCategory.add(new DefaultMutableTreeNode("Produits"));
        deleteCategory.add(new DefaultMutableTreeNode("Commandes"));
        deleteCategory.add(new DefaultMutableTreeNode("Dettes"));

        // Add categories to root
        root.add(addCategory);
        root.add(selectCategory);
        root.add(modifyCategory);
        root.add(deleteCategory);

        // Create the tree model and JTree
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        return new JTree(treeModel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        // Définition de la police pour augmenter la taille du texte
        Font menuFont = new Font("Arial", Font.BOLD, 25);
        Font JItemFont = new Font("Arial", Font.PLAIN, 12);

        JMenu productMenu = new JMenu("Produits");
        productMenu.setFont(menuFont); // Augmente la taille du texte
        productMenu.setForeground(new Color(37, 138, 196));

        JMenuItem displayProductItem = new JMenuItem("Afficher");
        displayProductItem.setFont(JItemFont);
        displayProductItem.setForeground(new Color(37, 138, 196));

// Appel correct de la méthode listeProduits() dans l'ActionListener
        displayProductItem.addActionListener(e -> {
            // Effacer le contenu précédent du contentPanel
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            // Créer une JTable pour afficher les produits
            JTable productTable = new JTable();

            // Appeler la méthode du contrôleur pour récupérer et afficher les produits dans la table
            ProductController productController = new ProductController();
            productController.listeProduits(productTable);

            // Ajouter la table dans un JScrollPane et l'afficher dans le contentPanel
            JScrollPane scrollPane = new JScrollPane(productTable);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            // Réactualiser le panneau principal
            contentPanel.revalidate();
            contentPanel.repaint();
        });
        // Supprimez les parenthèses supplémentaires ici

        productMenu.add(displayProductItem);
        menuBar.add(productMenu);

        JMenu fournisseurMenu = new JMenu("Fournisseurs");
        fournisseurMenu.setFont(menuFont);
        fournisseurMenu.setForeground(new Color(37, 138, 196));
        JMenuItem displayFournisseurItem = new JMenuItem("Afficher");
        displayFournisseurItem.setFont(JItemFont);
        displayFournisseurItem.setForeground(new Color(37, 138, 196));
        displayFournisseurItem.addActionListener(e -> {
            // Créer une JTable pour afficher les produits
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            // Créer une JTable pour afficher les produits
            JTable fournisseurTable = new JTable();

            // Appeler la méthode du contrôleur pour récupérer et afficher les produits dans la table
            PersonneController personneController = new PersonneController();
            personneController.getAllFournisseurs(fournisseurTable);

            // Ajouter la table dans un JScrollPane et l'afficher dans le contentPanel
            JScrollPane scrollPane = new JScrollPane(fournisseurTable);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            // Réactualiser le panneau principal
            contentPanel.revalidate();
            contentPanel.repaint();

        });
        fournisseurMenu.add(displayFournisseurItem);
        menuBar.add(fournisseurMenu);

        JMenu clientMenu = new JMenu("Client");
        clientMenu.setFont(menuFont);
        clientMenu.setForeground(new Color(37, 138, 196));
        JMenuItem displayClientItem = new JMenuItem("Afficher");
        displayClientItem.setFont(JItemFont);
        displayClientItem.setForeground(new Color(37, 138, 196));
        displayClientItem.addActionListener(e -> {
            // Créer une JTable pour afficher les produits
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            // Créer une JTable pour afficher les produits
            JTable clientTable = new JTable();

            // Appeler la méthode du contrôleur pour récupérer et afficher les produits dans la table
            PersonneController personneController = new PersonneController();
            personneController.getAllClient(clientTable);

            // Ajouter la table dans un JScrollPane et l'afficher dans le contentPanel
            JScrollPane scrollPane = new JScrollPane(clientTable);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            // Réactualiser le panneau principal
            contentPanel.revalidate();
            contentPanel.repaint();

        });
        clientMenu.add(displayClientItem);
        menuBar.add(clientMenu);

        JMenu orderMenu = new JMenu("Commandes");
        orderMenu.setFont(menuFont);
        orderMenu.setForeground(new Color(37, 138, 196));
        JMenuItem displayOrderItem = new JMenuItem("Afficher");
        displayOrderItem.setFont(JItemFont);
        displayOrderItem.setForeground(new Color(37, 138, 196));
        displayOrderItem.addActionListener(e -> {
            // Effacer le contenu précédent du contentPanel
            contentPanel.removeAll();
            contentPanel.revalidate();
            contentPanel.repaint();

            // Créer une JTable pour afficher les produits
            JTable commandeTable = new JTable();

            // Appeler la méthode du contrôleur pour récupérer et afficher les produits dans la table
            CommandeController commandeController = new CommandeController();
            commandeController.listeCommandes(commandeTable);

            // Ajouter la table dans un JScrollPane et l'afficher dans le contentPanel
            JScrollPane scrollPane = new JScrollPane(commandeTable);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            // Réactualiser le panneau principal
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        orderMenu.add(displayOrderItem);
        menuBar.add(orderMenu);

        return menuBar;
    }

    private void handleNodeSelection(DefaultMutableTreeNode selectedNode) {
        String nodeName = selectedNode.toString();

        // Effacer le contenu précédent du contentPanel
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();

        switch (nodeName) {
            case "Produits":
                addProductView.addProductContent(contentPanel);
                break;

            case "Clients":
                //  AddCustomers.addCustomersContent(contentPanel);
                break;
            case "Fournisseurs":
                // AddFournisseurs.addfournisseursContent(contentPanel);
                break;
            case "Factures":
                addFactures.addFacturesContent(contentPanel);
                break;
            case "Commandes":
                //  AddCommandes.addCommadesContent(contentPanel);
                break;
            case "Dettes":
                addDettes.addDettesContent(contentPanel);
                break;
            case "Dette":
                debtTableView.addDebtTableContent(contentPanel);
                break;
            case "Facture":
                facturesTableView.addFacturesContent(contentPanel);
                break;

            // Add cases for other nodes as needed
            default:
                break;
        }
    }

    private void showProductContent() {
        addProductView.addProductContent(contentPanel);
    }
//
//    private void showInvoiceContent() {
//        AddCustomers.addCustomersContent(contentPanel);
//    }
//
//    private void showOrderContent() {
//        AddFournisseurs.addfournisseursContent(contentPanel);
//    }

    public static void main(String[] args) {
        Shop_management shop = new Shop_management();
        System.out.println();
        shop.setVisible(true);

    }

    private void listeProduits() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
