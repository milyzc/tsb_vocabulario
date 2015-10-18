/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import logica.util.*;
import logica.entidades.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.LinkedList;

/**
 *
 * @author MilagrosZea
 */
public class Lector {

    private File f;
    private LinkedList<Archivo> colaArchivos;

    public Lector() {
        colaArchivos = new LinkedList<>();
    }

    /**
     * Procesa la cola de archivos devoliendo una lista con todos los archivos
     * procesados, si es que no hay archivos para procesar devuelve null;
     *
     * @return
     */
    public SimpleList<Archivo> procesar_archivos() {
        SimpleList<Archivo> archivos_procesados = null;
        if (this.hay_archivos()) {
            archivos_procesados = new SimpleList<>();
            for (Archivo archivo : colaArchivos) {
                this.leer(archivo);
                archivos_procesados.addFirst(archivo);
            }
        }
        return archivos_procesados;
    }

    /**
     * Agrega una archivo a la cola para ser procesado si éste no esta ya en
     * cola y devuelve true. Si ya esta en la cola, no lo agrega y devuelve
     * false.
     *
     * @param a
     * @return
     */
    public boolean agregar_archivo(Archivo a) {
        if (!colaArchivos.contains(a)) {
            colaArchivos.push(a);
            return true;
        }
        return false;
    }

    /**
     * Lee una archivo y retorna una colección de las palabras encontradas en el
     * sin ningún signo de puntuación. Si no retorna una lista vacia.
     *
     * @param ruta
     * @return
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

    private boolean hay_archivos() {
        if (!this.colaArchivos.isEmpty()) {
            return true;
        }
        return false;
    }
}
