package BackendProveedores;


import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontProveedores.frmProveedoresCajero.*;

public class BackProveedorCajero {

    ClsConexion CON;
    Connection CN;

    public BackProveedorCajero() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }
    
        private String SElDias() {

        String Dias = "";
        if (LUNES.isSelected()) {
            Dias += "LUNES, ";

        }
        if (MARTES.isSelected()) {
            Dias += "MARTES, ";

        }
        if (MIERCOLES.isSelected()) {
            Dias += "MIERCOLES, ";

        }
        if (JUEVES.isSelected()) {
            Dias += "JUEVES, ";

        }
        if (VIERNES.isSelected()) {
            Dias += "VIERNES, ";

        }
        if (SABADO.isSelected()) {
            Dias += "SABADO, ";

        }
        if (DOMINGO.isSelected()) {
            Dias += "DOMINGO, ";

        }
        return Dias;
    }

    public void Limpiar() {
       // Se limpian todos los campos
        txtNit.setText("");
        txtEmpresa.setText("");
        txtTelefono.setText("");
        txtCantiD.setText("0");
        txtPorceD.setText("0");
        LUNES.setSelected(false);
        MARTES.setSelected(false);
        MIERCOLES.setSelected(false);
        JUEVES.setSelected(false);
        VIERNES.setSelected(false);
        SABADO.setSelected(false);
        DOMINGO.setSelected(false);

        // Se ocultan todos los errores
        lblErrorNit.setVisible(false);
        lblErrorEmpresa.setVisible(false);
        lblErrorTelefono.setVisible(false);
        lblErrorCantiD.setVisible(false);
        lblErrorPorceD.setVisible(false);
    }

    public void Limpiare() {

        // Se ocultan todos los errores
        lblErrorNit.setVisible(false);
        lblErrorEmpresa.setVisible(false);
        lblErrorTelefono.setVisible(false);
        lblErrorCantiD.setVisible(false);
        lblErrorPorceD.setVisible(false);
    }

    public void ListarTabla() {

            // Definición de la configuración de la tabla y sus columnas
               DefaultTableModel modelo = new DefaultTableModel(){

 public boolean isCellEditable(int row, int column)
 {
     return false;
 }
  };        
        modelo.addColumn("Nit");
        modelo.addColumn("Empresa");
        modelo.addColumn("Telefono");
        modelo.addColumn("Dias De Entrega");
        modelo.addColumn("Cantidad Descuento");
        modelo.addColumn("Porcentaje Descuento");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblProv";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                modelo.addRow(Lista);
            }
            tbListProv.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    

    
    public void RegistarProveedor() {
         String Nit = txtNit.getText();
        String Empresa = txtEmpresa.getText();
        String Telefono = txtTelefono.getText();
        String Dias = SElDias();
        int CantiD = Integer.parseInt(txtCantiD.getText());
        int PorceD = Integer.parseInt(txtPorceD.getText());

        if (Nit.equals("")) {
            Limpiare();
            lblErrorNit.setVisible(true);
            txtNit.requestFocus();
        } else if (Empresa.equals("")) {
                        Limpiare();

            lblErrorEmpresa.setVisible(true);
            txtEmpresa.requestFocus();
        } else if (Telefono.equals("")) {
                        Limpiare();

            lblErrorTelefono.setVisible(true);
            txtTelefono.requestFocus();
       

        } else {
            try {
                String ValNit = "SELECT * FROM TblProv WHERE Nit='" + Nit + "'";
                PreparedStatement PS = CN.prepareStatement(ValNit);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                                Limpiare();
                    JOptionPane.showMessageDialog(null, "¡Error! el Proveedor ya existe en la BD");
                } else {
                    String ConsInser = "INSERT INTO TblProv(Nit,"
                            + " Empresa,"
                            + " Telefono,"
                            + " Dias,"
                            + " CantiD,"
                            + " PorceD) "
                            + "VALUES ('" + Nit + "','" + Empresa + "','" + Telefono + "','" + Dias + "','" + CantiD + "','" + PorceD + "')";
                    PreparedStatement PS1 = CN.prepareStatement(ConsInser);
                    PS1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito");
                    ListarTabla();
                    Limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el registro:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    

    public void BuscarProveedorNit() {
          String Nit = txtNit.getText();

        if (!Nit.equalsIgnoreCase("")) {
            try {
                       DefaultTableModel modelo = new DefaultTableModel(){

 public boolean isCellEditable(int row, int column)
 {
     return false;
 }
  };        
                modelo.addColumn("Nit");
                modelo.addColumn("Empresa");
                modelo.addColumn("Telefono");
                modelo.addColumn("Dias De Entrega");
                modelo.addColumn("Cantidad Descuento");
                modelo.addColumn("Porcentaje Descuento");

                String ConsBuscar = "SELECT * FROM TblProv WHERE Nit LIKE'%" + Nit + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProv.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(),   "¡Error!",  JOptionPane.ERROR_MESSAGE);
            }
        } else {
        ListarTabla();
        }
      
     
    }

    public void BuscarProveedorTelefono() {
        String Telefono = txtTelefono.getText();

        if (txtNit.getText().equalsIgnoreCase("")) {
            try {
                        DefaultTableModel modelo = new DefaultTableModel(){ public boolean isCellEditable(int row, int column) {     return false; }  };        
                modelo.addColumn("Nit");
                modelo.addColumn("Empresa");
                modelo.addColumn("Telefono");
                modelo.addColumn("Dias De Entrega");
                modelo.addColumn("Cantidad Descuento");
                modelo.addColumn("Porcentaje Descuento");

                String ConsBuscar = "SELECT * FROM TblProv WHERE Telefono LIKE'%" + Telefono + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProv.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(),"¡Error!",JOptionPane.ERROR_MESSAGE);
            }
        } 
       
    }

    public void BuscarProveedorNombre() {
        String Empresa = txtEmpresa.getText();

         if (txtNit.getText().equalsIgnoreCase("")) {
            try {
                        DefaultTableModel modelo = new DefaultTableModel(){ public boolean isCellEditable(int row, int column) {     return false; }  };        
                modelo.addColumn("Nit");
                modelo.addColumn("Empresa");
                modelo.addColumn("Telefono");
                modelo.addColumn("Dias De Entrega");
                modelo.addColumn("Cantidad Descuento");
                modelo.addColumn("Porcentaje Descuento");

                String ConsBuscar = "SELECT * FROM TblProv WHERE Empresa LIKE'%" + Empresa + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProv.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    public void BuscarProveedorCantidad() {
       int CantiD = Integer.parseInt(txtCantiD.getText());

       if (txtNit.getText().equalsIgnoreCase("")) {
            try {
                        DefaultTableModel modelo = new DefaultTableModel(){ public boolean isCellEditable(int row, int column) {     return false; }  };        
                modelo.addColumn("Nit");
                modelo.addColumn("Empresa");
                modelo.addColumn("Telefono");
                modelo.addColumn("Dias De Entrega");
                modelo.addColumn("Cantidad Descuento");
                modelo.addColumn("Porcentaje Descuento");

                String ConsBuscar = "SELECT * FROM TblProv WHERE CantiD LIKE'%" + CantiD + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProv.setModel(modelo);

                } 
            } catch (Exception e) { JOptionPane.showMessageDialog(null,  "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    public void BuscarProveedorPorcentaje() {
        int PorceD = Integer.parseInt(txtPorceD.getText());

         if (txtNit.getText().equalsIgnoreCase("")) {
            try {
                        DefaultTableModel modelo = new DefaultTableModel(){ public boolean isCellEditable(int row, int column) {     return false; }  };        
                modelo.addColumn("Nit");
                modelo.addColumn("Empresa");
                modelo.addColumn("Telefono");
                modelo.addColumn("Dias De Entrega");
                modelo.addColumn("Cantidad Descuento");
                modelo.addColumn("Porcentaje Descuento");

                String ConsBuscar = "SELECT * FROM TblProv WHERE PorceD LIKE'%" + PorceD + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProv.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!",JOptionPane.ERROR_MESSAGE);
            }
        } 

    }

   
       
       public void ClickListaProveedores() {

try {
            int row = tbListProv.getSelectedRow();
         txtNit.setText(String.valueOf(tbListProv.getValueAt(row, 0)));
           txtEmpresa.setText(String.valueOf(tbListProv.getValueAt(row, 1)));
           txtTelefono.setText(String.valueOf(tbListProv.getValueAt(row, 2)));
           txtCantiD.setText(String.valueOf(tbListProv.getValueAt(row, 4)));
            txtPorceD.setText(String.valueOf(tbListProv.getValueAt(row, 5)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en la consulta:" + e.getMessage(), "¡Error!",JOptionPane.ERROR_MESSAGE);
        }
}
}
