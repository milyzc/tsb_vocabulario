package logica.controladores;

import ConexionBD.Base;
import ConexionBD.BaseArchivo;
import ConexionBD.BasePalabra;
import logica.util.*;
import logica.entidades.*;
import presentacion.Inicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.StringTokenizer;

/**
 *
 * @author Milagros Zea Cárdenas
 * @version 1.0
 */
public class Lector {

    private File f;
    private SimpleList<Archivo> colaArchivos;
    private Inicio inicio;

    public Lector() {
        colaArchivos = new SimpleList<>();
        inicio = null;
    }

    public SimpleList<Archivo> getColaArchivos() {
        return colaArchivos;
    }

    public void setColaArchivos(SimpleList<Archivo> archivos) {
        colaArchivos = archivos;
    }

    public Inicio getInicio() {
        return inicio;
    }

    public void setInicio(Inicio inicio) {
        this.inicio = inicio;
    }

    /**
     * Procesa un archivo insertandolo en la BD e inserta las palabras
     * encontradas en la BD
     *
     * @param archivo
     */
    public boolean procesar_archivos(Archivo archivo) {
        if (BaseArchivo.insertarArchivo(archivo)) {
            archivo.setIdArchivo(BaseArchivo.obtenerIdArchivoPorRuta(archivo.getRuta()));

            leer(archivo);

            BasePalabra.insertarPalabra(archivo.getIdArchivo(), archivo.getPalabras());

            return true;
        }
        return false;
    }

    public void vaciarCola() {
        colaArchivos.clear();
    }

    /**
     * Procesa la cola de archivos devoliendo una lista con todos los archivos
     * procesados, si es que no hay archivos para procesar devuelve null;
     *
     * @return
     */
//    public SimpleList<Archivo> procesar_archivos() {
//        SimpleList<Archivo> archivos_procesados = null;
//        if (this.hay_archivos()) {
//            archivos_procesados = new SimpleList<>();
//            for (Archivo archivo : colaArchivos) {
//                this.leer(archivo);
//                archivos_procesados.addFirst(archivo);
//            }
//        }
//        return archivos_procesados;
//    }
    public void agregar_archivos(Archivo[] archivos) {
        for (Archivo archivo : archivos) {
            agregar_archivo(archivo);
        }
    }

    /**
     * Devuelve true si el archivo esta en a cola de archivos, de lo contrario
     * devuelve false.
     *
     * @param a
     * @return
     */
    public boolean esta_en_cola(Archivo a) {
        return colaArchivos.contains(a);
    }

    /**
     * Agrega un archivo a la cola de archivos
     *
     * @param a
     */
    public boolean agregar_archivo(Archivo a) {
        if (!colaArchivos.contains(a)) {
            colaArchivos.addFirst(a);
            return true;
        }
        return false;
    }

    /**
     * Lee una archivo y retorna una colección de las palabras encontradas en el
     * sin ningún signo de puntuación. Si no retorna una lista vacia.
     *
     * @param archivo
     */
    public void leer(Archivo archivo) {
        //pensar
        this.iniciarArchivo(archivo.getRuta());
        //SimpleList<Palabra> palabras_pro = new SimpleList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = "", palabra_en_linea = "";
            StringTokenizer st;
            Palabra palabra_pro;
            while ((linea = br.readLine()) != null) {
                st = new StringTokenizer(linea);
                while (st.hasMoreTokens()) {
                    palabra_en_linea = st.nextToken();
                    palabra_en_linea = palabra_en_linea.replaceAll("\\p{Punct}", "");
                    if (Util.es_palabra(palabra_en_linea)) {
                        palabra_pro = new Palabra(palabra_en_linea.toLowerCase());
                        archivo.agregar_palabra(palabra_pro);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void iniciarArchivo(String ruta) {
        f = new File(ruta);
    }

    @Override
    public String toString() {
        return "Lector{" + "f=" + f + ", colaArchivos=" + colaArchivos.toString() + '}';
    }

    public boolean hay_archivos() {
        if (!this.colaArchivos.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        Lector lectorArchivos = new Lector();
//        File f = new File("p.txt");
//        try {
//            System.out.print(f.createNewFile());
//            String rutaBD = f.getAbsolutePath();
//            //System.out.print(rutaBD.);            
//            rutaBD = rutaBD.substring(0, rutaBD.length() - 6);
//            System.out.println(rutaBD);
//            System.out.print(rutaBD + "\\tsb_vocabulario\\BD\\DB.sqlite");
//            //Base.stringConeccion = rutaBD + "\\tsb_vocabulario\\BD\\DB.sqlite";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //SimpleList<Archivo> archivos = null;       
        Base.clearDB();
        Inicio inicio = new Inicio(lectorArchivos);
        lectorArchivos.setInicio(inicio);
        inicio.setVisible(true);

    }
}
