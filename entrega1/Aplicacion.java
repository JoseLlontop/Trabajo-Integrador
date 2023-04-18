package curso.java.utn.Proyecto.entrega1;

import curso.java.utn.Proyecto.entrega1.dominio.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) throws IOException {

        Scanner leer = new Scanner(System.in);

        //Declaracion de la ronda unica:
        Ronda ronda = new Ronda(1);

        System.out.print("Ingrese la ruta del archivo pronosticos: ");
        String rutaPronosticos = leer.next();

        System.out.print("Ingrese la ruta del archivo resultados: ");
        String rutaResultados = leer.next();

        int puntaje = calcularPuntaje(rutaPronosticos, rutaResultados, ronda);

        System.out.println("Puntaje del jugados: " + puntaje);

    }

    public static int calcularPuntaje(String rutaPronosticos, String rutaResultados, Ronda ronda) throws IOException {

        boolean saltearPrimeraLinea = true;
        int cantidadAciertos = 0;


        // Carga de la ronda unica:

        for (String lineaResultados : Files.readAllLines(Paths.get(rutaResultados))) {

            if (!saltearPrimeraLinea) {

                String[] informacionResultados = lineaResultados.split(",");

                //Instancio las clases a partir del archivo resultados:

                Equipo equipo1 = new Equipo(informacionResultados[0], "");
                Equipo equipo2 = new Equipo(informacionResultados[3], "");

                Partido partido = new Partido(equipo1, equipo2, Integer.parseInt(informacionResultados[1]), Integer.parseInt(informacionResultados[2]));

                ronda.agregarPartido(partido);

            }

            saltearPrimeraLinea = false;
        }

        saltearPrimeraLinea = true;

        for (String lineaPronostico : Files.readAllLines(Paths.get(rutaPronosticos))) {

            Pronostico pronostico;

            if (!saltearPrimeraLinea) {

                String[] informacionPronostico = lineaPronostico.split(",");

                //Instancio las clases a partir del archivo pronstico:

                Equipo equipo1 = new Equipo(informacionPronostico[0], "");
                Equipo equipo2 = new Equipo(informacionPronostico[4], "");

                Partido partido = new Partido(equipo1, equipo2, 0, 0);

                if (informacionPronostico[1].equalsIgnoreCase("X")) {
                    pronostico = new Pronostico(partido, equipo1, ResultadoEnum.GANADOR);
                } else if (informacionPronostico[3].equalsIgnoreCase("X")) {
                    pronostico = new Pronostico(partido, equipo1, ResultadoEnum.PERDEDOR);
                } else {
                    pronostico = new Pronostico(partido, null, ResultadoEnum.EMPATE);
                }

                cantidadAciertos = cantidadAciertos + ronda.puntos(pronostico);

            }

            saltearPrimeraLinea = false;
        }

        return cantidadAciertos;
    }
}
