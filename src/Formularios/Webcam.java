/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author ingeniero sergio
 */
public class Webcam extends JFrame implements ActionListener, WebcamListener, WindowListener, Thread.UncaughtExceptionHandler, ItemListener, WebcamDiscoveryListener{
    
    private static final long serialVersionUID = 1L;

	private com.github.sarxos.webcam.Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
        private comunJFrame f;
        //private registroEstudiante e;
        
        private JButton boton=new JButton("tomar foto");
        
        
        public Webcam(comunJFrame f){
            this.f=f;
              
            com.github.sarxos.webcam.Webcam.addDiscoveryListener(this);

		setTitle("Java Webcam Capture POC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
                this.setLocationRelativeTo(null);

		addWindowListener(this);

		picker = new WebcamPicker();
		picker.addItemListener(this);

		webcam = picker.getSelectedWebcam();

		if (webcam == null) {
			System.out.println("No webcams found...");
			System.exit(1);
		}
                 // WebcamResolution.VGA.getSize()
		webcam.setViewSize(WebcamResolution.QVGA.getSize());
		webcam.addWebcamListener(Webcam.this);

		panel = new WebcamPanel(webcam,false);
		panel.setFPSDisplayed(true);
                
                /*CODIDO AGREGADO**/
               
                /**
                panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
               **/
               
               /** panel.setLocation(500,500);**/
             

		add(picker, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
                add(boton,BorderLayout.SOUTH);
                boton.addActionListener(this);

		pack();
		setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {
				panel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
        
        }
        

	@Override
	public void webcamOpen(WebcamEvent we) {
		System.out.println("webcam open");
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
            
		//System.out.println("webcam closed");
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		System.out.println("webcam disposed");
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
                  webcam.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("webcam viewer resumed");
		panel.resume();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("webcam viewer paused");
		panel.pause();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem() != webcam) {
			if (webcam != null) {

				panel.stop();

				remove(panel);
                          
                                
				webcam.removeWebcamListener(this);
				webcam.close();

				webcam = (com.github.sarxos.webcam.Webcam) e.getItem();
                                
                               // WebcamResolution.QQVGA.getSize();
			       webcam.setViewSize(WebcamResolution.VGA.getSize());
                                //webcam.setViewSize(WebcamResolution.QQVGA.getSize());
				webcam.addWebcamListener(this);

				System.out.println("selected " + webcam.getName());
                      
				panel = new WebcamPanel(webcam,false);
				panel.setFPSDisplayed(true);
                                 /*---- codigo nuevo */
                                panel.setDisplayDebugInfo(true);
                                panel.setImageSizeDisplayed(true);
                                panel.setMirrored(true);

				add(panel, BorderLayout.CENTER);
				pack();

				Thread t = new Thread() {

					@Override
					public void run() {
                                            /*---- codigo nuevo */
                                            panel.setDisplayDebugInfo(true);
                                            panel.setImageSizeDisplayed(true);
                                            panel.setMirrored(true);
						panel.start();
					}
				};
				t.setName("example-stoper");
				t.setDaemon(true);
				t.setUncaughtExceptionHandler(this);
				t.start();
			}
		}
	}

	@Override
	public void webcamFound(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.addItem(event.getWebcam());
		}
	}

	@Override
	public void webcamGone(WebcamDiscoveryEvent event) {
		if (picker != null) {
			picker.removeItem(event.getWebcam());
		}
	}

    @Override
    public void actionPerformed(ActionEvent e) {
     
        if(e.getSource()==boton){
        
            BufferedImage image=webcam.getImage();
            PreparedStatement ps = null;
 
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(image,"jpg",new File("test.png"));
                ImageIO.write((RenderedImage) image,"jpg", out);
                InputStream in = new ByteArrayInputStream(out.toByteArray());
                
                 this.f.incluirFoto(image);
                 this.f.incluirImagen(in);
               
              
            } catch (IOException ex) {
                Logger.getLogger(Webcam.class.getName()).log(Level.SEVERE, null, ex);
         
        }
            webcam.close();
            this.dispose();
}
    
    
}
}