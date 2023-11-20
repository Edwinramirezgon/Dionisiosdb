package FrontClientes;

import BackendCliente.BackClienteCajero;

public class frmClientsCAjero extends javax.swing.JFrame {

   BackClienteCajero ObjCLientes;

    public frmClientsCAjero() {
        ObjCLientes = new BackClienteCajero();
        initComponents();
        lblErrorDni.setVisible(false);
        lblErrorNombre.setVisible(false);
        lblErrorApellido.setVisible(false);
        lblErrorCorreo.setVisible(false);
        lblErrorDireccion.setVisible(false);
        lblErrorTelefono.setVisible(false);
        lblErrorFecha.setVisible(false);
        this.setLocationRelativeTo(this);
        ObjCLientes.ListarTabla();
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
        txtDni = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        lblErrorDni = new javax.swing.JLabel();
        lblErrorApellido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListClients = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblErrorCorreo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblErrorDireccion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblErrorFecha = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblErrorTelefono = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblErrorNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion De Clientes");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 361));
        jPanel1.setLayout(null);

        txtDni.setBackground(new java.awt.Color(0, 153, 255));
        txtDni.setForeground(new java.awt.Color(51, 0, 51));
        txtDni.setToolTipText("");
        txtDni.setAlignmentY(10.0F);
        txtDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniActionPerformed(evt);
            }
        });
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
        });
        jPanel1.add(txtDni);
        txtDni.setBounds(20, 50, 160, 35);

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 255));
        btnRegistrar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR CLIENTE");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar);
        btnRegistrar.setBounds(20, 310, 184, 40);

        lblErrorDni.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorDni.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorDni.setText("* Error, ingrese DNI válido");
        lblErrorDni.setAlignmentY(10.0F);
        jPanel1.add(lblErrorDni);
        lblErrorDni.setBounds(40, 90, 125, 13);

        lblErrorApellido.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorApellido.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorApellido.setText("* Error, ingrese Apellido válido");
        lblErrorApellido.setAlignmentY(10.0F);
        jPanel1.add(lblErrorApellido);
        lblErrorApellido.setBounds(375, 91, 148, 13);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("DNI DEL CLIENTE");
        jLabel1.setAlignmentY(10.0F);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 30, 118, 18);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("APELLIDO DEL CLIENTE");
        jLabel2.setAlignmentY(10.0F);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(370, 30, 163, 18);

        tbListClients.setAutoCreateRowSorter(true);
        tbListClients.setModel(new javax.swing.table.DefaultTableModel(
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
        tbListClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbListClients);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(230, 160, 1040, 368);

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
        btnCancelar.setBounds(20, 480, 184, 40);

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
        btnActualizar.setBounds(20, 430, 184, 40);

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
        btnLimpiar.setBounds(20, 370, 184, 40);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); // NOI18N
        jLabel4.setText("LISTA DE CLIENTES");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(590, 110, 236, 30);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("CORREO DEL CLIENTE");
        jLabel6.setAlignmentY(10.0F);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(560, 30, 155, 18);

        lblErrorCorreo.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorCorreo.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorCorreo.setText("* Error, ingrese Correo válido");
        lblErrorCorreo.setAlignmentY(10.0F);
        jPanel1.add(lblErrorCorreo);
        lblErrorCorreo.setBounds(553, 91, 141, 13);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("DIRECCION DEL CLIENTE");
        jLabel7.setAlignmentY(10.0F);
        jPanel1.add(jLabel7);
        jLabel7.setBounds(730, 30, 172, 18);

        lblErrorDireccion.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorDireccion.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorDireccion.setText("* Error, ingrese Direccion válida");
        lblErrorDireccion.setAlignmentY(10.0F);
        jPanel1.add(lblErrorDireccion);
        lblErrorDireccion.setBounds(737, 91, 153, 13);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel8.setText("FECHA DE NACIMIENTO");
        jLabel8.setAlignmentY(10.0F);
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1110, 30, 164, 18);

        lblErrorFecha.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorFecha.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorFecha.setText("* Error, ingrese fecha válida");
        lblErrorFecha.setAlignmentY(10.0F);
        jPanel1.add(lblErrorFecha);
        lblErrorFecha.setBounds(1120, 90, 135, 13);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel9.setText("TELEFONO DEL CLIENTE");
        jLabel9.setAlignmentY(10.0F);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(920, 30, 169, 18);

        lblErrorTelefono.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorTelefono.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorTelefono.setText("* Error, ingrese Telefono valido");
        lblErrorTelefono.setAlignmentY(10.0F);
        jPanel1.add(lblErrorTelefono);
        lblErrorTelefono.setBounds(920, 90, 151, 13);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE DEL CLIENTE");
        jLabel3.setAlignmentY(10.0F);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 30, 157, 18);

        txtApellido.setBackground(new java.awt.Color(0, 153, 255));
        txtApellido.setForeground(new java.awt.Color(51, 0, 51));
        txtApellido.setToolTipText("");
        txtApellido.setAlignmentY(10.0F);
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });
        jPanel1.add(txtApellido);
        txtApellido.setBounds(375, 50, 160, 35);

        lblErrorNombre.setFont(new java.awt.Font("Lucida Sans", 0, 10)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(153, 0, 0));
        lblErrorNombre.setText("* Error, ingrese Nombre válido");
        lblErrorNombre.setAlignmentY(10.0F);
        jPanel1.add(lblErrorNombre);
        lblErrorNombre.setBounds(197, 91, 146, 13);

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
        txtNombre.setBounds(197, 50, 160, 35);

        txtCorreo.setBackground(new java.awt.Color(0, 153, 255));
        txtCorreo.setForeground(new java.awt.Color(51, 0, 51));
        txtCorreo.setToolTipText("");
        txtCorreo.setAlignmentY(10.0F);
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });
        jPanel1.add(txtCorreo);
        txtCorreo.setBounds(553, 50, 160, 35);

        txtDireccion.setBackground(new java.awt.Color(0, 153, 255));
        txtDireccion.setForeground(new java.awt.Color(51, 0, 51));
        txtDireccion.setToolTipText("");
        txtDireccion.setAlignmentY(10.0F);
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtDireccion);
        txtDireccion.setBounds(731, 50, 170, 35);

        txtFecha.setBackground(new java.awt.Color(0, 153, 255));
        txtFecha.setForeground(new java.awt.Color(51, 0, 51));
        txtFecha.setToolTipText("");
        txtFecha.setAlignmentY(10.0F);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
        });
        jPanel1.add(txtFecha);
        txtFecha.setBounds(1110, 50, 170, 35);

        txtTelefono.setBackground(new java.awt.Color(0, 153, 255));
        txtTelefono.setForeground(new java.awt.Color(51, 0, 51));
        txtTelefono.setToolTipText("");
        txtTelefono.setAlignmentY(10.0F);
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        jPanel1.add(txtTelefono);
        txtTelefono.setBounds(920, 50, 170, 35);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N
        lblLogo.setRequestFocusEnabled(false);
        jPanel1.add(lblLogo);
        lblLogo.setBounds(10, 110, 200, 200);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1296, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        ObjCLientes.Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ObjCLientes.ListarTabla();
        ObjCLientes.Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        ObjCLientes.RegistarCliente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void tbClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientMouseClicked
 ObjCLientes.ClickListaClientes();
    
    }//GEN-LAST:event_tbClientMouseClicked

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased
      ObjCLientes.BuscarClienteDni();
    }//GEN-LAST:event_txtDniKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
      ObjCLientes.BuscarClienteNombre();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        ObjCLientes.BuscarClienteApellidos();
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        ObjCLientes.BuscarClienteCorreo();
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        ObjCLientes.BuscarClienteDireccion();
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
      ObjCLientes.BuscarClienteFecha();
    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
      ObjCLientes.BuscarClienteTelefono();
    }//GEN-LAST:event_txtTelefonoKeyReleased

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
            java.util.logging.Logger.getLogger(frmClientsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmClientsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmClientsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmClientsCAjero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frmClientsCAjero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblErrorApellido;
    public static javax.swing.JLabel lblErrorCorreo;
    public static javax.swing.JLabel lblErrorDireccion;
    public static javax.swing.JLabel lblErrorDni;
    public static javax.swing.JLabel lblErrorFecha;
    public static javax.swing.JLabel lblErrorNombre;
    public static javax.swing.JLabel lblErrorTelefono;
    private javax.swing.JLabel lblLogo;
    public static javax.swing.JTable tbListClients;
    public static javax.swing.JTextField txtApellido;
    public static javax.swing.JTextField txtCorreo;
    public static javax.swing.JTextField txtDireccion;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
