package Principal;

import logica.controladores.*;
import logica.entidades.*;
import logica.util.*;
import presentacion.Inicio;
import ConeccionBD.BasePalabra;

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
        Lector lectorArchivos = new Lector();
        //SimpleList<Archivo> archivos = null;       
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
                inicio.mostrarArchivosAProcesar(lectorArchivos.getColaArchivos());
                //System.out.println("final");
            }
            if (Inicio.PROCESAMIENTO_INICIADO) {
                //enviar esta SimpleList para grabar a la base de datos.
                lectorArchivos.procesar_archivos();

                //recargar la tabla de palabras (de la interfaz gráfica) desde 
                // la base de datos. El metodo devuelve un SimpleList<Palabras>
                SimpleList<Palabra> palabras = BasePalabra.obtenerTodasPalabras();
                inicio.mostrarPalabras(palabras);
            }
            if(Inicio.MOSTRAR_DETALLE){
                
            }
        }
    }
}
