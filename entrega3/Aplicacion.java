package entrega3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

public class Aplicacion {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Scanner leer = new Scanner(System.in);

        Map<String, Boolean> aciertaRonda = new HashMap<>();
        Map<String, Boolean> aciertaFase = new HashMap<>();
        List<Ronda> rondas = new ArrayList<>();

        System.out.print("Ingrese la ruta de configuraciones del juego: ");
        String rutaConfiguraciones = leer.next();

        List<Integer> puntosConfiguraciones = configuracionesJuego(rutaConfiguraciones);

        System.out.print("\nIngrese el numero de la fase: ");
        int numeroDeFase = leer.nextInt();
        Fase fase = new Fase(numeroDeFase);

        System.out.print("\nIngrese la ruta del archivo resultados: ");
        String rutaResultados = leer.next();

        Map<String, Integer> jugadores = PronosticosDB.identificarJugadores();
        List<Pronostico> listaPronosticos = PronosticosDB.recuperarPronosticos();

        calcularPuntaje(listaPronosticos, rutaResultados, rondas, jugadores, fase, puntosConfiguraciones, aciertaRonda, aciertaFase);

        cargarFase(rondas, fase);

        System.out.println("\n-------Resultados de los aciertos de la fase numero " + fase.getNumero() + "--------");
        for (String llave : jugadores.keySet()) {
            Integer valor = jugadores.get(llave);
            System.out.println(llave + ":" + valor);
        }

    }

    public static Map<String, Integer> calcularPuntaje(List<Pronostico> listaPronosticos, String rutaResultados, List<Ronda> rondas,
                                                       Map<String, Integer> jugadores, Fase fase, List<Integer> configuraciones, Map<String, Boolean> aciertaRonda, Map<String, Boolean> aciertaFase ) throws IOException {

        int puntosGana = configuraciones.get(0);
        int puntoExtraRonda = configuraciones.get(1);

        boolean saltearPrimeraLinea = true;

        //Ronda inicial por lo menos va a contener una:
        Ronda ronda1 = new Ronda(1);
        rondas.add(ronda1);

        try {

            for (String lineaResultados : Files.readAllLines(Paths.get(rutaResultados))) {

                if (!saltearPrimeraLinea) {

                    String[] informacionResultados = lineaResultados.split(",");
                    boolean guardado = false;

                    //Instancio las clases a partir del archivo resultados:
                    Ronda ronda = new Ronda(Integer.parseInt(informacionResultados[0]));

                    for (Ronda ron : rondas) {
                        if (ron.getNro() == ronda.getNro()) {

                            Equipo equipo1 = new Equipo(informacionResultados[1], "");
                            Equipo equipo2 = new Equipo(informacionResultados[4], "");

                            Partido partido = new Partido(equipo1, equipo2, Integer.parseInt(informacionResultados[2]), Integer.parseInt(informacionResultados[3]));

                            ron.agregarPartido(partido);

                            guardado = true;
                        }
                    }

                    if (!guardado) {
                        Equipo equipo1 = new Equipo(informacionResultados[1], "");
                        Equipo equipo2 = new Equipo(informacionResultados[4], "");

                        Partido partido = new Partido(equipo1, equipo2, Integer.parseInt(informacionResultados[2]), Integer.parseInt(informacionResultados[3]));

                        ronda.agregarPartido(partido);

                        rondas.add(ronda);
                    }
                }

                saltearPrimeraLinea = false;
            }


        } catch (NumberFormatException e) {
            System.out.println("El campo numero de ronda, cantidad de goles 1 y cantidad de goles 2 deben ser enteros!");
        } catch (Exception e) {
            System.out.println("La cantidad de campos de la tabla es incorrecto!");
        }

        for (int i=0; i<rondas.size(); i++) {

            for (Pronostico pronostico : listaPronosticos)  {

                for (String llave : jugadores.keySet()) {

                    if (pronostico.getJugador().equalsIgnoreCase(llave)) {

                        int puntoGanado = rondas.get(i).puntos(pronostico, puntosGana);
                        jugadores.replace(llave, jugadores.get(llave) + puntoGanado);

                        aciertaRonda.put(llave, true);

                        /*if (puntoGanado == 0 && contienePartido(rondas.get(i), pronostico)) {
                            aciertaFase.put(llave, false);
                        }*/

                        if (puntoGanado == 0) {
                            aciertaRonda.replace(llave, false);
                        }
                    }
                }
            }

            for (String llave : aciertaRonda.keySet()) {
                if (aciertaRonda.get(llave)) {
                    jugadores.replace(llave, jugadores.get(llave) + puntoExtraRonda);
                }

            }

            aciertaRonda.clear();

        }

        return jugadores;
    }

    public static List<Integer> configuracionesJuego(String rutaConfiguraciones) {

        List<Integer> configuracionPuntos = new ArrayList<>();

        boolean saltearPrimeraLinea = true;

        try {

            for (String lineaResultados : Files.readAllLines(Paths.get(rutaConfiguraciones))) {

                if (!saltearPrimeraLinea) {

                    String[] informacionConfiguraciones = lineaResultados.split(",");

                    configuracionPuntos.add(Integer.parseInt(informacionConfiguraciones[0]));
                    configuracionPuntos.add(Integer.parseInt(informacionConfiguraciones[1]));
                    configuracionPuntos.add(Integer.parseInt(informacionConfiguraciones[2]));
                }

                saltearPrimeraLinea = false;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return configuracionPuntos;
    }

    public static void cargarFase(List<Ronda> rondasJuego, Fase fase) {

        for (Ronda ronda: rondasJuego) {
            fase.addRonda(ronda);
        }
    }

    public static boolean contienePartido(Ronda ronda, Pronostico pronostico) {

        for (Partido partido: ronda.getPartidos()) {
            if (partido.getEquipo1().equals(pronostico.getPartido().getEquipo1())) {
                return true;
            }
        }

        return false;

    }
}
