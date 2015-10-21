/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConeccionBD;

import java.sql.*;

/**
 *
 * @author Emiliano
 */
public abstract class Base {

    private final static String stringConeccion = "jdbc:sqlite:C:\\Users\\Emiliano\\Desktop\\TSB - TPI - Repositorio\\tsb_vocabulario\\src\\BD\\DB.sqlite";        

//Por Ahora todos los metodos funcionan con tipos de datos simples como parametros. 
//Cuando este resuelto lo de entidades funcionaran con los objetos como parametros.    
//Devuelve objeto Coneccion    
    public static Connection getConeccion() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(stringConeccion);

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
//Devuelve True si el archivo seleccionado ya existe en la base de datos buscando por ruta. False si no existe

    public static boolean existeArchivo(String ruta) {
        boolean e = false;

        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            String rutaString = "\"" + ruta + "\"";
            String Query = "Select count (idArchivo) as Cantidad From Archivo Where ruta = " + rutaString;

            ResultSet rs = s.executeQuery(Query);
            int existe = rs.getInt("Cantidad");
            if (existe != 0) {
                e = true;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getClass() + " - " + ex.getMessage());
        }

        return e;
    }

//Devuelve True si el archivo seleccionado ya existe en la base de datos Buscando por id. False si no existe
    public static boolean existeArchivo(int id) {
        boolean e = false;

        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);

            String Query = "Select count (idArchivo) as Cantidad From Archivo Where idArchivo =" + id;

            ResultSet rs = s.executeQuery(Query);
            int existe = rs.getInt("Cantidad");
            if (existe != 0) {
                e = true;
            }
            rs.close();
            s.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getClass() + " - " + ex.getMessage());
        }

        return e;
    }

//Verifica que el archivo a insertar, no exista en la base de datos, en ese caso lo inserta y devuelve true. De otro modo devuelve false.
    public static boolean insertarArchivo(String nombre, String ruta, int id) {

        if (!existeArchivo(ruta)) {
            try {
                Connection conn = getConeccion();
                Statement s = getStatement(conn);
                String insert = "Insert into Archivo Values ( " + id + ", " + "\"" + nombre + "\"" + ", " + "\"" + ruta + "\"" + " )";
                s.execute(insert);
                s.close();
                conn.close();
                return true;

            } catch (Exception e) {
                System.out.println(e.getClass() + " - " + e.getMessage());
            }

        }
        return false;
    }

//Devuelve todo lo que hay en la tabla Archivo;
    public static StringBuilder seleccionarTodoArchivo() {

        StringBuilder sb = new StringBuilder();
        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            String Query = "Select * From Archivo";
            ResultSet rs = s.executeQuery(Query);
            while (rs.next()) {
                String row = "[ ";
                row += rs.getInt(1) + " - ";
                row += rs.getString(2) + " - ";
                row += rs.getString(3) + " ]";
                sb.append(row);
                sb.append("\n");
            }
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return sb;
    }
//Inserta una palabra con su contador en la tabla PalabrasXArchivo, resiviendo como parametro el idArchivo. 
//Devuelve true si se realiza la insercion. False en el caso contrario.

    public static boolean insertarPalabra(int id, String palabra, int con) {
        boolean insert = false;

        try {
            if (existeArchivo(id)) {
                Connection conn = getConeccion();
                Statement s = getStatement(conn);
                String Query = "Insert into PalabrasXArchivo Values (" + id + ", " + "\"" + palabra + "\"" + ", " + con + " )";
                s.execute(Query);
                insert = true;
                s.close();
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return insert;
    }

//Devuelve todo lo que hay en la tabla Archivo;
    public static StringBuilder seleccionarTodasPalabrasDeUnArchivo(int id) {
        StringBuilder sb = new StringBuilder();
        try {
            Connection con = getConeccion();
            Statement s = getStatement(con);
            String Query = "Select * From PalabrasXArchivo Where idArchivo = " + id;
            ResultSet rs = s.executeQuery(Query);
            while (rs.next()) {
                String row = "[ ";
                row += rs.getInt(1) + " - ";
                row += rs.getString(2) + " - ";
                row += rs.getString(3) + " ]";
                sb.append(row);
                sb.append("\n");
            }
            s.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return sb;
    }

//Busca por palabras o por raiz , Devuelve los archivos en los que esta y la frecuencia con que se repite
    public static StringBuilder buscarPalabra(String p) {
        try {
            Connection con = getConeccion();
            Statement s = getStatement(con);
            String Query = "Select Archivo.nombre,PalabrasXArchivo.palabra ,PalabrasXArchivo.contador ";
            Query += "From PalabrasXArchivo,Archivo ";
            Query += "Where PalabrasXArchivo.palabra LIKE('" + p + "%')";
            Query += " and PalabrasXArchivo.idArchivo = Archivo.idArchivo";
            ResultSet rs = s.executeQuery(Query);
            StringBuilder sb = new StringBuilder();
            String etiqueta = "[Palabra o raiz: " + p + " ] " + "\n";
            sb.append(etiqueta);
            while (rs.next()) {
                String row = "[ ";
                row += rs.getString(1) + " - ";
                row += rs.getString(2) + " - ";
                row += rs.getInt(3) + " ] ";

                sb.append(row);
                sb.append("\n");
            }

            s.close();
            con.close();

            return sb;
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return null;
    }

//Borra todo de las dos tablas de la base de datos.
    public static void clearDB() {
        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            s.execute("Delete  From Archivo Where idArchivo > 0");
            s.execute("Delete  From PalabrasXArchivo Where idArchivo > 0");
            System.out.println("Borrado Exitoso");
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
    }
    
}
