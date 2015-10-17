
package logica.entidades;

/**
 *
 * @author MilagrosZea
 */
public class Palabra implements Comparable{
    String descripcion;

    public Palabra(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String Palabra(){
        // conexión a base de datos
        return "";
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Palabra{" + "descripcion=" + descripcion + '}';
    }
        
}
