package logica.entidades;

import logica.util.*;

/**
 *
 * @author MilagrosZea
 */
public class Archivo {

    private int idArchivo;
    private String ruta;
    private String nombre;
    private SimpleList<Palabra> palabras;

    public Archivo(String ruta) {
        this.ruta = ruta;
        palabras = new SimpleList<>();
    }

    public boolean existe_palabra(Palabra p) {
        return palabras.contains(p);
    }

    public void agregar_palabra(Palabra p) {
        int posicion = palabras.indexOf(p);
        if (posicion != -1) {
            p.setCantidad(palabras.get(posicion).getCantidad() + 1);
            palabras.set(posicion, p);
        } else {
            agregar_nueva_palabra(p);
        }
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    private void agregar_nueva_palabra(Palabra p) {
        p.setCantidad(1);
        palabras.addFirst(p);
    }
}
