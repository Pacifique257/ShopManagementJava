package shop_management.Controller;


import Dao.CommanderDao;
import java.sql.Connection;
import shop_management.Database.DatabaseConnection;
import shop_management.Models.Commander;
import shop_management.Models.Fournir;

public class CommanderController {
     private CommanderDao commanderDao;
  
     private Connection conn;


  
    public CommanderController() {
        this.commanderDao = CommanderDao.getInstance();
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public void ajouterCommender(Commander commander) {
        commanderDao.ajouterCommender(commander);
    }
}
