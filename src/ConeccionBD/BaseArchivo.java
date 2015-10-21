/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConeccionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static ConeccionBD.Base.getConeccion;
import static ConeccionBD.Base.getStatement;

/**
 *
 * @author Emiliano
 */
public class BaseArchivo extends Base {

    //Devuelve True si el archivo seleccionado ya existe en la base de datos buscando por ruta. False si no existe
public static boolean existeArchivo(String ruta)
{   
    boolean e = false;
   
    try
    {
       Connection conn = getConeccion();
       Statement s = getStatement(conn);
       String rutaString = "\""+ruta+"\"";
       String Query = "Select count (idArchivo) as Cantidad From Archivo Where ruta = " + rutaString ; 
       
       ResultSet rs = s.executeQuery(Query);
       int existe = rs.getInt("Cantidad");
       if(existe != 0)
       {
          e = true;
       } 
    rs.close();
    s.close();
    conn.close();
    }
    catch(Exception ex){System.out.println(ex.getClass() + " - " + ex.getMessage());}
     
            
    return e;
}    

//Devuelve True si el archivo seleccionado ya existe en la base de datos Buscando por id. False si no existe
public static boolean existeArchivo(int id)
{   
    boolean e = false;
   
    try
    {
       Connection conn = getConeccion();
       Statement s = getStatement(conn);
       
       String Query = "Select count (idArchivo) as Cantidad From Archivo Where idArchivo =" +id ; 
       
       ResultSet rs = s.executeQuery(Query);
       int existe = rs.getInt("Cantidad");
       if(existe != 0)
       {
          e = true;
       } 
    rs.close();
    s.close();
    conn.close();
    }
    catch(Exception ex){System.out.println(ex.getClass() + " - " + ex.getMessage());}
     
            
    return e;
}    


//Verifica que el archivo a insertar, no exista en la base de datos, en ese caso lo inserta y devuelve true. De otro modo devuelve false.
public static boolean insertarArchivo (String nombre,String ruta) 
{
      
    try
    {
        if(!existeArchivo(ruta)){
        int ultimoID = obtenerUltimoIdArchivo();
        if(ultimoID == -1){
        ultimoID = 100;
        Connection conn = getConeccion();
        Statement s = getStatement(conn);
        String insert = "Insert into Archivo Values ( " + ultimoID + ", " + "\"" + nombre +"\"" + ", "+  "\"" + ruta +"\"" + " )";
        s.execute(insert);
        s.close(); 
        conn.close();
        return true;
        }else
        {
         Connection conn = getConeccion();
        Statement s = getStatement(conn);
        ultimoID++;
        String insert = "Insert into Archivo Values ( " + ultimoID + ", " + "\"" + nombre +"\"" + ", "+  "\"" + ruta +"\"" + " )";
        s.execute(insert);
        s.close(); 
        conn.close();
        return true;
            
        }
        }
    }
    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
       
    return false;
}

//Devuelve todo lo que hay en la tabla Archivo;
public static StringBuilder seleccionarTodoArchivo()
{
    try
    {   StringBuilder sb = new StringBuilder();
        
        Connection conn = getConeccion();
        Statement s = getStatement(conn);
        String Query = "Select * From Archivo";
        ResultSet rs = s.executeQuery(Query);
        while(rs.next())
        {
            sb.append("[ ");
            sb.append(rs.getInt("idArchivo"));
            sb.append(" ");
            sb.append(rs.getString("nombre"));
            sb.append(" ");
            sb.append(rs.getString("ruta"));
            sb.append("] ");
            sb.append("\n");
        }
        s.close(); 
        conn.close();
        return sb;
    }
    
    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
    return null;
}

//Devuelve el Id del Archivo que contenga la ruta pasada por parametro.
public static int obtenerIdArchivoPorRuta(String ruta)
{
    try
    {
        if(existeArchivo(ruta))
        {
        Connection conn = getConeccion();
        Statement s = getStatement(conn);
        String query = "Select Archivo.idArchivo FROM Archivo Where ruta Like '" + ruta + "'";
        System.out.println(query);
        ResultSet rs = s.executeQuery(query);
        int idArchivo = rs.getInt("idArchivo");
        s.close();
        conn.close();
        return idArchivo;
        }
    }
    catch(Exception e){}
    return -1;
}


//Devuelve todas las palabras que hay en un Archivo  @param id;
//public static ResultSet seleccionarTodasPalabrasDeUnArchivo(int id)
//{
//      try
//    {   
//        Connection con = getConeccion();
//        Statement s = getStatement(con);
//        String Query = "Select * From PalabrasXArchivo Where idArchivo = " + id;
//        ResultSet rs = s.executeQuery(Query);
//        s.close();
//        con.close();
//        return rs;
//    }
//    catch(Exception e){System.out.println(e.getClass() + " - " + e.getMessage());}
//    return null;
//}
//





//Obtiene el ultimo id de Archivo, para incrementarlo en el proximo insert;
public static int obtenerUltimoIdArchivo() {
        int ultimoID = -1;
    try {
        Connection conn = getConeccion();
        Statement s = getStatement(conn);
        String query = "Select Archivo.idArchivo From Archivo where idArchivo > 0";
        ResultSet rs = s.executeQuery(query);
        while(rs.next()){   
        ultimoID = rs.getInt("idArchivo");}
        s.close();
        conn.close();
        return ultimoID;
        } catch (Exception e) 
        {
           
        }
        return ultimoID;
    }

}
