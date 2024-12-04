package shop_management.Controller;

import dao.FournirDao;
import shop_management.Models.Fournir;

import java.sql.*;
import shop_management.Database.DatabaseConnection;
public class FournirController {

    private FournirDao fournirDao;
  
     private Connection conn;


  
    public FournirController() {
        this.fournirDao = FournirDao.getInstance();
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public void ajouterFournir(Fournir fournir) {
        fournirDao.ajouterFournir(fournir);
    }
  
   
}


