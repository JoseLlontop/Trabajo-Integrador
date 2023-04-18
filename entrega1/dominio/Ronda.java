package curso.java.utn.Proyecto.entrega1.dominio;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private int nro;
    private List<Partido> partidos;

    public Ronda(int nro) {
        this.nro = nro;
        this.partidos = new ArrayList<>();
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public List<Partido> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

   public void agregarPartido(Partido partido) {
        this.partidos.add(partido);
    }

    public int puntos(Pronostico pronostico) {

        for (Partido partido: this.partidos) {
            if (partido.getEquipo1().equals(pronostico.getPartido().getEquipo1()) &&
                    partido.getEquipo2().equals(pronostico.getPartido().getEquipo2())) {

                if (partido.resultado(pronostico.getEquipo()) == pronostico.getResultadoEnum()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        return 0;
    }
}
