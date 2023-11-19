package BackendCliente;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static FrontClientes.frmClientsCAjero.*;

public class BackClienteCajero {

    ClsConexion CON;
    Connection CN;

    public BackClienteCajero() {

        CON = new ClsConexion();
        CN = CON.getConnection();

    }

    public void Limpiar() {
        // Se limpian todos los campos
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtFecha.setText("");

        // Se ocultan todos los errores
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApellido.setVisible(false);
        lblErrorCorreo.setVisible(false);
        lblErrorDireccion.setVisible(false);
        lblErrorTelefono.setVisible(false);
        lblErrorFecha.setVisible(false);
    }

    public void Limpiare() {

        // Se ocultan los errores de los Labels
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApellido.setVisible(false);
        lblErrorCorreo.setVisible(false);
        lblErrorDireccion.setVisible(false);
        lblErrorTelefono.setVisible(false);
        lblErrorFecha.setVisible(false);
    }

    public void ListarTabla() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("CORREO ELECTRONICO    ");
        modelo.addColumn("DIRECCION      ");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("FECHA DE NACIMIENTO");

        // Cargado de datos a la tabla
        try {
            // Comunicación con la base de datos 
            String ConsLista = "SELECT * FROM tblClients";
            PreparedStatement PS = CN.prepareStatement(ConsLista);
            ResultSet RS = PS.executeQuery();

            // Recorer los resultados y cargalos a una lista
            while (RS.next()) {
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                modelo.addRow(Lista);
            }

            tbListClients.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar los datos: " + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void RegistarCliente() {
        String Dni = txtDni.getText();
        String Nombre = txtNombre.getText();
        String Apellido = txtApellido.getText();
        String Correo = txtCorreo.getText();
        String Direccion = txtDireccion.getText();
        String Telefono = txtTelefono.getText();
        String Fecha = txtFecha.getText();

        if (Dni.equals("")) {
            Limpiare();
            lblErrorDni.setVisible(true);
            txtDni.requestFocus();
        } else if (Nombre.equals("")) {
            Limpiare();

            lblErrorNombre.setVisible(true);
            txtNombre.requestFocus();
        } else if (Apellido.equals("")) {
            Limpiare();

            lblErrorApellido.setVisible(true);
            txtApellido.requestFocus();
        } else if (Correo.equals("")) {
            Limpiare();

            lblErrorCorreo.setVisible(true);
            txtCorreo.requestFocus();
        } else if (Direccion.equals("")) {
            Limpiare();

            lblErrorDireccion.setVisible(true);
            txtDireccion.requestFocus();
        } else if (Fecha.equals("")) {
            Limpiare();

            lblErrorFecha.setVisible(true);
            txtFecha.requestFocus();
        } else if (Telefono.equals("")) {
            Limpiare();

            lblErrorTelefono.setVisible(true);
            txtTelefono.requestFocus();
        } else {
            try {
                String ValDni = "SELECT * FROM tblClients WHERE Dni='" + Dni + "'";
                PreparedStatement PS = CN.prepareStatement(ValDni);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    JOptionPane.showMessageDialog(null, "¡Error! el Cliente ya existe en la BD");
                } else {
                    String ConsInser = "INSERT INTO tblClients(Dni,"
                            + " Nombre,"
                            + " Apellido,"
                            + " Correo,"
                            + " Direccion,"
                            + " Telefono,Fecha ) "
                            + "VALUES ('" + Dni + "','" + Nombre + "','" + Apellido + "','" + Correo + "','" + Direccion + "','" + Telefono + "','" + Fecha + "')";
                    PreparedStatement PS1 = CN.prepareStatement(ConsInser);
                    PS1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito");
                    ListarTabla();
                    Limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el registro:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
     public void BuscarClienteDni() {
        String Dni = txtDni.getText();

        if (!Dni.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Dni ='" + Dni + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);
              
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    

    public void BuscarClienteNombre() {
        String Nombre = txtNombre.getText();

       if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Nombre LIKE'%" + Nombre + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 

    }

    public void BuscarClienteApellidos() {
        String Apellido = txtApellido.getText();

     if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Apellido LIKE'%" + Apellido + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 

    }

    public void BuscarClienteCorreo() {
        String Correo = txtCorreo.getText();

          if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Correo LIKE'%" + Correo + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 

    }

    public void BuscarClienteDireccion() {
        String Direccion = txtDireccion.getText();

          if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Direccion LIKE'%" + Direccion + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 

    }

    public void BuscarClienteFecha() {
        
        String Fecha = txtFecha.getText();

             if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Fecha LIKE'%" + Fecha + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,  "Error en la consulta:" + e.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        

    }
    
       public void BuscarClienteTelefono() {
           
            String Telefono = txtTelefono.getText();

            if (txtDni.getText().equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("DNI");
                modelo.addColumn("NOMBRE");
                modelo.addColumn("APELLIDO");
                modelo.addColumn("CORREO ELECTRONICO    ");
                modelo.addColumn("DIRECCION      ");
                modelo.addColumn("TELEFONO");
                modelo.addColumn("FECHA DE NACIMIENTO");

                String ConsBuscar = "SELECT * FROM TblClients WHERE Telefono LIKE'%" + Telefono + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListClients.setModel(modelo);

                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la consulta:" + e.getMessage(),"¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } 

    }
       
        public void ClickListaClientes() {
           
     int row = tbListClients.getSelectedRow();
            txtDni.setText(String.valueOf(tbListClients.getValueAt(row, 0)));
            txtNombre.setText(String.valueOf(tbListClients.getValueAt(row, 1)));
            txtApellido.setText(String.valueOf(tbListClients.getValueAt(row, 2)));
            txtCorreo.setText(String.valueOf(tbListClients.getValueAt(row, 3)));
            txtDireccion.setText(String.valueOf(tbListClients.getValueAt(row, 4)));
            txtFecha.setText(String.valueOf(tbListClients.getValueAt(row, 6)));
            txtTelefono.setText(String.valueOf(tbListClients.getValueAt(row, 5))); 


}


}
