package entrega2Test;


import static entrega2.Aplicacion.*;

import entrega2.Ronda;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class entrega2Test {

    @Test
    public void calcularPuntajePersona() throws IOException {
        Map<String, Integer> jugadores = new HashMap<>();
        List<Ronda> rondas = new ArrayList<>();

        //Valores definidos
        String rutaPronostico = "/home/jose/Documentos/TESTING/src/main/java/entrega2/pronostico.csv";
        String rutaResultados = "/home/jose/Documentos/TESTING/src/main/java/entrega2/resultados.csv";

        //Valores esperados:
        String jugador = "Maria";
        int puntaje = 3;

        identificarJugadores(rutaPronostico, jugadores);
        calcularPuntaje(rutaPronostico, rutaResultados, rondas, jugadores);

        for (String llave : jugadores.keySet()) {
            if (llave.equalsIgnoreCase(jugador)) {
                int puntajeObtenido = jugadores.get(llave);
                assertEquals(jugador, llave);
                assertEquals(puntaje, puntajeObtenido);
            }
        }
    }

}
