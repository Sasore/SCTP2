/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2;
import java.sql.SQLException;
import sctp2.BancodeDados.conexao;
import sctp2.PacotedeLogin.Logar;
/**
 *
 * @author Adriano
 */
public class SCTP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Logar acesso= new Logar();
        acesso.setVisible(true);
        
        
        
    }
    
}
