package entrega3;

public class Pronostico {

    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultadoEnum;
    private String jugador;

    public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultadoEnum) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultadoEnum = resultadoEnum;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultadoEnum() {
        return resultadoEnum;
    }

    public void setResultadoEnum(ResultadoEnum resultadoEnum) {
        this.resultadoEnum = resultadoEnum;
    }


}
