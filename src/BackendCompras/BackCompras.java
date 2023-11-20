package BackendCompras;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontCompras.frmCompras.*;

public class BackCompras {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public BackCompras() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        txtNit.setText("");
        txtAnadirC.setText("");
        txtElimi.setText("");
        txtNit.setText("");
        txtValorC.setText("");

        // Se ocultan todos los errores
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);
        lblErrorValor.setVisible(false);

    }

    public void Limpiare() {

        // Se ocultan todos los errores
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);
        lblErrorValor.setVisible(false);

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

        String Nit = txtNit.getText();
        String Empresa = (String) ltClientes.getSelectedItem();
        String NombreE = "";
        int Login = 0;

        if (Empresa.equals("SELECCIONE UNA EMPRESA")) {
            Limpiare();
            txtNit.requestFocus();
            JOptionPane.showMessageDialog(null, "Debe escojer una empresa");

        } else {
            try {

                String ConsBuscar = "SELECT * FROM TblProv WHERE Empresa = '" + Empresa + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();

                if (RS.next()) {
                    Nit = RS.getString(1);

                    String ConsLogin = "SELECT MAX(Login) AS id FROM TblLogin";
                    PreparedStatement PS6 = CN.prepareStatement(ConsLogin);
                    ResultSet RS6 = PS6.executeQuery();
                    while (RS6.next()) {
                        Login = RS6.getInt(1);

                    }
                    String ConsUser = "SELECT * FROM TblLogin WHERE Login = '" + Login + "'";
                    PreparedStatement PS7 = CN.prepareStatement(ConsUser);
                    ResultSet RS7 = PS7.executeQuery();
                    while (RS7.next()) {
                        NombreE = RS7.getString(2);

                    }

                    String ConsInsert2 = "INSERT INTO TblFactC(Nit,"
                            + " Empresa,"
                            + " Nombre,"
                            + " Total ) "
                            + "VALUES ('" + Nit + "','" + Empresa + "','" + NombreE + "','" + Total() + "')";

                    PreparedStatement PS4 = CN.prepareStatement(ConsInsert2);
                    PS4.executeUpdate();

                    String ConsFact = "SELECT MAX(Fact) AS id FROM TblfactC";
                    PreparedStatement PS5 = CN.prepareStatement(ConsFact);
                    ResultSet RS5 = PS5.executeQuery();
                    int Fact = 0;
                    while (RS5.next()) {
                        Fact = RS5.getInt(1);
                    }

                    try {

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
                                    CantidadP = (RS1.getInt(4) + Cantidad);
                                    CantidadV = RS1.getInt(5);
                                    double ValorU = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 3)));
                                    double ValorT = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 4)));
                                    String Apodo = RS1.getString(2);
                                    double ValorV = RS1.getDouble(6);
                                    double ValorC = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 3)));
                                    double ValorD = RS1.getDouble(8);
                                    String ConsUpdate = "UPDATE TblProducts SET Nombre='" + Nombre + "', Apodo='" + Apodo + "',CantidadP='" + CantidadP + "',CantidadV='" + CantidadV + "',ValorV='" + ValorV + "',ValorC='" + ValorC + "',ValorD='" + ValorD
                                            + "' WHERE Codigo='" + Codigo + "'";
                                    PreparedStatement PS2 = CN.prepareStatement(ConsUpdate);
                                    PS2.executeUpdate();
                                    String ConsInser = "INSERT INTO TblCompras(Factura,"
                                            + " Nit,"
                                            + " Empresa,"
                                            + " Codigo,"
                                            + " NombreP,"
                                            + " Cantidad,"
                                            + " ValorU,"
                                            + "ValorT ) "
                                            + "VALUES ('" + Fact + "','" + Nit + "','" + Empresa + "','" + Codigo + "','" + Nombre + "','" + Cantidad + "','" + ValorU + "','" + ValorT + "')";

                                    PreparedStatement PS3 = CN.prepareStatement(ConsInser);
                                    PS3.executeUpdate();

                                }
                            }

                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(null, "SE LE DEBE PAGAR A LA EMPRESA " + Empresa + " CON NIT " + Nit + " UN TOTAL DE " + Total());

                    Limpiar();
                    ListarTablaP();
                    CrearTablaCar();
                    ltClientes.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "¡¡Error el la compra!!", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    txtNit.setText("");
                    txtNit.requestFocus();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void anadirTablaCar() {
        
        int Cantidad=0;
        if (!txtAnadirC.getText().equalsIgnoreCase("")) {
            Cantidad = Integer.parseInt(txtAnadirC.getText());
        }        
        
        
        double ValorC = 0;
        if (!txtValorC.getText().equalsIgnoreCase("")) {
            
           ValorC = Double.parseDouble(txtValorC.getText());
        }    
        
        int row = -1;
        row=tbListProducts.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(null, "Debe escojer un producto", "¡Error!", JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();}
        else{
         if (ValorC <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor de compra valido", "¡Error!", JOptionPane.ERROR_MESSAGE);
            Limpiare();
            lblErrorValor.setVisible(true);
            txtValorC.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad de producto valida", "¡Error!", JOptionPane.ERROR_MESSAGE);
            Limpiare();
            lblErrorAnadirC.setVisible(true);
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

                while (RS.next()) {
                    Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, ValorC, (Cantidad * ValorC),};
                    Carrito.addRow(Lista);
                    Limpiar();
                    ListarTablaP();

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

        }

        Total();
        }
    }

    public void EliminarTablaCar() {

        int row = -1;
        row= tbListCar.getSelectedRow();
        
        int Cantidad = 0;
        
        if (!txtElimi.getText().equalsIgnoreCase("")) {
           Cantidad =Integer.parseInt(txtElimi.getText());
        }
        


        if (row == -1) {

            JOptionPane.showMessageDialog(null, "Debe escojer un producto", "¡Error!", JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad de producto valida", "¡Error!", JOptionPane.ERROR_MESSAGE);
            lblErrorElimin.setVisible(true);
            txtElimi.requestFocus();

        } else {

            try {
                String Codigo = (String.valueOf(tbListCar.getValueAt(row, 0)));
                        double ValorC = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(row, 3)));


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
                    if (Cantidad == 0) {
                        Carrito.removeRow(row);
                        Limpiar();

                    } else {

                        while (RS.next()) {
                            Object[] Lista = {RS.getString(1), RS.getString(2), Cantidad, ValorC, (Cantidad * ValorC),};
                            Carrito.addRow(Lista);
                            Limpiar();

                        }
                    }
                }

            } catch (Exception e) {
                /*JOptionPane.showMessageDialog(null,"Error al listar los datos: " + e.getMessage(),"¡Error!",JOptionPane.ERROR_MESSAGE);*/
            }

        }

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
        modelo.addColumn("VALOR DE COMPRA");
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
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getInt(4), RS.getDouble(7), RS.getDouble(6), RS.getDouble(8)};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
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

    public void ComprobarNit() {

        String Nit = txtNit.getText();
        if (!Nit.equalsIgnoreCase("")) {
            try {
                String ConsBuscar = "SELECT * FROM TblProv WHERE Nit LIKE '%" + Nit + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {

                    ltClientes.setSelectedItem(RS.getString(2));

                } else {
                    CargarListaEmpresas();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al listar los datos de la empresa: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void CargarListaEmpresas() {

        try {
            String ConsBuscar = "SELECT * FROM TblProv";

            PreparedStatement PS = CN.prepareStatement(ConsBuscar);
            ResultSet RS = PS.executeQuery();
            ltClientes.removeAllItems();
            ltClientes.addItem("SELECCIONE UNA EMPRESA");

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                ltClientes.addItem(RS.getString(2));
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
