
package Conexion;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClsConexion {
    
     //Declaración de variables globales
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/db_Dionisios";
    private Connection CN;
    
    public ClsConexion(){
        CN = null;
    }
    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            CN = (Connection)DriverManager.getConnection(URL, USER, PASSWORD);
            if(CN != null){
            
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Error al conectar la Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return CN;
    }
    
    public void close(){
        try{
            CN.close();
            JOptionPane.showMessageDialog(null, "Base de Datos desconectada de phpMyAdmin");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Error al conectar la Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
