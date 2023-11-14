package Front;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmProductsC extends javax.swing.JFrame {

    ClsConexion CON;
    Connection CN;

    public frmProductsC() {
        initComponents();
        lblErrorCodigo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApodo.setVisible(false);
        lblErrorCantidadP.setVisible(false);
        lblErrorCantidadV.setVisible(false);
        lblErrorValorV.setVisible(false);
        lblErrorValorC.setVisible(false);
        lblErrorValorD.setVisible(false);
        this.setLocationRelativeTo(this);
        CON = new ClsConexion();
        CN = CON.getConnection();
        ListarTabla();
    }

    private void Limpiar() {
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

    private void ListarTabla() {

        // Definición de la configuración de la tabla y sus columnas
        DefaultTableModel modelo = new DefaultTableModel();
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
                Object[] Lista = {RS.getString(1), RS.getString(2), RS.getString(3), RS.getString(4), RS.getString(5), RS.getString(7), RS.getString(6), RS.getString(8),};
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
        txtCodigo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        lblErrorCodigo = new javax.swing.JLabel();
        lblErrorApodo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListProducts = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblErrorCantidadP = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblErrorCantidadV = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblErrorValorC = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblErrorValorV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApodo = new javax.swing.JTextField();
        lblErrorNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCantidadP = new javax.swing.JTextField();
        txtCantidadV = new javax.swing.JTextField();
        txtValorC = new javax.swing.JTextField();
        txtValorD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtValorV = new javax.swing.JTextField();
        lblErrorValorD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion De Productos");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 361));

        txtCodigo.setBackground(new java.awt.Color(0, 153, 255));
        txtCodigo.setForeground(new java.awt.Color(51, 0, 51));
        txtCodigo.setToolTipText("");
        txtCodigo.setAlignmentY(10.0F);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 255));
        btnRegistrar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR PRODUCTO");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        lblErrorCodigo.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorCodigo.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorCodigo.setText("* Error, ingrese Codigo válido");
        lblErrorCodigo.setAlignmentY(10.0F);

        lblErrorApodo.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorApodo.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorApodo.setText("* Error, ingrese Apodo válido");
        lblErrorApodo.setAlignmentY(10.0F);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("CODIGO DEL PRODUCTO");
        jLabel1.setAlignmentY(10.0F);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("APODO DEL PRODUCTO");
        jLabel2.setAlignmentY(10.0F);

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
        tbListProducts.setEnabled(false);
        tbListProducts.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tbListProducts);

        btnCancelar.setBackground(new java.awt.Color(0, 0, 255));
        btnCancelar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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

        btnLimpiar.setBackground(new java.awt.Color(0, 0, 255));
        btnLimpiar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR REGISTRO");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE PRODUCTOS");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("CANTIDAD DEL PRODUCTO");
        jLabel6.setAlignmentY(10.0F);

        lblErrorCantidadP.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorCantidadP.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorCantidadP.setText("* Error, ingrese Cantidad válida");
        lblErrorCantidadP.setAlignmentY(10.0F);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("CANTIDAD VENDIDA");
        jLabel7.setAlignmentY(10.0F);

        lblErrorCantidadV.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorCantidadV.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorCantidadV.setText("* Error, ingrese Cantidad válida");
        lblErrorCantidadV.setAlignmentY(10.0F);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel8.setText("VALOR DE COMPRA");
        jLabel8.setAlignmentY(10.0F);

        lblErrorValorC.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorValorC.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorValorC.setText("* Error, ingrese valor válido");
        lblErrorValorC.setAlignmentY(10.0F);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel9.setText("VALOR DE VENTA");
        jLabel9.setAlignmentY(10.0F);

        lblErrorValorV.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorValorV.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorValorV.setText("* Error, ingrese valor valido");
        lblErrorValorV.setAlignmentY(10.0F);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE DEL PRODUCTO");
        jLabel3.setAlignmentY(10.0F);

        txtApodo.setBackground(new java.awt.Color(0, 153, 255));
        txtApodo.setForeground(new java.awt.Color(51, 0, 51));
        txtApodo.setToolTipText("");
        txtApodo.setAlignmentY(10.0F);
        txtApodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApodoActionPerformed(evt);
            }
        });

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);

        txtNombre.setBackground(new java.awt.Color(0, 153, 255));
        txtNombre.setForeground(new java.awt.Color(51, 0, 51));
        txtNombre.setToolTipText("");
        txtNombre.setAlignmentY(10.0F);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtCantidadP.setBackground(new java.awt.Color(0, 153, 255));
        txtCantidadP.setForeground(new java.awt.Color(51, 0, 51));
        txtCantidadP.setToolTipText("");
        txtCantidadP.setAlignmentY(10.0F);
        txtCantidadP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadPActionPerformed(evt);
            }
        });

        txtCantidadV.setBackground(new java.awt.Color(0, 153, 255));
        txtCantidadV.setForeground(new java.awt.Color(51, 0, 51));
        txtCantidadV.setToolTipText("");
        txtCantidadV.setAlignmentY(10.0F);
        txtCantidadV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadVActionPerformed(evt);
            }
        });

        txtValorC.setBackground(new java.awt.Color(0, 153, 255));
        txtValorC.setForeground(new java.awt.Color(51, 0, 51));
        txtValorC.setToolTipText("");
        txtValorC.setAlignmentY(10.0F);
        txtValorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorCActionPerformed(evt);
            }
        });

        txtValorD.setBackground(new java.awt.Color(0, 153, 255));
        txtValorD.setForeground(new java.awt.Color(51, 0, 51));
        txtValorD.setToolTipText("");
        txtValorD.setAlignmentY(10.0F);
        txtValorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorDActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 0, 255));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setText("VALOR CON DESCUENTO");
        jLabel10.setAlignmentY(10.0F);

        txtValorV.setBackground(new java.awt.Color(0, 153, 255));
        txtValorV.setForeground(new java.awt.Color(51, 0, 51));
        txtValorV.setToolTipText("");
        txtValorV.setAlignmentY(10.0F);
        txtValorV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorVActionPerformed(evt);
            }
        });

        lblErrorValorD.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorValorD.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorValorD.setText("* Error, ingrese valor valido");
        lblErrorValorD.setAlignmentY(10.0F);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtCantidadP, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtValorC, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtValorV, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtValorD, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblErrorCodigo)
                        .addGap(130, 130, 130)
                        .addComponent(lblErrorNombre)
                        .addGap(44, 44, 44)
                        .addComponent(lblErrorApodo)
                        .addGap(39, 39, 39)
                        .addComponent(lblErrorCantidadP)
                        .addGap(48, 48, 48)
                        .addComponent(lblErrorCantidadV)
                        .addGap(12, 12, 12)
                        .addComponent(lblErrorValorC)
                        .addGap(18, 18, 18)
                        .addComponent(lblErrorValorV)
                        .addGap(18, 18, 18)
                        .addComponent(lblErrorValorD))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(468, 468, 468)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorCodigo)
                    .addComponent(lblErrorNombre)
                    .addComponent(lblErrorApodo)
                    .addComponent(lblErrorCantidadP)
                    .addComponent(lblErrorCantidadV)
                    .addComponent(lblErrorValorC)
                    .addComponent(lblErrorValorV)
                    .addComponent(lblErrorValorD))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1542, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ListarTabla();
        Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String Codigo = txtCodigo.getText();
        String Nombre = txtNombre.getText();
        String Apodo = txtApodo.getText();
        String CantidadP = txtCantidadP.getText();
        String CantidadV = txtCantidadV.getText();
        String ValorD = txtValorD.getText();
        String ValorC = txtValorC.getText();
        String ValorV = txtValorV.getText();

        if (Codigo.equals("")) {
            lblErrorCodigo.setVisible(true);
            txtCodigo.requestFocus();
        } else if (Nombre.equals("")) {
            lblErrorNombre.setVisible(true);
            txtNombre.requestFocus();
        } else if (Apodo.equals("")) {
            lblErrorApodo.setVisible(true);
            txtApodo.requestFocus();
        } else if (CantidadP.equals("")) {
            lblErrorCantidadP.setVisible(true);
            txtCantidadP.requestFocus();
        } else if (CantidadV.equals("")) {
            lblErrorCantidadV.setVisible(true);
            txtCantidadV.requestFocus();
        } else if (ValorV.equals("")) {
            lblErrorValorV.setVisible(true);
            txtValorV.requestFocus();
        } else if (ValorC.equals("")) {
            lblErrorValorC.setVisible(true);
            txtValorC.requestFocus();
        } else if (ValorD.equals("")) {
            lblErrorValorD.setVisible(true);
            txtValorD.requestFocus();
        } else {
            try {
                String ValCodigo = "SELECT * FROM TblProducts WHERE Codigo='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ValCodigo);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    JOptionPane.showMessageDialog(rootPane, "¡Error! el Producto ya existe en la BD");
                } else {
                    String ConsInser = "INSERT INTO tblProducts(Codigo,"
                            + " Nombre,"
                            + " Apodo,"
                            + " CantidadP,"
                            + " CantidadV,"
                            + " ValorD,"
                            + " ValorC,ValorV ) "
                            + "VALUES ('" + Codigo + "','" + Nombre + "','" + Apodo + "','" + CantidadP + "','" + CantidadV + "','" + ValorV + "','" + ValorC + "','" + ValorD + "')";
                    PreparedStatement PS1 = CN.prepareStatement(ConsInser);
                    PS1.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Producto registrado con éxito");
                    ListarTabla();
                    Limpiar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error en el registro:" + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtApodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApodoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCantidadPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadPActionPerformed

    private void txtCantidadVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadVActionPerformed

    private void txtValorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorCActionPerformed

    private void txtValorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Codigo = txtCodigo.getText();

        if (!Codigo.equalsIgnoreCase("")) {
            try {
                String ConsBuscar = "SELECT * FROM TblProducts WHERE Codigo='" + Codigo + "'";
                PreparedStatement PS = CN.prepareStatement(ConsBuscar);
                ResultSet RS = PS.executeQuery();
                if (RS.next()) {
                    txtNombre.setText(RS.getString("Nombre"));
                    txtApodo.setText(RS.getString("Apodo"));
                    txtCantidadP.setText(RS.getString("CantidadP"));
                    txtValorD.setText(RS.getString("ValorD"));
                    txtCantidadV.setText(RS.getString("CantidadV"));
                    txtValorC.setText(RS.getString("ValorC"));
                    txtValorV.setText(RS.getString("ValorV"));

                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el Producto en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    txtCodigo.setText("");
                    txtCodigo.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane,
                        "Error en el consulta:" + e.getMessage(),
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Debe ingresar un Codigo para validar",
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
            lblErrorCodigo.setVisible(false);
            txtCodigo.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtValorVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVActionPerformed

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
            java.util.logging.Logger.getLogger(frmProductsC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProductsC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProductsC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProductsC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new frmProductsC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorApodo;
    private javax.swing.JLabel lblErrorCantidadP;
    private javax.swing.JLabel lblErrorCantidadV;
    private javax.swing.JLabel lblErrorCodigo;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JLabel lblErrorValorC;
    private javax.swing.JLabel lblErrorValorD;
    private javax.swing.JLabel lblErrorValorV;
    private javax.swing.JTable tbListProducts;
    private javax.swing.JTextField txtApodo;
    private javax.swing.JTextField txtCantidadP;
    private javax.swing.JTextField txtCantidadV;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtValorC;
    private javax.swing.JTextField txtValorD;
    private javax.swing.JTextField txtValorV;
    // End of variables declaration//GEN-END:variables
}
