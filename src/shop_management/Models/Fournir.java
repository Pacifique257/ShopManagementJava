package shop_management.Models;

public class Fournir {
    private int idProduit;
    private int idPersonne;

    public Fournir(int idProduit, int idPersonne) {
        this.idProduit = idProduit;
        this.idPersonne = idPersonne;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String toString() {
        return "Fournir [idProduit=" + idProduit + ", idPersonne=" + idPersonne + "]";
    }
}
