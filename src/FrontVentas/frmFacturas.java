package FrontVentas;

import Conexion.ClsConexion;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmFacturas extends javax.swing.JFrame {

    ClsConexion CON;
    Connection CN;
    DefaultTableModel Carrito;

    public frmFacturas() {
        initComponents();
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorFecha.setVisible(false);

        this.setLocationRelativeTo(this);
        CON = new ClsConexion();
        CN = CON.getConnection();
        ListarTablaP();
        CrearTablaCar();
        Limpiar();
    }

    private double Total() {

        float total = 0;

        try {
            for (int row = 0; row < tbListProducts.getRowCount(); row++) {

                total += Double.parseDouble(String.valueOf(this.tbListProducts.getValueAt(row, 4)));

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

    private void Limpiar() {
        // Se limpian todos los campos

        // Se ocultan todos los errores
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorFecha.setVisible(false);
        txtDni.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        Total();

    }

    private void Limpiare() {

        // Se ocultan todos los errores
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorFecha.setVisible(false);
        Total();

    }

    private void CrearTablaCar() {

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

    private void ListarTablaP() {

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
            JOptionPane.showMessageDialog(rootPane,
                    "Error al listar los datos: " + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void ListarTablaFact(int fact) {

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
                JOptionPane.showMessageDialog(rootPane,
                        "¡¡No existe la factura en la base de datos!!",
                        "¡Error!",
                        JOptionPane.ERROR_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error en la consulta:" + e.getMessage(),
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
        lblErrorDni = new javax.swing.JLabel();
        jButtonSearchDni = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar1 = new javax.swing.JButton();
        txtDni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jButtonSearchNombre = new javax.swing.JButton();
        lblErrorNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblErrorFecha = new javax.swing.JLabel();
        jButtonSearchFecha = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURAS DE VENTA");
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
        jScrollPane2.setBounds(10, 418, 913, 280);

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
        jScrollPane1.setBounds(0, 44, 928, 330);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel5.setText("FACTURA");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(420, 380, 114, 30);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE FACTURAS DE VENTA");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(298, 10, 374, 30);

        lblErrorDni.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorDni.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorDni.setText("* Error, ingrese Dni válido");
        lblErrorDni.setAlignmentY(10.0F);
        jPanel1.add(lblErrorDni);
        lblErrorDni.setBounds(946, 100, 124, 13);

        jButtonSearchDni.setBackground(new java.awt.Color(0, 0, 255));
        jButtonSearchDni.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonSearchDni.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchDni.setText(">");
        jButtonSearchDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchDniActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSearchDni);
        jButtonSearchDni.setBounds(1130, 60, 60, 40);

        btnActualizar.setBackground(new java.awt.Color(0, 0, 255));
        btnActualizar.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR LISTA DE FACTURAS");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar);
        btnActualizar.setBounds(940, 420, 250, 40);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("BUSCAR POR DNI");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(950, 40, 121, 18);

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
        btnCancelar1.setBounds(940, 480, 250, 40);

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
        txtDni.setBounds(946, 58, 170, 40);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR POR NOMBRE");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(950, 130, 160, 18);

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
        txtNombre.setBounds(946, 149, 170, 40);

        jButtonSearchNombre.setBackground(new java.awt.Color(0, 0, 255));
        jButtonSearchNombre.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonSearchNombre.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchNombre.setText(">");
        jButtonSearchNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchNombreActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSearchNombre);
        jButtonSearchNombre.setBounds(1130, 150, 60, 40);

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);
        jPanel1.add(lblErrorNombre);
        lblErrorNombre.setBounds(950, 190, 146, 13);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("BUSCAR POR FECHA");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(950, 220, 144, 18);

        txtFecha.setBackground(new java.awt.Color(0, 153, 255));
        txtFecha.setForeground(new java.awt.Color(51, 0, 51));
        txtFecha.setToolTipText("");
        txtFecha.setAlignmentY(10.0F);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel1.add(txtFecha);
        txtFecha.setBounds(946, 242, 170, 40);

        lblErrorFecha.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorFecha.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorFecha.setText("* Error, ingrese Fecha válida");
        lblErrorFecha.setAlignmentY(10.0F);
        jPanel1.add(lblErrorFecha);
        lblErrorFecha.setBounds(950, 280, 136, 13);

        jButtonSearchFecha.setBackground(new java.awt.Color(0, 0, 255));
        jButtonSearchFecha.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jButtonSearchFecha.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearchFecha.setText(">");
        jButtonSearchFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchFechaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSearchFecha);
        jButtonSearchFecha.setBounds(1130, 240, 60, 40);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setText("VALOR TOTAL");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(1000, 300, 130, 23);

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(0, 153, 255));
        txtTotal.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(51, 0, 51));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
        txtTotal.setBounds(940, 330, 250, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchDniActionPerformed
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
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el Producto en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    Limpiare();
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
            Limpiar();
            lblErrorDni.setVisible(false);
            txtDni.requestFocus();
        }
        Limpiar();

    }//GEN-LAST:event_jButtonSearchDniActionPerformed

    private void TblProductsMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProductsMouseClick
        try {
            int row = this.tbListProducts.getSelectedRow();
            int code = Integer.parseInt(String.valueOf(this.tbListProducts.getValueAt(row, 0)));
            ListarTablaFact(code);

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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Error en la consulta:" + e.getMessage(),
                    "¡Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        Limpiar();
    }//GEN-LAST:event_tbListCarTblProductsMouseClick

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ListarTablaP();
        Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
      dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void jButtonSearchNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchNombreActionPerformed
        String Nombre = txtNombre.getText();

        if (!Nombre.equalsIgnoreCase("")) {
            try {
                DefaultTableModel modelo = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                modelo.addColumn("CODIGO");
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
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el Producto en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    Limpiare();
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
            Limpiar();
            lblErrorDni.setVisible(false);
            txtDni.requestFocus();
        }
        Limpiar();

    }//GEN-LAST:event_jButtonSearchNombreActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void jButtonSearchFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchFechaActionPerformed
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
                    JOptionPane.showMessageDialog(rootPane,
                            "¡¡No existe el Producto en la base de datos!!",
                            "¡Error!",
                            JOptionPane.ERROR_MESSAGE);
                    Limpiare();
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
            Limpiar();
            lblErrorDni.setVisible(false);
            txtDni.requestFocus();
        }
        Limpiar();
    }//GEN-LAST:event_jButtonSearchFechaActionPerformed

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
            java.util.logging.Logger.getLogger(frmFacturas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFacturas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFacturas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFacturas.class
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
                new frmFacturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton jButtonSearchDni;
    private javax.swing.JButton jButtonSearchFecha;
    private javax.swing.JButton jButtonSearchNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrorDni;
    private javax.swing.JLabel lblErrorFecha;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JTable tbListCar;
    private javax.swing.JTable tbListProducts;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
