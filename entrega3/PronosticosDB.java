package entrega3;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PronosticosDB {

    public static List<Pronostico> recuperarPronosticos() throws ClassNotFoundException, SQLException {

        //Lista de pronosticos vacio:
        List<Pronostico> listaPronosticos = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpresaPronosticosBD", "root", "");

        Statement consulta = conexion.createStatement();

        ResultSet resultado = consulta.executeQuery("SELECT * FROM Pronostico");

        while (resultado.next()) {

            Pronostico pronostico;

            Equipo equipo1 = new Equipo(resultado.getString(3), "");
            Equipo equipo2 = new Equipo(resultado.getString(7), "");

            Partido partido = new Partido(equipo1, equipo2, 0, 0);

            if (resultado.getString(4).equalsIgnoreCase("X")) {
                pronostico = new Pronostico( partido, equipo1, ResultadoEnum.GANADOR);
            } else if (resultado.getString(6).equalsIgnoreCase("X")) {
                pronostico = new Pronostico(partido, equipo1, ResultadoEnum.PERDEDOR);
            } else {
                pronostico = new Pronostico(partido, null, ResultadoEnum.EMPATE);
            }

            pronostico.setJugador(resultado.getString(2));

            listaPronosticos.add(pronostico);
        }

        conexion.close();

        return listaPronosticos;

    }

    public static Map<String, Integer> identificarJugadores() throws ClassNotFoundException, SQLException {

        Map<String, Integer> jugadores = new HashMap<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmpresaPronosticosBD", "root", "");

        Statement consulta = conexion.createStatement();

        ResultSet resultado = consulta.executeQuery("SELECT * FROM Pronostico");

        while (resultado.next()) {
            jugadores.put(resultado.getString(2), 0);
        }

        conexion.close();

        return jugadores;
    }
}
