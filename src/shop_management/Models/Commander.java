package shop_management.Models;

public class Commander {

    private int idProduit;
    private int idCommande;
    private String prodName; // Champ à inclure dans votre classe
    private int quantiteCommande; // Champ à inclure dans votre classe

    public Commander(int idProduit, int idCommande) {
        this.idProduit = idProduit;
        this.idCommande = idCommande;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Override
    public String toString() {
        return "Commander{" + "idProduit=" + idProduit + ", idCommande=" + idCommande + '}';
    }

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public String getProdName() {
        return prodName;
    }

}
