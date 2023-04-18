package curso.java.utn.Proyecto.entrega1.dominio;

public enum ResultadoEnum {

    GANADOR("Ganador"),
    PERDEDOR("Perdedor"),
    EMPATE("Empate");

    private final String resultado;

    ResultadoEnum(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return  resultado;
    }
}
