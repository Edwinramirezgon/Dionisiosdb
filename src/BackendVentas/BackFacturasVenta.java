package BackendVentas;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontVentas.frmFacturasVenta.*;

public class BackFacturasVenta {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public BackFacturasVenta() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        txtDni.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
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
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!",
                    JOptionPane.ERROR_MESSAGE);

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
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblFactV";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getString(1), RS.getString(6), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5),};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

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
        modelo.addColumn("FECHA");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblVentas WHERE Factura = '" + fact + "'";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();
            if (RS.next()) {
                do {
                    Object[] Lista = {RS.getString(5), RS.getString(6), RS.getString(7), RS.getString(8), RS.getString(9), RS.getString(10),};
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

    public void BuscarFacturaDni() {

        String Dni = txtDni.getText();

        if (!Dni.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
     modelo.addColumn("FACTURA");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

                String ConsLista = "SELECT * FROM tblFactV WHERE Dni LIKE'%" + Dni + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                               Object[] Lista = {RS.getString(1), RS.getString(6), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe la factura en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    txtDni.setText("");
                    txtDni.requestFocus();
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
        Limpiar();

    }

    public void ClickCar() {

        try {
            int row = tbListProducts.getSelectedRow();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();

    }

    public void BuscarFactNombre() {

        String Nombre = txtNombre.getText();

        if (!Nombre.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        modelo.addColumn("FACTURA");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

                String ConsLista = "SELECT * FROM tblFactV WHERE Nombre LIKE'%" + Nombre + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                              Object[] Lista = {RS.getString(1), RS.getString(6), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe la factura en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);

                    txtDni.setText("");
                    txtDni.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
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
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("VALOR TOTAL");
        modelo.addColumn("FECHA");

                String ConsLista = "SELECT * FROM tblFactV WHERE Fecha LIKE'%" + Fecha + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsLista);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                                 Object[] Lista = {RS.getString(1), RS.getString(6), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe la factura en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtFecha.setText("");
                    txtFecha.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTablaP();
        }
        Total();

    }

}
