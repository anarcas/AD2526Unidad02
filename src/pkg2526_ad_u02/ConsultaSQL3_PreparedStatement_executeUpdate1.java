/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2526_ad_u02;

import java.sql.CallableStatement;
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
public class ConsultaSQL3_PreparedStatement_executeUpdate1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Declaración de variables
        Connection con;
        String driver;
        String connectionUrl;
        int numActualizaciones;
        String mensajeSalida;
        String posicion;
        Integer posicionAtleta;
        String llamadaProcedimiento;
        Integer numAtletas;

        // Iniciación de variables
        driver = "com.mysql.cj.jdbc.Driver";
        connectionUrl = "jdbc:mysql://localhost/campeonato_atletismo?"
                + "user=root&password=root";
        posicion="2";
        posicionAtleta=Integer.parseInt(posicion);
        llamadaProcedimiento = "{ call atletas_posicion(?, ?) }";

        try {

            // Cargar el driver de mysql
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(connectionUrl);

            // Preparación de la consulta
            CallableStatement procedimiento = con.prepareCall(llamadaProcedimiento);
            // Se envía parámetro de entrada para completar la consulta
             // Se ejecuta el llamadaProcedimiento
            procedimiento.setInt(1, posicionAtleta);
            procedimiento.registerOutParameter(2, java.sql.Types.INTEGER);
            procedimiento.execute();
            // Se obtiene el resultado
            numAtletas=procedimiento.getInt(2);
            // Se muestra el resultado por pantalla
            mensajeSalida=String.format("El número de atletas que han acabado en la posición nº %d = %d.", posicionAtleta,numAtletas);
            System.out.println(mensajeSalida);

            // Cierre de recursos
            con.close();
            procedimiento.close();

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido instanciar la clase. Error: " + e);
        } catch (SQLException e) {
            System.out.println("No se ha podido establecer la conexión. Error: " + e);
        }
    }

}
