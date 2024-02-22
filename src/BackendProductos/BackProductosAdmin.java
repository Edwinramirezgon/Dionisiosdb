package BackendProductos;

import Conexion.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontProductos.frmProductsAdmin.*;

public class BackProductosAdmin {

    ClsConexion CON;
    Connection CN;

    public BackProductosAdmin() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApodo.setText("");
        txtCantidadP.setText("");
        txtCantidadV.setText("");
        txtValorV.setText("");
        txtValorC.setText("");
        txtValorD.setText("");

        // Se ocultan todos los errores
        lblErrorCodigo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApodo.setVisible(false);
        lblErrorCantidadP.setVisible(false);
        lblErrorCantidadV.setVisible(false);
        lblErrorValorV.setVisible(false);
        lblErrorValorC.setVisible(false);
        lblErrorValorD.setVisible(false);
    }

    public void Limpiare() {

        // Se ocultan todos los errores
        lblErrorCodigo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApodo.setVisible(false);
        lblErrorCantidadP.setVisible(false);
        lblErrorCantidadV.setVisible(false);
        lblErrorValorV.setVisible(false);
        lblErrorValorC.setVisible(false);
        lblErrorValorD.setVisible(false);
    }

    public void ListarTabla() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apodo");
        modelo.addColumn("Cantidad De Productos");
        modelo.addColumn("Cantidad Vendida");
        modelo.addColumn("Valor De Compra");
        modelo.addColumn("Valor De Venta");
        modelo.addColumn("Valor Con Descuento");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblProducts";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void ModificarProducto() {
        String Codigo = txtCodigo.getText();
        String Nombre = txtNombre.getText();
        String Apodo = txtApodo.getText();

        int CantidadP = 0;
        if (!txtCantidadP.getText().equalsIgnoreCase("")) {
            CantidadP = Integer.parseInt(txtCantidadP.getText());
        }

        int CantidadV = 0;
        if (!txtCantidadV.getText().equalsIgnoreCase("")) {
            CantidadV = Integer.parseInt(txtCantidadV.getText());
        }

        double ValorD = 0;
        if (!txtValorD.getText().equalsIgnoreCase("")) {
            ValorD = Double.parseDouble(txtValorD.getText());
        }

        double ValorC = 0;
        if (!txtValorC.getText().equalsIgnoreCase("")) {
            ValorC = Double.parseDouble(txtValorC.getText());
        }

        double ValorV = 0;
        if (!txtValorV.getText().equalsIgnoreCase("")) {
            ValorV = Double.parseDouble(txtValorV.getText());
        }

        if (Codigo.equals("")) {
            Limpiare();
            lblErrorCodigo.setVisible(true);
            txtCodigo.requestFocus();
        } else if (Nombre.equals("")) {
            Limpiare();
            lblErrorNombre.setVisible(true);
            txtNombre.requestFocus();
        } else if (Apodo.equals("")) {
            Limpiare();
            lblErrorApodo.setVisible(true);
            txtApodo.requestFocus();

        } else if (ValorV == 0) {
            Limpiare();
            lblErrorValorV.setVisible(true);
            txtValorV.requestFocus();
        } else if (ValorD == 0) {
            Limpiare();
            lblErrorValorD.setVisible(true);
            txtValorD.requestFocus();
        } else {

            try {
                String ValCodigo = "SELECT * FROM TblProducts WHERE Codigo='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ValCodigo);
                ResultSet RS = PS.executeQuery();
                if (!RS.next()) {
                    Limpiare();
                    JOptionPane.showMessageDialog(null, "¡Error! el Producto NO existe en la BD");
                } else {
                    String ConsUpdate = "UPDATE TblProducts SET Nombre='" + Nombre + "', Apodo='" + Apodo + "',CantidadP='" + CantidadP + "',CantidadV='" + CantidadV + "',ValorV='" + ValorV + "',ValorC='" + ValorC + "',ValorD='" + ValorD
                            + "' WHERE Codigo='" + Codigo + "'";
                    PreparedStatement PS1 = CN.prepareStatement(ConsUpdate);
                    PS1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
                    Limpiar();
                    txtCodigo.requestFocus();
                    ListarTabla();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el registro: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void EliminarProducto() {
        String Codigo = txtCodigo.getText();

        if (!Codigo.equalsIgnoreCase("")) {
            try {
                String ConsBuscar = "SELECT * FROM tblProducts WHERE Codigo='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    String ConsEliminar = "DELETE FROM tblProducts WHERE Codigo='" + Codigo + "'";
                    PreparedStatement PS1 = CN.prepareStatement(ConsEliminar);
                    PS1.executeUpdate();
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
                    ListarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "¡¡No existe el Producto en la base de datos!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtCodigo.setText("");
                    txtCodigo.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo del Producto poder eliminarlo", "¡Error!", JOptionPane.ERROR_MESSAGE);
            lblErrorCodigo.setVisible(false);
            txtCodigo.requestFocus();
        }
    }

    public void RegistrarProducto() {
        String Codigo = txtCodigo.getText();
        String Nombre = txtNombre.getText();
        String Apodo = txtApodo.getText();

        int CantidadP = 0;
        if (!txtCantidadP.getText().equalsIgnoreCase("")) {
            CantidadP = Integer.parseInt(txtCantidadP.getText());
        }

        int CantidadV = 0;
        if (!txtCantidadV.getText().equalsIgnoreCase("")) {
            CantidadV = Integer.parseInt(txtCantidadV.getText());
        }

        double ValorD = 0;
        if (!txtValorD.getText().equalsIgnoreCase("")) {
            ValorD = Double.parseDouble(txtValorD.getText());
        }

        double ValorC = 0;
        if (!txtValorC.getText().equalsIgnoreCase("")) {
            ValorC = Double.parseDouble(txtValorC.getText());
        }

        double ValorV = 0;
        if (!txtValorV.getText().equalsIgnoreCase("")) {
            ValorV = Double.parseDouble(txtValorV.getText());
        }

        if (Codigo.equals("")) {
            Limpiare();
            lblErrorCodigo.setVisible(true);
            txtCodigo.requestFocus();
        } else if (Nombre.equals("")) {
            Limpiare();

            lblErrorNombre.setVisible(true);
            txtNombre.requestFocus();
        } else if (Apodo.equals("")) {
            Limpiare();

            lblErrorApodo.setVisible(true);
            txtApodo.requestFocus();

        } else if (ValorV == 0) {
            Limpiare();

            lblErrorValorV.setVisible(true);
            txtValorV.requestFocus();
        } else if (ValorD == 0) {
            Limpiare();

            lblErrorValorD.setVisible(true);
            txtValorD.requestFocus();
        } else {
            try {
                String ValCodigo = "SELECT * FROM TblProducts WHERE Codigo='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ValCodigo);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    JOptionPane.showMessageDialog(null, "¡Error! el Producto ya existe en la BD");
                } else {
                    String ConsInser = "INSERT INTO tblProducts(Codigo,"
                            + " Nombre,"
                            + " Apodo,"
                            + " CantidadP,"
                            + " CantidadV,"
                            + " ValorV,"
                            + " ValorC,ValorD ) "
                            + "VALUES ('" + Codigo + "','" + Nombre + "','" + Apodo + "','" + CantidadP + "','" + CantidadV + "','" + ValorV + "','" + ValorC + "','" + ValorD + "')";
                    PreparedStatement PS1 = CN.prepareStatement(ConsInser);
                    PS1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
                    ListarTabla();
                    Limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el registro:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
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
                modelo.addColumn("Codigo");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apodo");
                modelo.addColumn("Cantidad De Productos");
                modelo.addColumn("Cantidad Vendida");
                modelo.addColumn("Valor De Compra");
                modelo.addColumn("Valor De Venta");
                modelo.addColumn("Valor Con Descuento");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo ='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTabla();
        }

    }

    public void BuscarProductoApodo() {
        String Apodo = txtApodo.getText();

        if (txtCodigo.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("Codigo");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apodo");
                modelo.addColumn("Cantidad De Productos");
                modelo.addColumn("Cantidad Vendida");
                modelo.addColumn("Valor De Compra");
                modelo.addColumn("Valor De Venta");
                modelo.addColumn("Valor Con Descuento");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Apodo LIKE '%" + Apodo + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTabla();
        }
    }

    public void BuscarProductoNombre() {
        String Nombre = txtNombre.getText();

        if (txtCodigo.getText().equalsIgnoreCase("")) {

            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("Codigo");
                modelo.addColumn("Nombre");
                modelo.addColumn("Apodo");
                modelo.addColumn("Cantidad De Productos");
                modelo.addColumn("Cantidad Vendida");
                modelo.addColumn("Valor De Compra");
                modelo.addColumn("Valor De Venta");
                modelo.addColumn("Valor Con Descuento");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Nombre LIKE '%" + Nombre + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ListarTabla();
        }

    }

    public void BuscarProductoCantidad() {

        if (!txtCantidadP.getText().equalsIgnoreCase("")) {

            int CantidadP = Integer.parseInt(txtCantidadP.getText());

            if (txtCodigo.getText().equalsIgnoreCase("")) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel() {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Apodo");
                    modelo.addColumn("Cantidad De Productos");
                    modelo.addColumn("Cantidad Vendida");
                    modelo.addColumn("Valor De Compra");
                    modelo.addColumn("Valor De Venta");
                    modelo.addColumn("Valor Con Descuento");

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE CantidadP LIKE '%" + CantidadP + "%'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();
                    if (RS.next()) {
                        do {
                            Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                            modelo.addRow(Lista);
                        } while (RS.next());
                        tbListProducts.setModel(modelo);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
                }
            }else {
            ListarTabla();
        }
        } else {
            ListarTabla();
        } 
    }

    public void ClickTablaProductos() {

        try {
            int row = tbListProducts.getSelectedRow();
            txtCodigo.setText(String.valueOf(tbListProducts.getValueAt(row, 0)));
            txtNombre.setText(String.valueOf(tbListProducts.getValueAt(row, 1)));
            txtApodo.setText(String.valueOf(tbListProducts.getValueAt(row, 2)));
            txtCantidadP.setText(String.valueOf(tbListProducts.getValueAt(row, 3)));
            txtCantidadV.setText(String.valueOf(tbListProducts.getValueAt(row, 4)));
            txtValorC.setText(String.valueOf(tbListProducts.getValueAt(row, 5)));
            txtValorV.setText(String.valueOf(tbListProducts.getValueAt(row, 6)));
            txtValorD.setText(String.valueOf(tbListProducts.getValueAt(row, 7)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error en la consulta:" + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void BuscarProductoCantidadVendida() {
        if (!txtCantidadV.getText().equalsIgnoreCase("")) {

            int CantidadV = Integer.parseInt(txtCantidadV.getText());

            if (txtCodigo.getText().equalsIgnoreCase("")) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel() {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Apodo");
                    modelo.addColumn("Cantidad De Productos");
                    modelo.addColumn("Cantidad Vendida");
                    modelo.addColumn("Valor De Compra");
                    modelo.addColumn("Valor De Venta");
                    modelo.addColumn("Valor Con Descuento");

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE CantidadV LIKE '%" + CantidadV + "%'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();
                    if (RS.next()) {
                        do {
                            Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                            modelo.addRow(Lista);
                        } while (RS.next());
                        tbListProducts.setModel(modelo);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
                }
            }else {
            ListarTabla();
        }

        } else {
            ListarTabla();
        } 
    }

    public void BuscarProductoValorCompra() {

        if (!txtValorC.getText().equalsIgnoreCase("")) {

            int ValorC = Integer.parseInt(txtValorC.getText());

            if (txtCodigo.getText().equalsIgnoreCase("")) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel() {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Apodo");
                    modelo.addColumn("Cantidad De Productos");
                    modelo.addColumn("Cantidad Vendida");
                    modelo.addColumn("Valor De Compra");
                    modelo.addColumn("Valor De Venta");
                    modelo.addColumn("Valor Con Descuento");

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE ValorC LIKE '%" + ValorC + "%'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();
                    if (RS.next()) {
                        do {
                            Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                            modelo.addRow(Lista);
                        } while (RS.next());
                        tbListProducts.setModel(modelo);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Error en la consulta:" + e.getMessage(),
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }else {
            ListarTabla();
        }
        } else {
            ListarTabla();
        } 

    }

    public void BuscarProductoValorVenta() {

        if (!txtValorV.getText().equalsIgnoreCase("")) {

            int ValorV = Integer.parseInt(txtValorV.getText());

            if (txtCodigo.getText().equalsIgnoreCase("")) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel() {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Apodo");
                    modelo.addColumn("Cantidad De Productos");
                    modelo.addColumn("Cantidad Vendida");
                    modelo.addColumn("Valor De Compra");
                    modelo.addColumn("Valor De Venta");
                    modelo.addColumn("Valor Con Descuento");

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE ValorV LIKE '%" + ValorV + "%'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();
                    if (RS.next()) {
                        do {
                            Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                            modelo.addRow(Lista);
                        } while (RS.next());
                        tbListProducts.setModel(modelo);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
                }
            }else {
            ListarTabla();
        }

        } else {
            ListarTabla();
        } 
    }

    public void BuscarProductoValoDescuento() {
        if (!txtValorD.getText().equalsIgnoreCase("")) {

            int ValorD = Integer.parseInt(txtValorD.getText());

            if (txtCodigo.getText().equalsIgnoreCase("")) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel() {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Apodo");
                    modelo.addColumn("Cantidad De Productos");
                    modelo.addColumn("Cantidad Vendida");
                    modelo.addColumn("Valor De Compra");
                    modelo.addColumn("Valor De Venta");
                    modelo.addColumn("Valor Con Descuento");

                    String ConsBuscar = "SELECT * FROM TblProducts WHERE ValorD LIKE '%" + ValorD + "%'";
                    PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                    ResultSet RS = PS.executeQuery();
                    if (RS.next()) {
                        do {
                            Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getInt(4), RS.getInt(5), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                            modelo.addRow(Lista);
                        } while (RS.next());
                        tbListProducts.setModel(modelo);

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
                }
            }else {
            ListarTabla();
        } 
        }else {
            ListarTabla();
        } 
    }
}
