package entrega3;

import java.util.ArrayList;
import java.util.List;

public class Fase {

    private List<Ronda> listaRondas;
    private int numero;

    public Fase(int numero) {
        this.listaRondas = new ArrayList<>();
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Ronda> getListaRondas() {
        return listaRondas;
    }

    public void setListaRondas(List<Ronda> listaRondas) {
        this.listaRondas = listaRondas;
    }

    public void addRonda(Ronda ronda) {


       if (this.listaRondas.isEmpty()) {
           this.listaRondas.add(ronda);
       } else {
           for (Ronda ronda1: this.listaRondas) {
               if (ronda1.equals(ronda)) {
                   this.listaRondas.add(ronda);
               }
           }
       }
    }
}
