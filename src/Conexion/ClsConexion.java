
package Conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClsConexion {
    

    private static final String USER = "EDWIN";
    private static final String PASSWORD = "Ca121203.";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DbDio;integratedSecurity=false;"+ 
    "encrypt=false; trustServerCertificate=false;";
    private Connection CN;
    
    public ClsConexion(){
        CN = null;
    }
    
    public java.sql.Connection getConnection(){
        try {
            
            CN = DriverManager.getConnection(URL, USER, PASSWORD);
            if(CN != null){
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Error al conectar la Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return CN;
    }
}
    
    
    
   

