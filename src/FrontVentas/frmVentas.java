package FrontVentas;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmVentas extends javax.swing.JFrame {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public frmVentas() {
        initComponents();
        lblErrorNombre.setVisible(false);
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);
        lblErrorDni.setVisible(false);
        this.setLocationRelativeTo(this);
        CON = new ClsConexion();
        CN = CON.getConnection();
        ListarTablaP();
        CrearTablaCar();
        Limpiar();
    }

    private void Limpiar() {
        // Se limpian todos los campos
        txtDni.setText("");
        txtAnadirC.setText("0");
        txtElimi.setText("0");
        txtDni.setText("");

        // Se ocultan todos los errores
        lblErrorNombre.setVisible(false);
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);
        lblErrorDni.setVisible(false);

    }

    private void Limpiare() {

        // Se ocultan todos los errores
        lblErrorNombre.setVisible(false);
        lblErrorAnadirC.setVisible(false);
        lblErrorElimin.setVisible(false);
        lblErrorDni.setVisible(false);

    }

    private void CrearTablaCar() {

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

    private double Total() {

        float total = 0;

        try {
            for (int row = 0; row < Carrito.getRowCount(); row++) {

                total += Double.parseDouble(String.valueOf(this.Carrito.getValueAt(row, 4)));

            }

            txtTotal.setText(total + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error al listar los datos: " + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        return total;

    }

    private void Pagar() {

        String Dni = txtDni.getText();
        String NombreC = "";
        String Apellido = "";

        if (Dni.equals("")) {
            Limpiare();
            lblErrorDni.setVisible(true);
            txtDni.requestFocus();
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar el Dni del cliente");

        } else {
            try {

                String ConsBuscar = "SELECT * FROM TblClients WHERE Dni ='" + Dni + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();

                if (RS.next()) {

                    NombreC = RS.getString(2);
                    Apellido = RS.getString(3);

                    String ConsInsert2 = "INSERT INTO TblFactV(Dni,"
                            + " Nombre,"
                            + " Apellido,"
                            + " Total ) "
                            + "VALUES ('" + Dni + "','" + NombreC + "','" + Apellido + "','" + Total() + "')";

                    PreparedStatement PS4 = CN.prepareStatement(ConsInsert2);
                    PS4.executeUpdate();

                    String ConsFact = "SELECT MAX(Fact) AS id FROM TblfactV";
                    PreparedStatement PS5 = CN.prepareStatement(ConsFact);
                    ResultSet RS5 = PS5.executeQuery();
                    String fact = "";
                    while (RS5.next()) {
                        fact = RS5.getString(1);
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
                                    CantidadP = (Integer.parseInt(RS1.getString(4)) - Cantidad);
                                    CantidadV = (Integer.parseInt(RS1.getString(5)) + Cantidad);
                                    double ValorU = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 3)));
                                    double ValorT = Double.parseDouble(String.valueOf(this.Carrito.getValueAt(rowC, 4)));

                                    String Apodo = RS1.getString(2);
                                    double ValorV = (Double.parseDouble(RS1.getString(6)));
                                    double ValorC = (Double.parseDouble(RS1.getString(7)));
                                    double ValorD = (Double.parseDouble(RS1.getString(8)));
                                    String ConsUpdate = "UPDATE TblProducts SET Nombre='" + Nombre + "', Apodo='" + Apodo + "',CantidadP='" + CantidadP + "',CantidadV='" + CantidadV + "',ValorV='" + ValorV + "',ValorC='" + ValorC + "',ValorD='" + ValorD
                                            + "' WHERE Codigo='" + Codigo + "'";
                                    PreparedStatement PS2 = CN.prepareStatement(ConsUpdate);
                                    PS2.executeUpdate();
                                    String ConsInser = "INSERT INTO TblVentas(Factura,"
                                            + " Dni,"
                                            + " NombreC,"
                                            + " ApellidoC,"
                                            + " Codigo,"
                                            + " NombreP,"
                                            + " CantidadP,"
                                            + " ValorU,"
                                            + "ValorT ) "
                                            + "VALUES ('" + fact + "','" + Dni + "','" + NombreC + "','" + Apellido + "','" + Codigo + "','" + Nombre + "','" + Cantidad + "','" + ValorU + "','" + ValorT + "')";

                                    PreparedStatement PS3 = CN.prepareStatement(ConsInser);
                                    PS3.executeUpdate();

                                }
                            }

                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane,
                                "Error al listar los datos: " + e.getMessage(),
                                "¡Error!",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(rootPane, "El cliente con Dni " + Dni + " de nombre " + NombreC + " " + Apellido + " Debe pagar un total de " + Total());

                    Limpiar();
                    ListarTablaP();
                    CrearTablaCar();
                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el CLiente en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    txtDni.setText("");
                    txtDni.requestFocus();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error al listar los datos: " + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void anadirTablaCar() {

        int Cantidad = Integer.parseInt(txtAnadirC.getText());
        int row = this.tbListProducts.getSelectedRow();
        int inventario = Integer.parseInt(String.valueOf(this.tbListProducts.getValueAt(row, 2)));

        if (row == -1) {

            JOptionPane.showMessageDialog(rootPane,
                    "Debe escojer un producto",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(rootPane,
                    "Debe ingresar una cantidad de producto valida",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            Limpiare();
            lblErrorAnadirC.setVisible(false);
            txtAnadirC.requestFocus();

        } else if (Cantidad > inventario) {
            JOptionPane.showMessageDialog(rootPane,
                    "Inventario insuficiente",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            lblErrorAnadirC.setVisible(false);
            txtAnadirC.requestFocus();

        } else {

            try {
                String Codigo = (String.valueOf(this.tbListProducts.getValueAt(row, 0)));
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
                JOptionPane.showMessageDialog(rootPane,
                        "Error al listar los datos: " + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        Limpiar();

        Total();

    }

    private void EliminarTablaCar() {

        int row = this.tbListCar.getSelectedRow();
        int Cantidad = Integer.parseInt(txtElimi.getText());

        if (row == -1) {

            JOptionPane.showMessageDialog(rootPane,
                    "Debe escojer un producto",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            tbListProducts.requestFocus();

        } else if (Cantidad <= 0) {
            JOptionPane.showMessageDialog(rootPane,
                    "Debe ingresar una cantidad de producto valida",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            lblErrorElimin.setVisible(false);
            txtElimi.requestFocus();

        } else {

            try {
                String Codigo = (String.valueOf(this.tbListCar.getValueAt(row, 0)));

                Cantidad = Integer.parseInt(String.valueOf(this.Carrito.getValueAt(row, 2))) - Cantidad;
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

    private void ListarTablaP() {

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
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(4), RS.getString(6), RS.getString(8),};
                modelo.addRow(Lista);
            }
            tbListProducts.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error al listar los datos: " + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListCar = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListProducts = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblErrorNombre = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jButtonSearchNom = new javax.swing.JButton();
        lblErrorAnadirC = new javax.swing.JLabel();
        jButtonAnadirC = new javax.swing.JButton();
        txtAnadirC = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtElimi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonEliminarC = new javax.swing.JButton();
        lblErrorElimin = new javax.swing.JLabel();
        btnPagar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jButtonDni = new javax.swing.JButton();
        lblErrorDni = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButtonFactura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GESTION DE VENTAS");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 361));
        jPanel1.setLayout(null);

        tbListCar.setAutoCreateRowSorter(true);
        tbListCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbListCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListCarTblProductsMouseClick(evt);
            }
        });
        jScrollPane2.setViewportView(tbListCar);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 510, 913, 330);

        tbListProducts.setAutoCreateRowSorter(true);
        tbListProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbListProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblProductsMouseClick(evt);
            }
        });
        jScrollPane1.setViewportView(tbListProducts);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 44, 928, 410);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel5.setText("CARRITO");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(390, 470, 109, 30);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE PRODUCTOS");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(307, 6, 272, 30);

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);
        jPanel1.add(lblErrorNombre);
        lblErrorNombre.setBounds(946, 91, 146, 13);

        txtDni.setBackground(new java.awt.Color(0, 153, 255));
        txtDni.setForeground(new java.awt.Color(51, 0, 51));
        txtDni.setToolTipText("");
        txtDni.setAlignmentY(10.0F);
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        jPanel1.add(txtDni);
        txtDni.setBounds(944, 625, 160, 40);

        jButtonSearchNom.setBackground(new java.awt.Color(0, 0, 255));
        jButtonSearchNom.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonSearchNom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchNom.setText(">");
        jButtonSearchNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchNomActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSearchNom);
        jButtonSearchNom.setBounds(1115, 44, 60, 40);

        lblErrorAnadirC.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorAnadirC.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorAnadirC.setText("* Error, ingrese Cantidad válida");
        lblErrorAnadirC.setAlignmentY(10.0F);
        jPanel1.add(lblErrorAnadirC);
        lblErrorAnadirC.setBounds(946, 180, 152, 13);

        jButtonAnadirC.setBackground(new java.awt.Color(0, 0, 255));
        jButtonAnadirC.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonAnadirC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAnadirC.setText(">");
        jButtonAnadirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirCActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAnadirC);
        jButtonAnadirC.setBounds(1110, 134, 60, 40);

        txtAnadirC.setBackground(new java.awt.Color(0, 153, 255));
        txtAnadirC.setForeground(new java.awt.Color(51, 0, 51));
        txtAnadirC.setToolTipText("");
        txtAnadirC.setAlignmentY(10.0F);
        txtAnadirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnadirCActionPerformed(evt);
            }
        });
        txtAnadirC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnadirCKeyTyped(evt);
            }
        });
        jPanel1.add(txtAnadirC);
        txtAnadirC.setBounds(946, 139, 158, 35);

        btnActualizar.setBackground(new java.awt.Color(0, 0, 255));
        btnActualizar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR LISTA");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);
        btnActualizar.setBounds(940, 210, 230, 40);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR POR NOMBRE");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(979, 20, 160, 18);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("ANADIR CANTIDAD AL CARRITO");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(946, 110, 221, 18);

        txtElimi.setBackground(new java.awt.Color(0, 153, 255));
        txtElimi.setForeground(new java.awt.Color(51, 0, 51));
        txtElimi.setToolTipText("");
        txtElimi.setAlignmentY(10.0F);
        txtElimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtElimiActionPerformed(evt);
            }
        });
        txtElimi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtElimiKeyTyped(evt);
            }
        });
        jPanel1.add(txtElimi);
        txtElimi.setBounds(946, 526, 152, 34);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("DNI DEL CLIENTE");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(963, 595, 160, 23);

        jButtonEliminarC.setBackground(new java.awt.Color(0, 0, 255));
        jButtonEliminarC.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonEliminarC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarC.setText(">");
        jButtonEliminarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminarC);
        jButtonEliminarC.setBounds(1104, 524, 71, 40);

        lblErrorElimin.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorElimin.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorElimin.setText("* Error, ingrese Cantidad válida");
        lblErrorElimin.setAlignmentY(10.0F);
        jPanel1.add(lblErrorElimin);
        lblErrorElimin.setBounds(946, 570, 152, 13);

        btnPagar.setBackground(new java.awt.Color(0, 0, 255));
        btnPagar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        jPanel1.add(btnPagar);
        btnPagar.setBounds(1100, 715, 80, 50);

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("ELIMINAR CANTIDAD DEL CARRITO");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(940, 500, 244, 18);

        btnCancelar1.setBackground(new java.awt.Color(0, 0, 255));
        btnCancelar1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar1);
        btnCancelar1.setBounds(940, 270, 230, 40);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setText("VALOR TOTAL");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(964, 685, 130, 23);

        txtNombre.setBackground(new java.awt.Color(0, 153, 255));
        txtNombre.setForeground(new java.awt.Color(51, 0, 51));
        txtNombre.setToolTipText("");
        txtNombre.setAlignmentY(10.0F);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre);
        txtNombre.setBounds(946, 45, 160, 40);

        jButtonDni.setBackground(new java.awt.Color(0, 0, 255));
        jButtonDni.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonDni.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDni.setText(">");
        jButtonDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDniActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDni);
        jButtonDni.setBounds(1110, 630, 63, 40);

        lblErrorDni.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorDni.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorDni.setText("* Error, ingrese Dni válido");
        lblErrorDni.setAlignmentY(10.0F);
        jPanel1.add(lblErrorDni);
        lblErrorDni.setBounds(950, 671, 124, 13);

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 153, 255));
        txtTotal.setForeground(new java.awt.Color(51, 0, 51));
        txtTotal.setToolTipText("");
        txtTotal.setAlignmentY(10.0F);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalKeyTyped(evt);
            }
        });
        jPanel1.add(txtTotal);
        txtTotal.setBounds(942, 715, 152, 50);

        jButtonFactura.setBackground(new java.awt.Color(0, 0, 255));
        jButtonFactura.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonFactura.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFactura.setText("FACTURAS");
        jButtonFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonFactura);
        jButtonFactura.setBounds(940, 780, 240, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchNomActionPerformed
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
                modelo.addColumn("VALOR DE VENTA");
                modelo.addColumn("VALOR CON DESCUENTO");

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Nombre LIKE'%" + Nombre + "%'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    do {
                        Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(4), RS.getString(7), RS.getString(8),};
                        modelo.addRow(Lista);
                    } while (RS.next());
                    tbListProducts.setModel(modelo);

                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el Producto en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    txtNombre.setText("");
                    txtNombre.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error en la consulta:" + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Debe ingresar un Nombre para validar",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            Limpiar();
            lblErrorNombre.setVisible(false);
            txtNombre.requestFocus();
        }

    }//GEN-LAST:event_jButtonSearchNomActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void TblProductsMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProductsMouseClick
        try {
            int row = this.tbListProducts.getSelectedRow();
            this.txtDni.setText(String.valueOf(this.tbListProducts.getValueAt(row, 1)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error en la consulta:" + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();
    }//GEN-LAST:event_TblProductsMouseClick

    private void tbListCarTblProductsMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListCarTblProductsMouseClick
        try {
            int row = this.tbListProducts.getSelectedRow();

            this.txtDni.setText(String.valueOf(this.tbListProducts.getValueAt(row, 1)));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error en la consulta:" + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();
    }//GEN-LAST:event_tbListCarTblProductsMouseClick

    private void jButtonAnadirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirCActionPerformed
        anadirTablaCar();
    }//GEN-LAST:event_jButtonAnadirCActionPerformed

    private void txtAnadirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnadirCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnadirCActionPerformed

    private void txtAnadirCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnadirCKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtAnadirCKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ListarTablaP();
        Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtElimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtElimiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElimiActionPerformed

    private void txtElimiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtElimiKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtElimiKeyTyped

    private void jButtonEliminarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCActionPerformed
        EliminarTablaCar();
    }//GEN-LAST:event_jButtonEliminarCActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        Pagar();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
       dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void jButtonDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDniActionPerformed
        String Dni = txtDni.getText();
        if (!Dni.equalsIgnoreCase("")) {
            try {
                String ConsBuscar = "SELECT * FROM TblClients WHERE Dni ='" + Dni + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(6), RS.getString(7),};
                    JOptionPane.showMessageDialog(rootPane, "El DNI " + RS.getString(1) + " ESTA ASOCIADO AL CLIENTE " + RS.getString(2) + " " + RS.getString(3));
                } else {
                    JOptionPane.showMessageDialog(rootPane, "EL CLIENTE NO EXISTE EN LA BASE DE DATOS");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error en la consulta:" + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Debe ingresar un Dni para validar",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            Limpiar();
            lblErrorDni.setVisible(false);
            txtDni.requestFocus();
        }


    }//GEN-LAST:event_jButtonDniActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key == 255;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtTotalKeyTyped

    private void jButtonFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFacturaActionPerformed
        frmFacturas venfact = new frmFacturas();
        venfact.setVisible(true);
    }//GEN-LAST:event_jButtonFacturaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton jButtonAnadirC;
    private javax.swing.JButton jButtonDni;
    private javax.swing.JButton jButtonEliminarC;
    private javax.swing.JButton jButtonFactura;
    private javax.swing.JButton jButtonSearchNom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrorAnadirC;
    private javax.swing.JLabel lblErrorDni;
    private javax.swing.JLabel lblErrorElimin;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JTable tbListCar;
    private javax.swing.JTable tbListProducts;
    private javax.swing.JTextField txtAnadirC;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtElimi;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}