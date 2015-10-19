package logica.util;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Milagros Zea Cárdenas
 * @version 1.0
 */
public class Util {

    /**
     * Dada una palabra verifica que solo contenga letras y retorna true, de lo
     * contrario retorna false
     *
     * @param palabra
     * @return
     */
    public static boolean es_palabra(String palabra) {
        char[] c = palabra.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (!Character.isLetter(c[i])) {
                try {
                    if (Character.isDigit(c[i])) {
                        //System.out.println(palabra);
                        return false;
                    }
//                    if (Character.isLetter(c[i - 1]) && Character.isLetter(c[i + 1])) {
//                        System.out.println(palabra);
//                        return false;
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
