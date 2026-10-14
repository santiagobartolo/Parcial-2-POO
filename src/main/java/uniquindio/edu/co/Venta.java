package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private String codigo;
    private String fecha;
    private Vendedor vendedor;
    private Cliente cliente;
    private List<DetalleVenta> detalles;

    public Venta(String codigo, String fecha, Vendedor vendedor, Cliente cliente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
    }

    public double calcularTotalVenta() {
        double total = 0;
        for (DetalleVenta d : detalles) {
            total += d.getSubtotal();
        }
        return total;
    }

}