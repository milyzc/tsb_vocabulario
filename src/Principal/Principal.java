package Principal;

import logica.controladores.*;
import logica.entidades.*;
import logica.util.*;
import presentacion.Inicio;

import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Controlador Principal.
 *
 * @author Tyncho
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try {
//            // Set cross-platform Java L&F (also called "Metal")
//
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (UnsupportedLookAndFeelException e) {
//            // handle exception
//        } catch (ClassNotFoundException e) {
//            // handle exception
//        } catch (InstantiationException e) {
//            // handle exception
//        } catch (IllegalAccessException e) {
//            // handle exception
//        }        
        Lector lectorArchivos = new Lector();
        SimpleList<Archivo> archivosProcesados = null;
        Archivo archivo = null;
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        while (true) {
            System.out.println("aca");
            if (Inicio.NUEVOS_ARCHIVOS) {
                System.out.println("entró");
                for (File archivoSeleccionado : inicio.getArchivosALeer()) {
                    lectorArchivos.agregar_archivo(new Archivo(archivoSeleccionado));
                }
                //System.out.println(lectorArchivos.toString());
                inicio.mostrarArchivos(lectorArchivos.getColaArchivos());
                //System.out.println("final");
            }
            if (Inicio.PROCESAMIENTO_INICIADO) {
                System.out.println(lectorArchivos.procesar_archivos());

            }
        }
    }
}
