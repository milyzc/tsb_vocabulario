package logica.entidades;

/**
 *
 * @author MilagrosZea
 */
public class Palabra implements Comparable {

    private String descripcion;
    private int cantidad;

    public Palabra(String descripcion) {
        this.descripcion = descripcion;
        this.cantidad = 0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public static String Palabra() {
        // conexión a base de datos
        return "";
    }

    /**
     * Compara dos palabras, devuelve 0 si son iguales y -1 si no lo son.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Palabra p = (Palabra) o;
            if (this.descripcion.compareTo(p.descripcion) == 0) {
                return 0;
            }
        } catch (UnsupportedOperationException u) {
            System.out.println(u.getMessage());
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Palabra{" + "descripcion=" + descripcion +", cantidad= "+ cantidad+ '}';
    }

}
