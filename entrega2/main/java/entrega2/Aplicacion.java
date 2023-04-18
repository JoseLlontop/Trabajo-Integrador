package entrega2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Aplicacion {
    public static void main(String[] args) throws IOException {

        Scanner leer = new Scanner(System.in);

        //Lista de rondas:
        List<Ronda> rondas = new ArrayList<>();

        //Declaracion de un map con los nombres de los jugadores con sus puntajes:
        Map<String, Integer> jugadores = new HashMap<>();

        System.out.print("Ingrese la ruta del archivo pronosticos: ");
        String rutaPronosticos = leer.next();

        System.out.print("Ingrese la ruta del archivo resultados: ");
        String rutaResultados = leer.next();


        identificarJugadores(rutaPronosticos, jugadores);

        calcularPuntaje(rutaPronosticos, rutaResultados, rondas, jugadores);

        for (String llave : jugadores.keySet()) {
            Integer valor = jugadores.get(llave);
            System.out.println(llave + ":" + valor);
        }

    }

    public static Map<String, Integer> calcularPuntaje(String rutaPronosticos, String rutaResultados, List<Ronda> rondas, Map<String, Integer> juagadores) throws IOException {

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

        saltearPrimeraLinea = true;

        for (String lineaPronostico : Files.readAllLines(Paths.get(rutaPronosticos))) {

            Pronostico pronostico;

            if (!saltearPrimeraLinea) {

                String[] informacionPronostico = lineaPronostico.split(",");

                for (Ronda ron : rondas) {

                    for (String llave : juagadores.keySet()) {

                        if (informacionPronostico[0].equalsIgnoreCase(llave)) {
                            //Instancio las clases a partir del archivo pronstico:

                            Equipo equipo1 = new Equipo(informacionPronostico[1], "");
                            Equipo equipo2 = new Equipo(informacionPronostico[5], "");

                            Partido partido = new Partido(equipo1, equipo2, 0, 0);

                            if (informacionPronostico[2].equalsIgnoreCase("X")) {
                                pronostico = new Pronostico(partido, equipo1, ResultadoEnum.GANADOR);
                            } else if (informacionPronostico[4].equalsIgnoreCase("X")) {
                                pronostico = new Pronostico(partido, equipo1, ResultadoEnum.PERDEDOR);
                            } else {
                                pronostico = new Pronostico(partido, null, ResultadoEnum.EMPATE);
                            }

                            juagadores.replace(llave, juagadores.get(llave) + ron.puntos(pronostico));

                        }

                    }

                }

            }

            saltearPrimeraLinea = false;
        }

        return juagadores;
    }

    public static void identificarJugadores(String rutaPronosticos, Map<String, Integer> jugadores) throws IOException {

        boolean saltearPrimeraLinea = true;

        for (String lineaPronostico : Files.readAllLines(Paths.get(rutaPronosticos))) {

            if (!saltearPrimeraLinea) {

                String[] informacionPronostico = lineaPronostico.split(",");

                jugadores.put(informacionPronostico[0], 0);

            }

            saltearPrimeraLinea = false;
        }

    }

}
