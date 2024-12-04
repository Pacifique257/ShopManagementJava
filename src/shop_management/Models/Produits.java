package shop_management.Models;

public class Produits {

    private int idProduct;
    private String prodName;
    private Double prixUnit;
    private Double quantiteProd;
    public String getProdName;


  
    public Produits() {
    }

    public Produits(String prodName, Double prixUnit, Double quantiteProd) {
        this.prodName = prodName;
        this.prixUnit = prixUnit;
        this.quantiteProd = quantiteProd;
      
    }

    public Produits(int idProduct, String prodName, Double prixUnit, Double quantiteProd) {
        this.idProduct = idProduct;
        this.prodName = prodName;
        this.prixUnit = prixUnit;
        this.quantiteProd = quantiteProd;
      
    }
     
    


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(Double prixUnit) {
        this.prixUnit = prixUnit;
    }

  
    public Double getQuantiteProd() {
        return quantiteProd;
    }

    public void setQuantiteProd(Double quantiteProd) {
        this.quantiteProd = quantiteProd;
    }

   

    @Override
    public String toString() {
        return  prodName;
    }

  

   

    public int getQuantite() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getNomProduit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdProduit() {
        
        return 0;
        
    }

   

}
