package BackendVentas;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontVentas.frmVentas.*;

public class BackVentas {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public BackVentas() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        txtDni.setText("");
        txtAnadirC.setText("");
        txtElimi.setText("");
        txtDni.setText("");

        // Se ocultan todos los errores
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);

    }

    public void Limpiare() {

        // Se ocultan todos los errores
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);

    }

    public void CrearTablaCar() {

        // Definición de la configuración de la tabla y sus columnas
        Carrito = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Carrito.addColumn("CODIGO");
        Carrito.addColumn("NOMBRE");
        Carrito.addColumn("CANTIDAD");
        Carrito.addColumn("VALOR UNITARIO");
        Carrito.addColumn("VALOR TOTAL");

        tbListCar.setModel(Carrito);

    }

    public double Total() {

        float total = 0;

        try {
            for (int row = 0; row < Carrito.getRowCount(); row++) {

                total += Double.parseDouble(String.valueOf(this.Carrito.getValueAt(row, 4)));

            }

            txtTotal.setText(total + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        return total;

    }

    public void Pagar() {

        String NombreC = (String) ltClientes.getSelectedItem();

        if (NombreC.equals("SELECCIONE UN CLIENTE")) {
            Limpiare();
            txtDni.requestFocus();
            JOptionPane.showMessageDialog(null, "Debe escojer un cliente");

        } else {
            try {

                String ConsBuscar = "SELECT * FROM TblClients WHERE Nombre ='" + NombreC + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();

                String ConsInsert2 = "INSERT INTO TblFactV(Nombre,"
                        + " Total ) "
                        + "VALUES ('" + NombreC + "','" + Total() + "')";

                PreparedStatement PS4 = CN.prepareStatement(ConsInsert2);
                PS4.executeUpdate();

                String ConsFact = "SELECT MAX(Fact) AS id FROM TblfactV";
                PreparedStatement PS5 = CN.prepareStatement(ConsFact);
                ResultSet RS5 = PS5.executeQuery();
                int fact = 0;
                while (RS5.next()) {
                    fact = RS5.getInt(1);
                }

                for (int rowC = 0; rowC < Carrito.getRowCount(); rowC++) {
                    String Codigo = (String) Carrito.getValueAt(rowC, 0);
                    String ConsBuscar1 = "SELECT * FROM TblProducts WHERE Codigo ='" + Codigo + "'";
                    PreparedStatement PS1 = CN.prepareStatement(ConsBuscar1);
                    ResultSet RS1 = PS1.executeQuery();

                    while (RS1.next()) {
                        String Code = RS1.getString(1);
                        if (Codigo.equals(Code)) {
                            int CantidadP = 0;
                            int CantidadV = 0;
                            int Cantidad = Integer.parseInt(String.valueOf(this.Carrito.getValueAt(rowC, 2)));
                            String Nombre = RS1.getString(2);
                            CantidadP = (RS1.getInt(4) - Cantidad);
                            CantidadV = (RS1.getInt(5) + Cantidad);
                            double ValorU = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 3)));
                            double ValorT = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 4)));

                            String Apodo = RS1.getString(2);
                            double ValorV = RS1.getDouble(6);
                            double ValorC = RS1.getDouble(7);
                            double ValorD = RS1.getDouble(8);
                            String ConsUpdate = "UPDATE TblProducts SET Nombre='" + Nombre + "', Apodo='" + Apodo + "',CantidadP='" + CantidadP + "',CantidadV='" + CantidadV + "',ValorV='" + ValorV + "',ValorC='" + ValorC + "',ValorD='" + ValorD
                                    + "' WHERE Codigo='" + Codigo + "'";
                            PreparedStatement PS2 = CN.prepareStatement(ConsUpdate);
                            PS2.executeUpdate();
                            String ConsInser = "INSERT INTO TblVentas(Factura,"
                                    + " NombreC,"
                                    + " Codigo,"
                                    + " NombreP,"
                                    + " CantidadP,"
                                    + " ValorU,"
                                    + "ValorT ) "
                                    + "VALUES ('" + fact + "','" + NombreC + "','" + Codigo + "','" + Nombre + "','" + Cantidad + "','" + ValorU + "','" + ValorT + "')";

                            PreparedStatement PS3 = CN.prepareStatement(ConsInser);
                            PS3.executeUpdate();
                        }
                    }

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "El CLIENTE " + NombreC + "  DEBE PAGAR UN TOTAL DE " + Total());

            Limpiar();
            ListarTablaP();
            CrearTablaCar();
            ltClientes.setSelectedIndex(0);
        }
    }

    public void anadirTablaCar() {

        int Cantidad = 0;

        if (!txtAnadirC.getText().equalsIgnoreCase("")) {

            Cantidad = Integer.parseInt(txtAnadirC.getText());
        }

        int row = -1;
        row = tbListProducts.getSelectedRow();

        String codigo = "";

        if (!String.valueOf(tbListProducts.getValueAt(row, 0)).equalsIgnoreCase(null)) {
            codigo = String.valueOf(tbListProducts.getValueAt(row, 0));
        }

        int inventario = 0;
        if (!String.valueOf(tbListProducts.getValueAt(row, 2)).equalsIgnoreCase(null)) {

            inventario = Integer.parseInt(String.valueOf(tbListProducts.getValueAt(row, 2)));

        }

        for (int rowc = 0; rowc < Carrito.getRowCount(); rowc++) {
            String Code = (String) Carrito.getValueAt(rowc, 0);
            if (codigo.equalsIgnoreCase(Code)) {
                inventario -= Integer.parseInt(String.valueOf(tbListCar.getValueAt(rowc, 2)));
            }

        }

        if (row == -1) {

            JOptionPane.showMessageDialog(null, "Debe escojer un producto", "¡Error!", JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad de producto valida", "¡Error!", JOptionPane.ERROR_MESSAGE);
            Limpiare();
            lblErrorAnadirC.setVisible(false);
            txtAnadirC.requestFocus();

        } else if (Cantidad > inventario) {
            JOptionPane.showMessageDialog(null, "Inventario insuficiente", "¡Error!", JOptionPane.ERROR_MESSAGE);
            lblErrorAnadirC.setVisible(false);
            txtAnadirC.requestFocus();

        } else {

            try {
                String Codigo = (String.valueOf(tbListProducts.getValueAt(row, 0)));
                for (int rowC = 0; rowC < Carrito.getRowCount(); rowC++) {
                    String Code = (String) Carrito.getValueAt(rowC, 0);

                    if (Code.equals(Codigo)) {

                        Cantidad += Integer.parseInt(String.valueOf(this.Carrito.getValueAt(rowC, 2)));
                        Carrito.removeRow(rowC);

                    }
                }

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo ='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();

                if (Cantidad <= 10) {
                    while (RS.next()) {
                        Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, RS.getString(6), (Cantidad * RS.getInt(6)),};
                        Carrito.addRow(Lista);

                    }
                } else {
                    // Recorer los resultados y cargalos a una lista
                    while (RS.next()) {
                        Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, RS.getString(8), (Cantidad * RS.getInt(8)),};
                        Carrito.addRow(Lista);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

        }
        Limpiar();

        Total();

    }

    public void EliminarTablaCar() {

        int row = -1;
        row = tbListCar.getSelectedRow();

        int Cantidad = 0;

        if (!txtElimi.getText().equalsIgnoreCase("")) {

            Cantidad = Integer.parseInt(txtElimi.getText());
        }

        if (row == -1) {

            JOptionPane.showMessageDialog(null, "Debe escojer un producto", "¡Error!", JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad de producto valida", "¡Error!", JOptionPane.ERROR_MESSAGE);
            lblErrorElimin.setVisible(false);
            txtElimi.requestFocus();

        } else {

            try {
                String Codigo = (String.valueOf(tbListCar.getValueAt(row, 0)));

                int Cantidadc = Integer.parseInt(String.valueOf(this.Carrito.getValueAt(row, 2)));
                if (Cantidad > Cantidadc) {
                    JOptionPane.showMessageDialog(null, "!!La cantidad de productos a eliminar debe ser menor a la cantidad que estan en el carrito!!");
                } else {
                    Cantidad = Cantidadc - Cantidad;
                    Carrito.removeRow(row);

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo ='" + Codigo + "'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();

                    // Recorer los resultados y cargalos a una lista
                    if (Cantidad <= 0) {
                        Carrito.removeRow(row);

                    } else if (Cantidad <= 10) {
                        while (RS.next()) {
                            Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, RS.getString(6), (Cantidad * RS.getInt(6)),};
                            Carrito.addRow(Lista);

                        }
                    } else {
                        // Recorer los resultados y cargalos a una lista
                        while (RS.next()) {
                            Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, RS.getString(8), (Cantidad * RS.getInt(8)),};
                            Carrito.addRow(Lista);
                        }
                    }
                }
            } catch (Exception e) {
                /*JOptionPane.showMessageDialog(rootPane,
                        "Error al listar los datos: " + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);*/
            }

        }
        Limpiar();
        Total();

    }

    public void ListarTablaP() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("VALOR DE VENTA");
        modelo.addColumn("VALOR CON DESCUENTO");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblProducts";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getInt(4), RS.getDouble(6), RS.getDouble(8),};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void BuscarProductoNombre() {
        String Nombre = txtNombre.getText();

        if (!Nombre.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                modelo.addColumn("CODIGO");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("CANTIDAD");
                modelo.addColumn("VALOR DE COMPRA");
                modelo.addColumn("VALOR DE VENTA");
                modelo.addColumn("VALOR CON DESCUENTO");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Nombre LIKE'%" + Nombre + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getInt(4), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe el Producto en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtNombre.setText("");
                    txtNombre.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTablaP();
        }
    }

    public void BuscarProductoCodigo() {
        String Codigo = txtCodigo.getText();

        if (!Codigo.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                modelo.addColumn("CODIGO");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("CANTIDAD");
                modelo.addColumn("VALOR DE COMPRA");
                modelo.addColumn("VALOR DE VENTA");
                modelo.addColumn("VALOR CON DESCUENTO");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo LIKE'%" + Codigo + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getInt(4), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe el Producto en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtNombre.setText("");
                    txtNombre.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTablaP();
        }
    }

    public void ComprobarDni() {
        String Dni = txtDni.getText();

        if (!Dni.equalsIgnoreCase("")) {
            try {
                String ConsBuscar = "SELECT * FROM TblClients WHERE Dni LIKE '%" + Dni + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    ltClientes.setSelectedItem(RS.getString(2) + " " + " " + RS.getString(3));
                } else {
                    CargarListaPersonas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos de la empresa: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void CargarListaPersonas() {

        try {
            String ConsBuscar = "SELECT * FROM Tblclients";

            PreparedStatement PS = CN.prepareStatement(ConsBuscar);
            ResultSet RS = PS.executeQuery();
            ltClientes.removeAllItems();
            ltClientes.addItem("SELECCIONE UN CLIENTE");

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {

                ltClientes.addItem(RS.getString(2) + " " + " " + RS.getString(3));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error al listar los datos del Cliente: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void ClickListaCar() {

        try {
            int row = tbListProducts.getSelectedRow();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();

    }

    public void ClickListaProd() {

        try {
            int row = tbListProducts.getSelectedRow();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();
    }

}
