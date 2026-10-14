package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private String talla;
    private String color;
    private int cantidadDisponible;
    private Prenda prenda;
    private List<DetalleVenta> detallesVendidos;

    public Inventario(String talla, String color, int cantidadDisponible, Prenda prenda) {
        this.talla = talla;
        this.color = color;
        this.cantidadDisponible = cantidadDisponible;
        this.prenda = prenda;
        this.detallesVendidos = new ArrayList<>();
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public List<DetalleVenta> getDetallesVendidos() {
        return detallesVendidos;
    }

    public void setDetallesVendidos(List<DetalleVenta> detallesVendidos) {
        this.detallesVendidos = detallesVendidos;
    }

    public void descontarStock(int cantidad) {
        if (cantidad <= this.cantidadDisponible) {
            this.cantidadDisponible -= cantidad;
        }
    }

    public void agregarDetalleVendido(DetalleVenta detalle) {
        detallesVendidos.add(detalle);
    }

    public boolean estaAgotado() {
        return this.cantidadDisponible == 0;
    }

}
