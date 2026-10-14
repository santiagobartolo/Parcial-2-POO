package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Persona {
    private String codigoEmpleado;
    private String fechaContratacion;
    private double salarioBasico;
    private List<Venta> ventasRegistradas;

    public Vendedor(String documentoIdentidad, String nombreCompleto, String correoElectronico, String numeroTelefono, String codigoEmpleado, String fechaContratacion, double salarioBasico) {
        super(documentoIdentidad, nombreCompleto, correoElectronico, numeroTelefono);
        this.codigoEmpleado = codigoEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.salarioBasico = salarioBasico;
        this.ventasRegistradas = new ArrayList<>();
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalarioBasico() {
        return salarioBasico;
    }

    public void setSalarioBasico(double salarioBasico) {
        this.salarioBasico = salarioBasico;
    }

    public List<Venta> getVentasRegistradas() {
        return ventasRegistradas;
    }

    public void setVentasRegistradas(List<Venta> ventasRegistradas) {
        this.ventasRegistradas = ventasRegistradas;
    }

    public void registrarVenta(Venta venta) {
        ventasRegistradas.add(venta);
    }

}
