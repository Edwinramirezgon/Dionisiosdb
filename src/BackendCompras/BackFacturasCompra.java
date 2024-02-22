package BackendCompras;

import Conexion.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontCompras.frmFacturasCompra.*;

public class BackFacturasCompra {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public BackFacturasCompra() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        // Se ocultan todos los errores
        txtNit.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        ListarTablaP();
        ListarTablaFactvac();
        Total();

    }



    public void CrearTablaCar() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("VALOR UNITARIO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

    }

    public double Total() {

        float total = 0;

        try {
            for (int row = 0; row < tbListProducts.getRowCount(); row++) {

                total += Double.parseDouble(String.valueOf(tbListProducts.getValueAt(row, 4)));

            }

            txtTotal.setText(total + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);

        }
        return total;

    }

    public void ListarTablaP() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("FACTURA");
        modelo.addColumn("NIT");
        modelo.addColumn("EMPRESA");
        modelo.addColumn("CAJERO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM TblCompras INNER JOIN TblFactC ON TblCompras.Factura=TblFactC.Fact INNER JOIN TblProv ON TblProv.Nit=TblFactC.Nit";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorrer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getInt("Fact"), RS.getString("Nit"), RS.getString("Empresa"), RS.getString("Nombre"), RS.getDouble("Total"), RS.getDate("Fecha")};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void ListarTablaFactvac() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("VALOR UNITARIO");
        modelo.addColumn("VALOR TOTAL");
        tbListCar.setModel(modelo);
      

        // Cargado de datos a la tabla
        
    }

    public void ListarTablaFact(int fact) {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("VALOR UNITARIO");
        modelo.addColumn("VALOR TOTAL");
      

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM TblCompras INNER JOIN TblProducts ON TblCompras.Codigo=TblProducts.Codigo WHERE TblCompras.Factura = '" + fact + "'";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();
            if (RS.next()) {
                do {
                    Object[] Lista = {RS.getString("Codigo"), RS.getString("Nombre"), RS.getInt("Cantidad"), RS.getDouble("ValorU"), RS.getDouble("ValorT")};
                    modelo.addRow(Lista);
                } while (RS.next());
                tbListCar.setModel(modelo);

            } else {
                JOptionPane.showMessageDialog(null, "¡¡No existe la factura en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void BuscarFacturaNit() {

        String Nit = txtNit.getText();

        if (!Nit.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        modelo.addColumn("FACTURA");
        modelo.addColumn("NIT");
        modelo.addColumn("EMPRESA");
        modelo.addColumn("CAJERO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");
               

                String ConsLista = "SELECT * FROM TblCompras INNER JOIN TblFactC ON TblCompras.Factura=TblFactC.Fact INNER JOIN TblProv ON TblProv.Nit=TblFactC.Nit WHERE TblProv.Nit LIKE '%" + Nit + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                     Object[] Lista = {RS.getInt("Fact"), RS.getString("Nit"), RS.getString("Empresa"), RS.getString("Nombre"), RS.getDouble("Total"), RS.getDate("Fecha")};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe la factura en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                 
                    txtNit.setText("");
                    txtNit.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
          ListarTablaP();
        }
        Total();
       

    }

    public void ClickProducts() {

        try {
            int row = tbListProducts.getSelectedRow();
            int code = Integer.parseInt(String.valueOf(tbListProducts.getValueAt(row, 0)));
            ListarTablaFact(code);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        

    }

    public void ClickCar() {

        try {
            int row = tbListProducts.getSelectedRow();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    public void BuscarFactNombre() {

    String Empresa = txtNombre.getText();

        if (!Empresa.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
             modelo.addColumn("FACTURA");
        modelo.addColumn("NIT");
        modelo.addColumn("EMPRESA");
        modelo.addColumn("CAJERO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");
               

                String ConsLista = "SELECT * FROM TblCompras INNER JOIN TblFactC ON TblCompras.Factura=TblFactC.Fact INNER JOIN TblProv ON TblProv.Nit=TblFactC.Nit WHERE TblProv.Empresa LIKE '%" + Empresa + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
              Object[] Lista = {RS.getInt("Fact"), RS.getString("Nit"), RS.getString("Empresa"), RS.getString("Nombre"), RS.getDouble("Total"), RS.getDate("Fecha")};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null,"¡¡No existe la factura en la base de datos!!","¡Error!", JOptionPane.ERROR_MESSAGE);
                  
                    txtNit.setText("");
                    txtNit.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(),  "¡Error!",   JOptionPane.ERROR_MESSAGE);
            }
        } else {
      ListarTablaP();
        }
        Total();
      

}
    
        public void BuscarFactFecha() {

    String Fecha = txtFecha.getText();

        if (!Fecha.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                 modelo.addColumn("FACTURA");
        modelo.addColumn("NIT");
        modelo.addColumn("EMPRESA");
        modelo.addColumn("CAJERO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");
               

                String ConsLista = "SELECT * FROM TblCompras INNER JOIN TblFactC ON TblCompras.Factura=TblFactC.Fact INNER JOIN TblProv ON TblProv.Nit=TblFactC.Nit WHERE TblFactC.Fecha LIKE '%" + Fecha + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
              Object[] Lista = {RS.getInt("Fact"), RS.getString("Nit"), RS.getString("Empresa"), RS.getString("Nombre"), RS.getDouble("Total"), RS.getDate("Fecha")};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null,"¡¡No existe la factura en la base de datos!!","¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtFecha.setText("");
                    txtFecha.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,   "Error en la consulta:" + e.getMessage(),"¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
         ListarTablaP();
        }
        Total();
       

}
    
    
}
