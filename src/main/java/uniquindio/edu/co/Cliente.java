package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private String codigoAfiliacion;
    private CategoriaCliente categoria;
    private List<Venta> historialCompras;

    public Cliente(String documentoIdentidad, String nombreCompleto, String correoElectronico, String numeroTelefono, String codigoAfiliacion, CategoriaCliente categoria) {
        super(documentoIdentidad, nombreCompleto, correoElectronico, numeroTelefono);
        this.codigoAfiliacion = codigoAfiliacion;
        this.categoria = categoria;
        this.historialCompras = new ArrayList<>();
    }

    public String getCodigoAfiliacion() {
        return codigoAfiliacion;
    }

    public void setCodigoAfiliacion(String codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
    }

    public List<Venta> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<Venta> historialCompras) {
        this.historialCompras = historialCompras;
    }

    public void agregarCompra(Venta venta) {
        historialCompras.add(venta);
    }


}
