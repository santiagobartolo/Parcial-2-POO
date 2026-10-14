package uniquindio.edu.co;

public enum CategoriaCliente {
    BRONCE(0.05), // 5% de descuento
    PLATA(0.10),  // 10% de descuento
    ORO(0.15);    // 15% de descuento

    private final double descuento;

    CategoriaCliente(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }
}