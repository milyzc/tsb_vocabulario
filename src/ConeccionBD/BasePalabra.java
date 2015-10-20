/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConeccionBD;
import ConeccionBD.BaseArchivo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static ConeccionBD.Base.getConeccion;
import static ConeccionBD.Base.getStatement;
import java.util.LinkedList;
import logica.entidades.Palabra;

/**
 *
 * @author Emiliano
 */
public class BasePalabra extends Base {
    
  

    //Devuelve un Objeto Palabra si es que existe.
public static Palabra obtenerPalabra(String p)
{
    try
    {   
        if(existePalabra(p)){
        Connection con = getConeccion();
        Statement s = getStatement(con);
        String Query = "Select Palabra.palabra ,Palabra.contadorTotal ";
        Query += "From Palabra ";
        Query += "Where Palabra.palabra LIKE('" +p + "')";        
        ResultSet rs = s.executeQuery(Query);
        Palabra pal = new Palabra(rs.getString("palabra"), rs.getInt("contadorTotal"));
        s.close();
        con.close();
        
        return pal;
        }
    }
    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
   return null;
    
}

//Devuelve true si existe la palabra, y false sino existe.
public static boolean existePalabra(String p)
{
     try {
        Connection conn = getConeccion();
        Statement s = getStatement(conn);
        String query = "Select Count(palabra) as existe From Palabra where palabra Like '" + p + "'";
        
        ResultSet rs = s.executeQuery(query);
        if(rs.getInt("existe") != 0)
        {   
            s.close();
            conn.close();
            return true;
        }
        
        s.close();
        conn.close();
        } catch (Exception e) 
        {
           
        }
return false;
}



//Inserta una palabra con su contador en la tabla PalabrasXArchivo, resiviendo como parametro el idArchivo. 
//Ademas Inserta o Actualiza(si la palabra ya existe) la palabra en la Tabla Palabra, con su contadorTotal.
//Devuelve true si se realiza la insercion. False en el caso contrario.
public static boolean insertarPalabra(int id,String palabra, int con)
{   
   
    try
    {
       if(BaseArchivo.existeArchivo(id) )
       { if(!existePalabra(palabra)){
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            String insertPalabra = "Insert into Palabra Values (" + "\"" + palabra +"\"" + ", "+ con+  " )" ;
            String insertPXA = "Insert into PalabrasXArchivo Values (" + id + ", " + "\"" + palabra +"\"" + ", "+ con+  " )";
            s.execute(insertPalabra);
            s.execute(insertPXA);
            s.close();
            conn.close();
            return true;
       }
       else
       {
           
            Palabra pal = obtenerPalabra(palabra);
            int c = pal.getCantidad();
            c += con;
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            String update = "Update Palabra Set contadorTotal = " + c + " Where palabra Like '" + palabra + "'"; 
            s.executeUpdate(update);
            String insertPXA = "Insert into PalabrasXArchivo Values (" + id + ", " + "\"" + palabra +"\"" + ", "+ con+  " )";
            s.execute(insertPXA);
            s.close();
            conn.close();
            return true;
       }
       }
    }
    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
    return false;
}



public static LinkedList<String> obtenerArchivosPorPalabra(String p)
{
    try
    {
        LinkedList<String> listaArchivos = new LinkedList<>();
        Connection conn = Base.getConeccion();
        Statement s = Base.getStatement(conn);
        String query = "Select Archivo.nombre From Archivo, PalabrasXArchivo Where PalabrasXArchivo.palabra Like ('" + p +"')";
        query += " and PalabrasXArchivo.idArchivo = Archivo.idArchivo";
        ResultSet rs = s.executeQuery(query);
        
        while(rs.next())
        {
           String nodo  = rs.getString("nombre") ;
           listaArchivos.add(nodo);
        }
        s.close();
        conn.close();
        return listaArchivos;
    }
    catch(Exception e){}
    return null;
}
    
//Devuelve un String con cada una de las palabras que tenga a p como raiz, devuelve su contadorTotal y los archivos en los que aparece.
   public static StringBuilder buscarPalabras(String p)
{
     try
    {   
        StringBuilder sb = new StringBuilder();
        LinkedList<Palabra> listaPalabras = new LinkedList<>();
        String Query = "Select Palabra.palabra ,Palabra.contadorTotal ";
        Query += "From Palabra ";
        Query += "Where Palabra.palabra LIKE('" +p + "%')";
        Connection con = getConeccion();
        Statement s = getStatement(con);
        ResultSet rs = s.executeQuery(Query);
        while(rs.next())
        {
            Palabra pal = new Palabra(rs.getString("palabra"),rs.getInt("contadorTotal"));
            listaPalabras.add(pal);            
        }          
        s.close();
        con.close();
        
        if(!listaPalabras.isEmpty()){
            for(Palabra pal:listaPalabras)
            {
                LinkedList<String> listaArchivos = obtenerArchivosPorPalabra(pal.getDescripcion());
                sb.append(pal.toString());
                sb.append(" ");
                sb.append(listaArchivos);
                sb.append("\n");       
            }
        } return sb;
    }
    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
   return null;
}





}
