/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;


import Dao.UsuarioDao;
import Negocio.Huella;
import Negocio.Negocio;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import dto.AsistenciaP;
import dto.Usuario;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author sergio
 */
public class tomarAsistencia extends javax.swing.JFrame {
    public DPFPCapture lector =null; 
    private String mensaje;
    private byte[] actual;
    private Negocio myNegocio;
    Usuario usuario;
    Image imagenHuella=null;
    private  JLabel imagen1; 
    private Huella h; 
    public boolean activado=false;
    public Color antes;

    
    
    
    public JLabel getImagen1() {
        return imagen1;
    }
    
    public Image getImagenHuella() {
        return imagenHuella;
    }

    public byte[] getActual() {
        return actual;
    }

  
      public Negocio getMyNegocio() {
        return myNegocio;
    }

    public void iniciarTodo(){
     this.actual=null;
     usuario=null;
    }
   
    
   
    public tomarAsistencia() {
       
        // Jlabelque monta la huella
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        imagen1= new javax.swing.JLabel();
        imagen1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(imagen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, 130));
        pack();
        String path = "/imagenes/limpiar.jpeg";  
        URL url = this.getClass().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        imagen1.setIcon(icon);
        //
        initComponents();
        this.setTitle("Tomar Asistencia");
        antes= Activar.getBackground();
        this.setSize(410,400);
        this.setLocationRelativeTo(null);
        this.myNegocio = new Negocio();
       
    }

  

    
     public void ini() {
        this.lector=DPFPGlobal.getCaptureFactory().createCapture();
        lector.startCapture();
        JOptionPane.showMessageDialog(null,"EL huellero esta activado! ");         
    }
     
     
     
       
     
     
       protected void iniciarHuellero() {

           lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {      
                       if(mensaje==null){ 
                        JOptionPane.showMessageDialog(null,"El huellero esta "
                                + "Conectado.");
                        mensaje="lleno";
                       }
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                          JOptionPane.showMessageDialog(null,"El Sensor de "
                                  + "Huella Digital esta Desactivado o no Conectado");
                         
                          mensaje=null;
                    }
                    
                });
            }
        });
           
        lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        imagenHuella = CrearImagenHuella(e.getSample());
                        DibujarHuella(imagenHuella);
                          lector.stopCapture();  
                        try {

                            Usuario usu=EstaHuella(e.getSample());
                        
                                     if(usu==null){     
                            registrarHuella(e.getSample());
                                       }
                            if(usu!=null){
                                AsistenciaP a=registroSalidaHuella();
                                    if(a!=null){
                                     MarcacionSalida obj=new MarcacionSalida(usu,a);
                                     Activar.setBackground(antes);  
                                     activado=false;
                                     obj.setVisible(true);
                                     String path = "/imagenes/limpiar.jpeg";  
                                     URL url = this.getClass().getResource(path);
                                     ImageIcon icon = new ImageIcon(url);
                                     imagen1.setIcon(icon);
                                    }
                                    else{
                              JOptionPane.showMessageDialog(null,"¡Huellla encontrada!");
                              Activar.setBackground(antes); 
                              activado=false;
                              llamarFormulario();
                                    }
                            }
                            else{
                                          System.out.println("valor  variable de control :"+h.isCapturaFalsa());
                                          if(!h.isCapturaFalsa()){
                               JOptionPane.showMessageDialog(null,"¡Usuario no registrado!");
                                                  }
                               if(actual==null){
                               JOptionPane.showMessageDialog(null,"ponla huella de nuevo no la tomo");
                               }
                               else{
                               Activar.setBackground(antes);    
                               activado=false;    
                               llamarRegistrar();
                               }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(tomarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                 
                    }
                });
            }
        });
        
                lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // El dedo ha sido colocado sobre el Lector de Huella
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    //  EnviarTexto("El dedo ha sido quitado del Lector de Huella
                    }
                });
            }
        });

        lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    }
                });
            }
        });

    }
    
       private void registrarHuella(DPFPSample sample){
          h=new Huella();
       this.actual=h.generarHuella(sample);
       }
       
    private void llamarFormulario(){
      this.setVisible(false);
      Asistencia form=new Asistencia(this,this.usuario);
      form.setVisible(true);
    }
    
    public void cerrarFormulario(){
    this.dispose();
    }
    
  

     public void DibujarHuella(Image image){
        this.imagen1.setIcon(new ImageIcon(
                image.getScaledInstance(this.imagen1.getWidth(),this.imagen1.getHeight(), image.SCALE_DEFAULT)
        ));
        repaint();
    }
     
      public Image CrearImagenHuella(DPFPSample sample){
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }
    
      private Usuario EstaHuella(DPFPSample sample) throws SQLException{
        
       Huella h=new Huella();
       this.h=new Huella();
       
       ArrayList<Usuario> e=this.myNegocio.devolverUsuarios();

         for (Usuario usu : e) {
                 if(usu.getHuella()!=null){
                    
              if (h.verificarHuella(sample,usu.getHuella())) {
                  this.usuario=usu;
                     return usu;
              }
                        }
          }
         
             return null;
      }
     
      
      
     private  AsistenciaP  registroSalidaHuella() throws SQLException{
        return  this.myNegocio.registrarSalida(usuario.getCedula());
       
     }
     
     private void llamarRegistrar(){
         this.setVisible(false);
         registroUsuarios usu=new registroUsuarios(this);
         usu.setVisible(true);
     }
     
     
     
   
         
    
      
         
    private byte[] explitHuella(String huella){
        String [] cad=huella.split(",");
        byte [] res=new byte[cad.length];
          for(int i=0;i<cad.length;i++){
              res[i]=Byte.parseByte(cad[i]);
          }
          return res;       
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
        jLabel3 = new javax.swing.JLabel();
        txtCampo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        campo = new javax.swing.JComboBox<>();
        campo_Huella = new javax.swing.JLabel();
        Activar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("BIO-UFPS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Campo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));
        getContentPane().add(txtCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 118, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Huella");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        ingresar.setText("Ingresar");
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });
        getContentPane().add(ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, 114));

        campo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Cedula" }));
        campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoActionPerformed(evt);
            }
        });
        getContentPane().add(campo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        campo_Huella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sistemas.png"))); // NOI18N
        campo_Huella.setPreferredSize(new java.awt.Dimension(34, 54));
        getContentPane().add(campo_Huella, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 120, 130));

        Activar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/huella.png"))); // NOI18N
        Activar.setText("Activar");
        Activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivarActionPerformed(evt);
            }
        });
        getContentPane().add(Activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        String cadenaCampo=this.txtCampo.getText();
        this.txtCampo.setText("");
      
        
                if(cadenaCampo.isEmpty()){
                   JOptionPane.showMessageDialog(null,"Digite campo");
                   return;
                }
            
                    try {
        
                 if(this.campo.getSelectedIndex()==0){
                     this.usuario=this.myNegocio.devolverUsuarioCodigo(cadenaCampo);
                     
                               if(this.usuario!=null){
                     AsistenciaP a=this.registroSalidaHuella();
                        if(a!=null){
                                     MarcacionSalida obj=new MarcacionSalida(this.usuario,a);
                                     String path = "/imagenes/limpiar.jpeg";  
                                     URL url = this.getClass().getResource(path);
                                     ImageIcon icon = new ImageIcon(url);
                                     imagen1.setIcon(icon);
                                     obj.setVisible(true);
                        }else{
                         this.llamarFormulario();
                        }
                                     }else{
                                      
                                     this.imagenHuella=null;
                                     JOptionPane.showMessageDialog(null,"Usuario no registrado");
                                     
                                  this.llamarRegistrar();
                                     }
                 }
                 else{
                     
                     this.usuario=this.myNegocio.deovolverUsuarioCedula(cadenaCampo);
                              if(this.usuario!=null){
                     AsistenciaP a=this.registroSalidaHuella();
                        if(a!=null){
                                     MarcacionSalida obj=new MarcacionSalida(this.usuario,a);
                                     String path = "/imagenes/limpiar.jpeg";  
                                     URL url = this.getClass().getResource(path);
                                     ImageIcon icon = new ImageIcon(url);
                                     imagen1.setIcon(icon);
                                     obj.setVisible(true);
                        }
                        else{
                         this.llamarFormulario();
                        }
                                     }else{
     
                                      this.imagenHuella=null;
                                     JOptionPane.showMessageDialog(null,"Usuario no registrado");
                                  this.llamarRegistrar();
                                     }
                 }
                        
                 
                  } catch (SQLException ex) {
                        Logger.getLogger(tomarAsistencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
      
    }//GEN-LAST:event_ingresarActionPerformed

    private void ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivarActionPerformed
                  if(!this.activado){
                      ini();
                      this.Activar.setBackground(Color.red);
                      this.iniciarHuellero();
                      this.activado=true;
                  }else{
                   JOptionPane.showMessageDialog(null,"El hullero ya ha sido activado!");                   
                  }
    }//GEN-LAST:event_ActivarActionPerformed

    private void campoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoActionPerformed

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
            java.util.logging.Logger.getLogger(tomarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tomarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tomarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tomarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tomarAsistencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Activar;
    private javax.swing.JComboBox<String> campo;
    private javax.swing.JLabel campo_Huella;
    private javax.swing.JButton ingresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCampo;
    // End of variables declaration//GEN-END:variables
}
