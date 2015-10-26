package logica.controladores;

import ConexionBD.Base;
import java.time.Clock;

import java.util.Scanner;
import logica.entidades.*;
import logica.util.*;

/**
 *
 * @author MilagrosZea
 */
public class Prueba {

    public static void main(String[] args) {
        Lector lector = new Lector();
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Ingrese archivo");
        //String ruta = sc.nextLine();
//        Archivo a = new Archivo("E:\\TSB\\tsb_vocabulario\\res\\16082-8.txt");
//        Archivo b = new Archivo("E:\\TSB\\tsb_vocabulario\\res\\18166-8.txt");
//        lector.agregar_archivo(a);
//        lector.agregar_archivo(b);
//        SimpleList<Archivo> archi_proce = lector.procesar_archivos();
//        System.out.println(archi_proce.toString());
//        System.out.println(lector.toString());
        Base.clearDB();
        
    }
}
