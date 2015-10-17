package logica.entidades;

import logica.util.*;

/**
 *
 * @author MilagrosZea
 */
public class Archivo {
    private int idArchivo;
    private String nombre;
    private SimpleList<Palabra> palabras; 

    public Archivo() {
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        

    public SimpleList<Palabra> getPalabras() {
        return palabras;
    }

    public void setPalabras(SimpleList<Palabra> palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        return "Archivo{" + "idArchivo=" + idArchivo + ", nombre=" + nombre + ", palabras=" + palabras.toString() + '}';
    }
    
    
    
}
