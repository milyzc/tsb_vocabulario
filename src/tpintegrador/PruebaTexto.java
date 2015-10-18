/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpintegrador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author Tyncho
 */
public class PruebaTexto {

        static File f = new File ("E:\\TSB\\tsb_vocabulario\\res\\16082-8.txt");
        
        static FileReader in;
        static BufferedReader ifile;
    public static void main(String[] args) {
        System.out.println(f.getPath());
            try
                {
                    in = new FileReader(f);
                    ifile = new BufferedReader(in); 
                    String linea = ifile.readLine();
                    int numLinea=0;
                    while(linea != null)
                    {
                        int numTokens = 0;
                        StringTokenizer st = new StringTokenizer (linea);
                        String lineastr="";
                        // bucle por todas las palabras
                        while (st.hasMoreTokens())
                        {
                            lineastr = st.nextToken().replaceAll("\\p{Punct}", "");
                            numTokens++;
                            
                            System.out.println ("Linea "+ numLinea+" Palabra " + numTokens + " es: " + lineastr);
                        }
                        
                        linea = ifile.readLine();
                        numLinea++;
                    }
            }catch(Exception e){
                e.printStackTrace();
            }

    }
}
    