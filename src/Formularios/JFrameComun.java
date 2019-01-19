/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.io.InputStream;
import javax.swing.JFrame;

/**
 *
 * @author ingeniero sergio
 */
public class JFrameComun extends JFrame {
    private   InputStream foto;
    
    public void agregarFoto(InputStream foto){
    this.foto=foto;
    }
    
}
