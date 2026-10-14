package uniquindio.edu.co;

public class DetalleVenta {
    private int cantidadComprada;
    private double precioUnitarioFinal;
    private double subtotal;
    private Inventario inventario;

    public DetalleVenta(int cantidadComprada, double precioUnitarioFinal, Inventario inventario) {
        this.cantidadComprada = cantidadComprada;
        this.precioUnitarioFinal = precioUnitarioFinal;
        this.inventario = inventario;
        this.subtotal = cantidadComprada * precioUnitarioFinal;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
        this.subtotal = this.cantidadComprada * this.precioUnitarioFinal;
    }

    public double getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(double precioUnitarioFinal) {
        this.precioUnitarioFinal = precioUnitarioFinal;
        this.subtotal = this.cantidadComprada * this.precioUnitarioFinal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

}
