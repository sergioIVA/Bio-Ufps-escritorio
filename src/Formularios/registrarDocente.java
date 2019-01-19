/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Negocio.Huella;
import Negocio.Negocio;
import java.io.IOException;
import java.io.PrintWriter;

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
import dto.Usuario;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author sergio
 */
public class registrarDocente extends javax.swing.JFrame implements comunJFrame {

    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private String mensaje;
    private byte[] actual = null;
    public boolean sw = false;
    registroUsuarios r;
    InputStream foto = null;
    BufferedImage f;

    public registrarDocente(registroUsuarios r) {

        initComponents();
         this.setTitle("Registrar Docente");
        this.setLocationRelativeTo(null);
        Image i = r.getMyAsistencia().getImagenHuella();
        if (i != null) {
            this.DibujarHuella(i);
        }
        this.actual = r.getMyAsistencia().getActual();
        this.r = r;
        //this.setSize(330,560);
        this.setSize(330, 545);

    }

    public void DibujarHuella(Image image) {
        this.imagen.setIcon(new ImageIcon(
                image.getScaledInstance(this.imagen.getWidth(), this.imagen.getHeight(), image.SCALE_DEFAULT)
        ));
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Docente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        Registrar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Registrar Docente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        Docente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Docente.setText("                   SIN FOTO");
        Docente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(Docente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 170, 140));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cedula:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));
        getContentPane().add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 197, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Nombre:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 197, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Codigo:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 197, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Tipo :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 50, 40));
        getContentPane().add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 200, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Telefono:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 60, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 197, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Huella:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        imagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 130, 110));

        Registrar.setText("Registrar");
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, -1, -1));

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        getContentPane().add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/camara2.png"))); // NOI18N
        jButton1.setText("Tomar");
        jButton1.setToolTipText("Toma la foto aca !");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed

        String cedula = this.txtCedula.getText();
        String nombre = this.txtNombre.getText();
        String codigo = this.txtCodigo.getText();
        String tipo = this.txtTipo.getText();
        String telefono = this.txtTelefono.getText();
        byte[] huella = null;

        if (cedula.isEmpty() || nombre.isEmpty() || codigo.isEmpty() || tipo.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "¡Registre todos los campos!");
            return;
        }

        //cedula no mas de 10 digitos
        if (cedula.length() > 10) {
            JOptionPane.showMessageDialog(null, "¡Solo puede tener 10 digitos la cedula!");
            return;
        }

        // Codigo  no mas de 10 
        if (codigo.length() > 10) {
            JOptionPane.showMessageDialog(null, "¡Limite de longitud de codigo hasta 10 !");
            return;
        }
        //telefono  no mas de 10 digitos 
        if (telefono.length() > 10) {
            JOptionPane.showMessageDialog(null, "¡Limite de longitud del telefono hasta 10!");
            return;
        }

        if (this.actual != null) {
            huella = actual;
        }
        Usuario e = new Usuario(cedula, codigo, huella, nombre, "Docente", tipo, null, null, telefono, foto);

        try {
            if (this.r.getMyAsistencia().getMyNegocio().registrarUsuario(e)) {
                JOptionPane.showMessageDialog(null, "¡registro Exitoso!");
                this.setVisible(false);
                if (f != null) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ImageIO.write(f, "jpg", new File("test.png"));
                    ImageIO.write((RenderedImage) f, "jpg", out);
                    InputStream in = new ByteArrayInputStream(out.toByteArray());
                    e.setFoto(in);
                }
                Asistencia form = new Asistencia(this.r.getMyAsistencia(), e);
                form.setVisible(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(registrarDocente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_RegistrarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.setVisible(false);
        this.r.setVisible(true);
    }//GEN-LAST:event_CancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Webcam obj = new Webcam(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Docente;
    private javax.swing.JButton Registrar;
    private javax.swing.JLabel imagen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void incluirFoto(BufferedImage image) throws IOException {
        this.f = image;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", new File("test.png"));
        ImageIO.write((RenderedImage) image, "jpg", out);
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        this.foto = in;
    }

    @Override
    public void incluirImagen(InputStream f) {
        try {
            Image I = ImageIO.read(f);
            Icon icono = new ImageIcon(I.getScaledInstance(Docente.getWidth(), Docente.getHeight(), Image.SCALE_DEFAULT));
            this.Docente.setIcon(icono);
        } catch (IOException ex) {
            Logger.getLogger(registroEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
