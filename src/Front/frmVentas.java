package Front;

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

    }private void Total() {

        int total=0;
        
            try {                
                for (int row = 0; row < Carrito.getRowCount(); row++) {
                   

                        total+= Integer.parseInt(String.valueOf(this.Carrito.getValueAt(row, 4)));                      

                    } 
                
                txtTotal.setText(total+"");
                

                
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error al listar los datos: " + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        
       
       
    }

    private void anadirTablaCar() {

        int Cantidad = Integer.parseInt(txtAnadirC.getText());
        int row = this.tbListProducts.getSelectedRow();

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
        int Cantidad =Integer.parseInt(txtElimi.getText()); 
                
       

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
                String Codigo = (String.valueOf(this.tbListProducts.getValueAt(row, 0)));

                Cantidad = Integer.parseInt(String.valueOf(this.Carrito.getValueAt(row, 2)))-Cantidad;
                Carrito.removeRow(row);

                String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo ='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();

                // Recorer los resultados y cargalos a una lista
                if (Cantidad <= 0) {
                    Carrito.removeRow(row);
                    
                }
                else if (Cantidad <= 10) {
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jButtonDni = new javax.swing.JButton();
        lblErrorDni = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GESTION DE VENTAS");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 361));

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

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel5.setText("CARRITO");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE PRODUCTOS");

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);

        txtDni.setBackground(new java.awt.Color(0, 153, 255));
        txtDni.setForeground(new java.awt.Color(51, 0, 51));
        txtDni.setToolTipText("");
        txtDni.setAlignmentY(10.0F);
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });

        jButtonSearchNom.setBackground(new java.awt.Color(0, 0, 255));
        jButtonSearchNom.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonSearchNom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchNom.setText(">");
        jButtonSearchNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchNomActionPerformed(evt);
            }
        });

        lblErrorAnadirC.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorAnadirC.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorAnadirC.setText("* Error, ingrese Cantidad válida");
        lblErrorAnadirC.setAlignmentY(10.0F);

        jButtonAnadirC.setBackground(new java.awt.Color(0, 0, 255));
        jButtonAnadirC.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonAnadirC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAnadirC.setText(">");
        jButtonAnadirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirCActionPerformed(evt);
            }
        });

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

        btnActualizar.setBackground(new java.awt.Color(0, 0, 255));
        btnActualizar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR LISTA");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR POR NOMBRE");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("ANADIR CANTIDAD AL CARRITO");

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

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("DNI DEL CLIENTE");

        jButtonEliminarC.setBackground(new java.awt.Color(0, 0, 255));
        jButtonEliminarC.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonEliminarC.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarC.setText(">");
        jButtonEliminarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCActionPerformed(evt);
            }
        });

        lblErrorElimin.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorElimin.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorElimin.setText("* Error, ingrese Cantidad válida");
        lblErrorElimin.setAlignmentY(10.0F);

        btnPagar.setBackground(new java.awt.Color(0, 0, 255));
        btnPagar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnPagar.setForeground(new java.awt.Color(255, 255, 255));
        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("ELIMINAR CANTIDAD DEL CARRITO");

        btnCancelar1.setBackground(new java.awt.Color(0, 0, 255));
        btnCancelar1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar1.setText("CANCELAR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setText("VALOR TOTAL");

        txtNombre1.setBackground(new java.awt.Color(0, 153, 255));
        txtNombre1.setForeground(new java.awt.Color(51, 0, 51));
        txtNombre1.setToolTipText("");
        txtNombre1.setAlignmentY(10.0F);
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });

        jButtonDni.setBackground(new java.awt.Color(0, 0, 255));
        jButtonDni.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonDni.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDni.setText(">");
        jButtonDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDniActionPerformed(evt);
            }
        });

        lblErrorDni.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorDni.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorDni.setText("* Error, ingrese Dni válido");
        lblErrorDni.setAlignmentY(10.0F);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel4)
                        .addGap(400, 400, 400)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(380, 380, 380)
                                        .addComponent(jLabel5))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtElimi, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEliminarC, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)
                                                .addComponent(jButtonSearchNom, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblErrorNombre)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtAnadirC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jButtonAnadirC, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblErrorAnadirC)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblErrorDni))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblErrorElimin))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDni, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonSearchNom, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(lblErrorNombre)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnadirC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnadirC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(lblErrorAnadirC)
                        .addGap(17, 17, 17)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonEliminarC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtElimi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorElimin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDni, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorDni)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(txtTotal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1244, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchNomActionPerformed
        String Nombre = txtDni.getText();

        if (!Nombre.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                modelo.addColumn("Codigo");
                modelo.addColumn("Nombre");
                modelo.addColumn("Cantidad");
                modelo.addColumn("Valor De Venta");
                modelo.addColumn("Valor Con Descuento");

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
                    txtDni.setText("");
                    txtDni.requestFocus();
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
            lblErrorNombre.setVisible(false);
            txtDni.requestFocus();
        }
        Limpiar();
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
 
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

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
            lblErrorDni.setVisible(false);
            txtDni.requestFocus();
        }
        Limpiar();

    }//GEN-LAST:event_jButtonDniActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyTyped
                int key = evt.getKeyChar();
        boolean numero = key ==255;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtTotalKeyTyped

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
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButtonSearchNom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
