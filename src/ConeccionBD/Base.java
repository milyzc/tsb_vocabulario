
package ConeccionBD;

import java.sql.*;

/**
 *
 * @author Emiliano
 */
public abstract class Base {
//Por Ahora todos los metodos funcionan con tipos de datos simples como parametros. 
//Cuando este resuelto lo de entidades funcionaran con los objetos como parametros.    
//Devuelve objeto Coneccion    

    public static Connection getConeccion() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Emiliano\\Desktop\\TSB - TPI - Repositorio\\tsb_vocabulario\\src\\DB.sqlite");

        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return conn;
    }
//Devuelve objeto Statement

    public static Statement getStatement(Connection c) {
        Statement s = null;
        try {
            s = c.createStatement();
        } catch (Exception ex) {
        }
        return s;
    }
//Devuelve True si el archivo seleccionado ya existe en la base de datos. False si no existe

    public static boolean existeArchivo(String ruta) {
        boolean e = false;
        Connection con = getConeccion();

        Statement s = getStatement(con);
        try {
            String rutaString = "\"" + ruta + "\"";
            String Query = "Select count (idArchivo) as Cantidad From Archivo Where ruta = " + rutaString;
            System.out.println(Query);
            ResultSet rs = s.executeQuery(Query);
            int existe = rs.getInt("Cantidad");
            if (existe == 0) {
                return e;
            } else {
                e = true;
            }
            rs.close();
            s.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getClass() + " - " + ex.getMessage());
        }

        return e;
    }
//Verifica que el archivo a insertar, no exista en la base de datos, en ese caso lo inserta y devuelve true. De otro modo devuelve false.

    public static boolean insertarArchivo(String nombre, String ruta, int id) {
        Connection con = getConeccion();
        Statement s = getStatement(con);
        if (!existeArchivo(ruta)) {
            try {
                String insert = "Insert into Archivo Values ( " + id + ", " + "\"" + nombre + "\"" + ", " + "\"" + ruta + "\"" + " )";
                s.execute(insert);
                return true;
            } catch (Exception e) {
                System.out.println(e.getClass() + " - " + e.getMessage());
            }
        }
        return false;
    }

//Devuelve todo lo que hay en la tabla Archivo;
    public static StringBuilder seleccionarTodoArchivo() {

        Connection con = getConeccion();
        Statement s = getStatement(con);
        StringBuilder sb = new StringBuilder();
        try {
            String Query = "Select * From Archivo";
            ResultSet rs = s.executeQuery(Query);
            while (rs.next()) {
                String row = "[ ";
                row += rs.getInt("idArchivo") + " - ";
                row += rs.getString("nombre") + " - ";
                row += rs.getString("ruta") + " ]";
                sb.append(row);
                sb.append("\n");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return sb;
    }

}
