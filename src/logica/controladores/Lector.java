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
import java.util.StringTokenizer;

/**
 *
 * @author MilagrosZea
 */
public class Lector {

    private File f;

    public Lector() {
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
            String linea = "";
            StringTokenizer st;
            Palabra palabra_pro;
            while ((linea = br.readLine()) != null) {
                st = new StringTokenizer(linea);
                String palabra_en_linea = "";
                while (st.hasMoreTokens()) {
                    palabra_en_linea = st.nextToken();
                    if(!(palabra_en_linea.contains("0")||palabra_en_linea.contains("1")||palabra_en_linea.contains("2")||
                       palabra_en_linea.contains("3")||palabra_en_linea.contains("4")||palabra_en_linea.contains("5")||
                       palabra_en_linea.contains("6")||palabra_en_linea.contains("7")
                     ||palabra_en_linea.contains("8")||palabra_en_linea.contains("9")||palabra_en_linea.contains("10")||palabra_en_linea.contains(".")))
                    {
                    palabra_en_linea = palabra_en_linea.replaceAll("\\p{Punct}", "");
                    palabra_pro = new Palabra(palabra_en_linea.toLowerCase());
                    archivo.agregar_palabra(palabra_pro);}
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }       
    }

    private void iniciarArchivo(String ruta) {
        f = new File(ruta);
    }

}
