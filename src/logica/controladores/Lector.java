/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica.controladores;
import java.io.File; 
import java.io.FileReader; 
import java.io.BufferedReader; 

/**
 *
 * @author MilagrosZea
 */
public class Lector {
    private File f;
    private FileReader fr;
    private BufferedReader br;
    
    public Lector(){        
    }
    
    public void leer(String ruta){
        //pensar
    }
    private void iniciarArchivo(String ruta){
        f = new File(ruta);        
    }
    
}
