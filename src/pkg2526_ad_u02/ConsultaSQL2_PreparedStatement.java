/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2526_ad_u02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author anaranjo
 */
public class ConsultaSQL2_PreparedStatement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Declaración de variables
        Connection con;
        String driver;
        String connectionUrl;
        String sentenciaSQL;
        String campo;
        ArrayList<String> listaAtletas;
        String nombreAtleta;
        String sexo;

        // Iniciación de variables
        driver = "com.mysql.cj.jdbc.Driver";
        connectionUrl = "jdbc:mysql://localhost/campeonato_atletismo?"
                + "user=root&password=root";
        sentenciaSQL = "SELECT nombre "
                + "FROM atleta "
                + " WHERE sexo = ? "
                + "ORDER BY nombre ASC";
        campo = "nombre";
        sexo = "F";
        listaAtletas = new ArrayList<>();

        try {

            // Cargar el driver de mysql
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(connectionUrl);

            // Preparación de la consulta
            PreparedStatement consulta = con.prepareStatement(sentenciaSQL);
            // Se envía parámetro de entrada para completar la consulta
            consulta.setString(1, sexo);
            // Se ejecuta la consulta
            ResultSet resultados = consulta.executeQuery();

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
