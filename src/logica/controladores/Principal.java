package logica.controladores;
import logica.entidades.*;

/**
 *
 * @author MilagrosZea
 */
public class Principal {
    public static void main(String[] args){
        Lector lector = new Lector();
        Archivo a = new Archivo("E:\\TSB\\tsb_vocabulario\\res\\16082-8.txt");       
        lector.leer(a);
        System.out.println(a.getPalabras().toString());        
    }
}
