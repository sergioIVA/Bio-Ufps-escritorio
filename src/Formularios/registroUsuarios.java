/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author sergio
 */
public class registroUsuarios extends javax.swing.JFrame {
   private tomarAsistencia myAsistencia;

  
   
   
   
    /**
     * Creates new form registroUsuarios
     */
    public registroUsuarios(tomarAsistencia asis) {
        initComponents();
         this.setTitle("Registrar Usuarios");
        asis.cerrarFormulario();
        this.setLocationRelativeTo(null);
        this.myAsistencia=asis;
        this.setSize(430,150);
        
    }
  
    public tomarAsistencia getMyAsistencia() {
        return myAsistencia;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        usuario = new javax.swing.JComboBox<>();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("REGISTRO DE USUARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Docente", "Administrativo", "Externo" }));
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        aceptar.setText("aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });
        getContentPane().add(aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 82, -1, -1));

        cancelar.setText("cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 82, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/huella2.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
                 
        if (this.usuario.getSelectedIndex() == 0) {
            
            registroEstudiante r = new registroEstudiante(this);
            r.setVisible(true);
        } else if (this.usuario.getSelectedIndex() == 1) {
            registrarDocente r=new registrarDocente(this);
            r.setVisible(true);
        }
        else if(this.usuario.getSelectedIndex() == 2){
            registroAdministrativo r=new registroAdministrativo(this);
            r.setVisible(true);
        }else{
            registroExterno r = new registroExterno(this);
            r.setVisible(true);
        }
        this.setVisible(false);  
    }//GEN-LAST:event_aceptarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
       this.setVisible(false);
       this.myAsistencia.setVisible(true);
    }//GEN-LAST:event_cancelarActionPerformed

    
    
   
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> usuario;
    // End of variables declaration//GEN-END:variables
}
