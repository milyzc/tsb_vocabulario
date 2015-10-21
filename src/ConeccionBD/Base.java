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

//Borra todo de las dos tablas de la base de datos.
    public static void clearDB() {
        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            s.execute("Delete  From Archivo Where idArchivo > 0");
            s.execute("Delete  From PalabrasXArchivo Where idArchivo > 0");
            s.execute("Delete From Palabra");
            System.out.println("Borrado Exitoso");
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }

    }
}
