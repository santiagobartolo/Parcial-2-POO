package uniquindio.edu.co;

public enum CategoriaPrenda {
    CAMISETA(0.0),
    PANTALON(0.0),
    VESTIDO(0.10),   // 10% de recargo
    CHAQUETA(0.15),  // 15% de recargo
    CALZADO(0.05);   // 5% de recargo

    private final double recargo;

    CategoriaPrenda(double recargo) {
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }
}