package FrontProductos;

import BackendProductos.BackProductosCajero;

public class frmProductsCAjero extends javax.swing.JFrame {

BackProductosCajero ObjProductos;

    public frmProductsCAjero() {
        ObjProductos = new BackProductosCajero();
        initComponents();
        lblErrorCodigo.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApodo.setVisible(false);
        lblErrorValorV.setVisible(false);
        lblErrorValorD.setVisible(false);
        this.setLocationRelativeTo(this);      
        ObjProductos.ListarTabla();
        ObjProductos.Limpiar();
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
        jLabel9 = new javax.swing.JLabel();
        lblErrorValorV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApodo = new javax.swing.JTextField();
        lblErrorNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtValorD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtValorV = new javax.swing.JTextField();
        lblErrorValorD = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion De Productos");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 361));
        jPanel1.setLayout(null);

        txtCodigo.setBackground(new java.awt.Color(0, 153, 255));
        txtCodigo.setForeground(new java.awt.Color(51, 0, 51));
        txtCodigo.setToolTipText("");
        txtCodigo.setAlignmentY(10.0F);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        jPanel1.add(txtCodigo);
        txtCodigo.setBounds(300, 50, 186, 40);

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 255));
        btnRegistrar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR PRODUCTO");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar);
        btnRegistrar.setBounds(10, 230, 203, 40);

        lblErrorCodigo.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorCodigo.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorCodigo.setText("* Error, ingrese Codigo válido");
        lblErrorCodigo.setAlignmentY(10.0F);
        jPanel1.add(lblErrorCodigo);
        lblErrorCodigo.setBounds(310, 90, 142, 20);

        lblErrorApodo.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorApodo.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorApodo.setText("* Error, ingrese Apodo válido");
        lblErrorApodo.setAlignmentY(10.0F);
        jPanel1.add(lblErrorApodo);
        lblErrorApodo.setBounds(690, 90, 139, 13);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("CODIGO DEL PRODUCTO");
        jLabel1.setAlignmentY(10.0F);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(300, 20, 172, 30);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("APODO DEL PRODUCTO");
        jLabel2.setAlignmentY(10.0F);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(690, 30, 166, 18);

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
        jScrollPane1.setBounds(230, 150, 1200, 350);

        btnCancelar.setBackground(new java.awt.Color(0, 0, 255));
        btnCancelar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(10, 460, 203, 40);

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
        btnActualizar.setBounds(10, 370, 203, 40);

        btnLimpiar.setBackground(new java.awt.Color(0, 0, 255));
        btnLimpiar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR REGISTRO");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar);
        btnLimpiar.setBounds(10, 300, 203, 40);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE PRODUCTOS");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(680, 110, 272, 30);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel9.setText("VALOR DE VENTA");
        jLabel9.setAlignmentY(10.0F);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(870, 30, 122, 18);

        lblErrorValorV.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorValorV.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorValorV.setText("* Error, ingrese valor valido");
        lblErrorValorV.setAlignmentY(10.0F);
        jPanel1.add(lblErrorValorV);
        lblErrorValorV.setBounds(870, 90, 132, 13);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE DEL PRODUCTO");
        jLabel3.setAlignmentY(10.0F);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(500, 30, 178, 18);

        txtApodo.setBackground(new java.awt.Color(0, 153, 255));
        txtApodo.setForeground(new java.awt.Color(51, 0, 51));
        txtApodo.setToolTipText("");
        txtApodo.setAlignmentY(10.0F);
        txtApodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApodoActionPerformed(evt);
            }
        });
        txtApodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApodoKeyReleased(evt);
            }
        });
        jPanel1.add(txtApodo);
        txtApodo.setBounds(690, 50, 166, 35);

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);
        jPanel1.add(lblErrorNombre);
        lblErrorNombre.setBounds(500, 90, 146, 13);

        txtNombre.setBackground(new java.awt.Color(0, 153, 255));
        txtNombre.setForeground(new java.awt.Color(51, 0, 51));
        txtNombre.setToolTipText("");
        txtNombre.setAlignmentY(10.0F);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel1.add(txtNombre);
        txtNombre.setBounds(500, 50, 178, 35);

        txtValorD.setBackground(new java.awt.Color(0, 153, 255));
        txtValorD.setForeground(new java.awt.Color(51, 0, 51));
        txtValorD.setToolTipText("");
        txtValorD.setAlignmentY(10.0F);
        txtValorD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorDActionPerformed(evt);
            }
        });
        txtValorD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorDKeyTyped(evt);
            }
        });
        jPanel1.add(txtValorD);
        txtValorD.setBounds(1020, 50, 182, 35);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setText("VALOR CON DESCUENTO");
        jLabel10.setAlignmentY(10.0F);
        jPanel1.add(jLabel10);
        jLabel10.setBounds(1020, 30, 182, 18);

        txtValorV.setBackground(new java.awt.Color(0, 153, 255));
        txtValorV.setForeground(new java.awt.Color(51, 0, 51));
        txtValorV.setToolTipText("");
        txtValorV.setAlignmentY(10.0F);
        txtValorV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorVActionPerformed(evt);
            }
        });
        txtValorV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorVKeyTyped(evt);
            }
        });
        jPanel1.add(txtValorV);
        txtValorV.setBounds(870, 50, 132, 35);

        lblErrorValorD.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorValorD.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorValorD.setText("* Error, ingrese valor valido");
        lblErrorValorD.setAlignmentY(10.0F);
        jPanel1.add(lblErrorValorD);
        lblErrorValorD.setBounds(1020, 90, 132, 13);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N
        lblLogo.setRequestFocusEnabled(false);
        jPanel1.add(lblLogo);
        lblLogo.setBounds(20, 10, 200, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1453, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        ObjProductos.Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ObjProductos.ListarTabla();
        ObjProductos.Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
  ObjProductos.RegistrarProducto();
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

    private void txtValorDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorDActionPerformed

    private void txtValorVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVActionPerformed

    private void TblProductsMouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblProductsMouseClick
        ObjProductos.ClickTablaProductos();
    }//GEN-LAST:event_TblProductsMouseClick

    private void txtValorVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtValorVKeyTyped

    private void txtValorDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorDKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;

        if (!numero) {
            evt.consume();

        }
    }//GEN-LAST:event_txtValorDKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        ObjProductos.BuscarProductoCodigo();
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
      ObjProductos.BuscarProductoNombre();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApodoKeyReleased
        ObjProductos.BuscarProductoApodo();
    }//GEN-LAST:event_txtApodoKeyReleased

    private void txtValorVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVKeyReleased
        ObjProductos.BuscarProductoValorVenta();
    }//GEN-LAST:event_txtValorVKeyReleased

    private void txtValorDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorDKeyReleased
       ObjProductos.BuscarProductoValoDescuento();
    }//GEN-LAST:event_txtValorDKeyReleased

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
            java.util.logging.Logger.getLogger(frmProductsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmProductsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmProductsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmProductsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmProductsCAjero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblErrorApodo;
    public static javax.swing.JLabel lblErrorCodigo;
    public static javax.swing.JLabel lblErrorNombre;
    public static javax.swing.JLabel lblErrorValorD;
    public static javax.swing.JLabel lblErrorValorV;
    private javax.swing.JLabel lblLogo;
    public static javax.swing.JTable tbListProducts;
    public static javax.swing.JTextField txtApodo;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtValorD;
    public static javax.swing.JTextField txtValorV;
    // End of variables declaration//GEN-END:variables
}
