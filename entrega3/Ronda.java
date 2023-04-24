package entrega3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public int puntos(Pronostico pronostico, int puntosGana) {

        for (Partido partido: this.partidos) {
            if (partido.getEquipo1().equals(pronostico.getPartido().getEquipo1()) &&
                    partido.getEquipo2().equals(pronostico.getPartido().getEquipo2())) {

                if (partido.resultado(pronostico.getEquipo()) == pronostico.getResultadoEnum()) {
                    return puntosGana;
                } else {
                    return 0;
                }
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ronda ronda = (Ronda) o;
        return nro == ronda.nro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nro);
    }

}
