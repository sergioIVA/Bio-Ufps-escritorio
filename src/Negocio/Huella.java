package Negocio;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar garcia y manuel saravia.
 * Para mayor informacion de esta clase, consulte con los autores.
 */
public class Huella {
    
    private boolean capturaFalsa=false;
    private DPFPCapture lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPFeatureSet featuresinscripcion;
    private DPFPFeatureSet featuresverificacion;
    private DPFPTemplate template;
    private DPFPVerification verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPEnrollment reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    /**
     * Constructor por defecto de la clase huella.
     */
    public Huella() {
       
    }

    public boolean isCapturaFalsa() {
        return capturaFalsa;
    }

    
    
    /**
     * Metodo que devuelve la plantilla de la huella del usuario.
     * @return una plantilla creada de la huella del usuario.
     */
    public DPFPTemplate getTemplate() {
        return template;
    }

    /**
     * Metodo que reemplaza la plantilla que hay por otra nueva que se crea.
     * @param template plantilla nueva con la que se cambiara la que habia en 
     * el sistema.
     */
    public void setTemplate(DPFPTemplate template) {
        this.template = template;
    }

    /**
     * Metodo que dada una muestra genera un vector de tipo de dato Byte que
     * identifica la huella del usuario.
     * @param sample Muestra de la huella que se creara.
     * @return un vector de tipo de dato Byte con las caracteristicas de la 
     * huella.
     */
    public byte[] generarHuella(DPFPSample sample){
        byte[] resultado = null;
        try {
            featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
            featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

            reclutador.addFeatures(featuresinscripcion);
            reclutador.addFeatures(featuresinscripcion);
            reclutador.addFeatures(featuresinscripcion);
            reclutador.addFeatures(featuresinscripcion);


        } catch (DPFPImageQualityException ex) {
            JOptionPane.showMessageDialog(null, "Coloque bien la huella.");
        }catch(NullPointerException e){
            //JOptionPane.showMessageDialog(null, "Digite de manera correcta la huella.");
        } 
        finally {
           
            switch (reclutador.getTemplateStatus()) {
                case TEMPLATE_STATUS_READY:
                    lector.stopCapture();
                 //   JOptionPane.showMessageDialog(null, "Digite de nuevo su huella para corroborar que es usted");
                    
                    resultado = reclutador.getTemplate().serialize();
                    reclutador.clear();
                    
                    break;
                case TEMPLATE_STATUS_FAILED:
                    reclutador.clear();
               
                    break;
            }
           
        }
        return resultado;
    }

    /**
     * Metodo que dada una muestra y una huella ya generada de otro muestra, 
     * las compara y dice si son iguales o no.
     * @param sample Muestra que sera comparada.
     * @param antigua Huella representada en un vector de tipo de dato Byte 
     * que sera comparada con la muestra.
     * @return true si son iguales y false si no lo son.
     */
    public boolean verificarHuella(DPFPSample sample,byte [] antigua){
        
            featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
            featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
            try {
                reclutador.addFeatures(featuresinscripcion);
                reclutador.addFeatures(featuresinscripcion);
                reclutador.addFeatures(featuresinscripcion);
                reclutador.addFeatures(featuresinscripcion);
            } catch (DPFPImageQualityException ex) {
                JOptionPane.showMessageDialog(null, "error");
            }catch(java.lang.NullPointerException ex){
                capturaFalsa=true;
            JOptionPane.showMessageDialog(null,"No tomo la huella bien ... digitela de nuevo");
            }
            finally {
                switch (reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:
                        
                        setTemplate(DPFPGlobal.getTemplateFactory().createTemplate(antigua));
                        try {
                            DPFPVerificationResult result = verificador.verify(featuresverificacion, getTemplate());
                            if (result.isVerified()) {
                                //JOptionPane.showMessageDialog(null, "Las huellas son iguales");
                                 return true;
                               
                            }else{
                                //JOptionPane.showMessageDialog(null, "Las huellas NO son iguales");
                                return false;
                            }
                            
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "ERROR");
                        }

                        reclutador.clear();
                        
                        setTemplate(null);
                      
                        break;
                    case TEMPLATE_STATUS_FAILED:
                        reclutador.clear();
                        
                        setTemplate(null);
                      
                        break;
                }
            }
            return false;
        }
    
    /**
     * Metodo que extrae las caracteristicas dada una muestra y una resolucion
     * de la huella.
     * @param sample Muestra a la cual se le extraeran las caracteristicas.
     * @param purpose Resolucion con la que se tomara la muestra para devolver
     * las caracteristicas apropiadas.
     * @return las caracteristicas de la huella que se crearon dada la muestra
     * y la resolucion.
     */
    
    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }
}
