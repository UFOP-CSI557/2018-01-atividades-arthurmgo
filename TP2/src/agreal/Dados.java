package agreal;

public class Dados {

    private Double piorResultado;
    private Double melhorResultado;


    public Double getPiorResultado() {
        return piorResultado;
    }

    public void setPiorResultado(Double piorResultado) {
        this.piorResultado = piorResultado;
    }

    public Double getMelhorResultado() {
        return melhorResultado;
    }

    public void setMelhorResultado(Double melhorResultado) {
        this.melhorResultado = melhorResultado;
    }

    public Dados(Double piorResultado, Double melhorResultado) {
        this.piorResultado = piorResultado;
        this.melhorResultado = melhorResultado;
    }
}
