/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ingeniero sergio
 */
public interface comunJFrame {
    
    public void incluirFoto(BufferedImage image)throws IOException;
    public void incluirImagen(InputStream f);
}
