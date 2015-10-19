/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.util;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author MilagrosZea
 */
public class Util {

    /**
     * Dada una palabra verifica que solo contenga letras y retorna true, de lo
     * contrario retorna false
     *
     * @param palabra
     * @return
     */
    
    //MILI LO MODIFIQUE UN POCO :). EMILIANO.
    public static boolean es_palabra(String palabra) {
        char[] c = palabra.toCharArray();
        for (int i = 0; i < c.length; i++) {
            
                try {
                    if (!Character.isLetter(c[i])) {return false;}
                } catch (Exception e) { e.printStackTrace(); }
            }
        
        return true;
    }
}
