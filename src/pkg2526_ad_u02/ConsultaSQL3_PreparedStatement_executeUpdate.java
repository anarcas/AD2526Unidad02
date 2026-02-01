/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2526_ad_u02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author anaranjo
 */
public class ConsultaSQL3_PreparedStatement_executeUpdate {

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
        Integer puntosAcumulados;
        String comunidad;
        int numActualizaciones;

        // Iniciación de variables
        driver = "com.mysql.cj.jdbc.Driver";
        connectionUrl = "jdbc:mysql://localhost/campeonato_atletismo?"
                + "user=root&password=root";
        sentenciaSQL = "UPDATE universidad SET puntos_acumulados = ? "
                + "WHERE comunidad = ?";
        puntosAcumulados = 150;
        comunidad = "Andalucía";

        try {

            // Cargar el driver de mysql
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(connectionUrl);

            // Preparación de la consulta
            PreparedStatement consulta = con.prepareStatement(sentenciaSQL);
            // Se envía parámetro de entrada para completar la consulta
            consulta.setInt(1, puntosAcumulados);
            consulta.setString(2, comunidad);
            // Se ejecuta la consulta
            numActualizaciones = consulta.executeUpdate();

            // Se imprime la lista de atletas por consola
            System.out.println("Puntos acumulados actualizados: " + numActualizaciones);

            // Cierre de recursos
            con.close();
            consulta.close();

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido instanciar la clase. Error: " + e);
        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexión. Error: " + e);
        }
    }

}
