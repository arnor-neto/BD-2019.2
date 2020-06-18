
package fakeifood;

import db.*;
import java.sql.SQLException;
public class FakeIfood {
    public static void main(String[] args) throws SQLException {
        
        CreateDB.createNewDatabase("fakeIfood.db");
        CreateTables.main();
        InsereDados.main();
        BuscaDados.main();
        FrameInicial FI = new FrameInicial();
        FI.setVisible(true);     
    }
    
}


