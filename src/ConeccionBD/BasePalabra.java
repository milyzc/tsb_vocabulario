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

import logica.entidades.Palabra;
import logica.util.*;

/**
 *
 * @author Emiliano
 */
public class BasePalabra extends Base {

    //Devuelve un Objeto Palabra si es que existe.
    public static Palabra obtenerPalabra(String p) {
        try {
            if (existePalabra(p)) {
                Connection con = getConeccion();
                Statement s = getStatement(con);
                String Query = "Select Palabra.palabra ,Palabra.contadorTotal ";
                Query += "From Palabra ";
                Query += "Where Palabra.palabra LIKE('" + p + "')";
                ResultSet rs = s.executeQuery(Query);
                Palabra pal = new Palabra(rs.getString("palabra"), rs.getInt("contadorTotal"));
                s.close();
                con.close();

                return pal;
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return null;

    }

//Devuelve true si existe la palabra, y false sino existe.
    public static boolean existePalabra(String p) {
        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            String query = "Select Count(palabra) as existe From Palabra where palabra Like '" + p + "'";

            ResultSet rs = s.executeQuery(query);
            if (rs.getInt("existe") != 0) {
                s.close();
                conn.close();
                return true;
            }

            s.close();
            conn.close();
        } catch (Exception e) {

        }
        return false;
    }

    public static SimpleList<String> obtenerArchivosPorPalabra(String p) {
        try {
            SimpleList<String> listaArchivos = new SimpleList<>();
            Connection conn = Base.getConeccion();
            Statement s = Base.getStatement(conn);
            String query = "Select Archivo.nombre From Archivo, PalabrasXArchivo Where PalabrasXArchivo.palabra Like ('" + p + "')";
            query += " and PalabrasXArchivo.idArchivo = Archivo.idArchivo";
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                String nodo = rs.getString("nombre");
                listaArchivos.addFirst(nodo);
            }
            s.close();
            conn.close();
            return listaArchivos;
        } catch (Exception e) {
        }
        return null;
    }

//Devuelve un String con cada una de las palabras que tenga a p como raiz, devuelve su contadorTotal y los archivos en los que aparece.
    public static SimpleList<Palabra> buscarPalabras(String p) {
        try {

            SimpleList<Palabra> listaPalabras = new SimpleList<>();
            String Query = "Select Palabra.palabra ,Palabra.contadorTotal ";
            Query += "From Palabra ";
            Query += "Where Palabra.palabra LIKE('" + p + "%')";
            Connection con = getConeccion();
            Statement s = getStatement(con);
            ResultSet rs = s.executeQuery(Query);
            while (rs.next()) {
                Palabra pal = new Palabra(rs.getString("palabra"), rs.getInt("contadorTotal"));
                listaPalabras.addFirst(pal);
            }
            s.close();
            con.close();

            return listaPalabras;
        } catch (Exception e) {
            System.out.println(e.getClass() + " - " + e.getMessage());
        }
        return null;
    }

    public static SimpleList<Palabra> obtenerTodasPalabras() {
        try {
            SimpleList<Palabra> slp = new SimpleList<>();
            String query = "Select * From Palabra";
            Connection con = getConeccion();
            Statement s = getStatement(con);
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                Palabra p = new Palabra(rs.getString("palabra"), rs.getInt("contadorTotal"));
                slp.addFirst(p);
            }
            s.close();
            con.close();
            return slp;

        } catch (Exception e) {
        }
        return null;
    }

    public static boolean insertarPalabra(int id, SimpleList<Palabra> slp) {
        try {
            Connection conn = getConeccion();
            Statement s = getStatement(conn);
            final boolean oldAutoCommit = s.getConnection().getAutoCommit();
            s.getConnection().setAutoCommit(false);
            try {
//                if (BaseArchivo.existeArchivo(id)) {

                    String insertPalabra = "Insert into Palabra Values ";

                    int tam = insertPalabra.length();
                    for (Palabra p : slp) {
                        String palabra = p.getDescripcion();
                        int con = p.getCantidad();
                        String insertPXA = "Insert into PalabrasXArchivo Values (" + id + ", " + "\"" + palabra + "\"" + ", " + con + " )";
                        s.execute(insertPXA);
                        if (!existePalabra(p.getDescripcion())) {

                            insertPalabra += " (" + "\"" + palabra + "\"" + ", " + con + " ),";

                        } else {

                            Palabra pal = obtenerPalabra(palabra);
                            int c = pal.getCantidad();
                            c += con;

                            String update = "Update Palabra Set contadorTotal = " + c + " Where palabra Like '" + palabra + "'";
                            s.executeUpdate(update);
                        }
                    }
                    if (tam != insertPalabra.length()) {
                        insertPalabra = insertPalabra.substring(0, insertPalabra.length() - 1);

                        s.executeUpdate(insertPalabra);
                    }
                //}
            } catch (Exception e) {
                System.out.println(e.getClass() + " - " + e.getMessage());
                s.getConnection().rollback();
            } finally {
                s.getConnection().commit();
                s.getConnection().setAutoCommit(oldAutoCommit);
                s.close();
                conn.close();

            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getClass() + " - " + ex.getMessage());
        }
        return false;
    }

}
