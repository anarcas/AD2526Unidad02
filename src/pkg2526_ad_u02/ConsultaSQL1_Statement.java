/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2526_ad_u02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author anaranjo
 */
public class ConsultaSQL1_Statement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Connection con;

        // Declaración de variables
        String driver;
        String connectionUrl;
        String sentenciaSQL;
        String campo;
        ArrayList<String> listaAtletas;
        String nombreAtleta;

        // Iniciación de variables
        driver = "com.mysql.cj.jdbc.Driver";
        connectionUrl = "jdbc:mysql://localhost/campeonato_atletismo?"
                + "user=root&password=root";
        sentenciaSQL = "SELECT DISTINCT nombre "
                + "FROM atleta "
                + "ORDER BY nombre ASC";
        campo = "nombre";
        listaAtletas = new ArrayList<>();

        try {

            // Cargar el driver de mysql
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(connectionUrl);

            // Preparación de la consulta
            Statement consulta = con.createStatement();
            // Se ejecuta la consulta
            ResultSet resultados = consulta.executeQuery(sentenciaSQL);

            // Se obtienen los resultados
            while (resultados.next()) {
                nombreAtleta = resultados.getString(campo);
                listaAtletas.add(nombreAtleta);
            }

            // Se imprime la lista de atletas por consola
            System.out.println("Lista de atletas: " + listaAtletas.toString());

            // Cierre de recursos
            con.close();
            consulta.close();
            resultados.close();

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido instanciar la clase. Error: " + e);
        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexión. Error: " + e);
        }
    }

}
