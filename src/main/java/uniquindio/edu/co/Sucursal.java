package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Sucursal {
    private String codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private List<Inventario> inventarios;
    private List<Vendedor> vendedores;
    private List<Venta> ventas;

    public Sucursal(String codigo, String nombre, String direccion, String ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.inventarios = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public boolean agregarInventario(Inventario nuevoInv) {
        for (Inventario inv : inventarios) {
            if (inv.getPrenda().getCodigo().equalsIgnoreCase(nuevoInv.getPrenda().getCodigo()) &&
                    inv.getTalla().equalsIgnoreCase(nuevoInv.getTalla()) &&
                    inv.getColor().equalsIgnoreCase(nuevoInv.getColor())) {
                return false; // Ya existe esa combinación exacta de inventario
            }
        }
        inventarios.add(nuevoInv);
        return true;
    }

    public void agregarVendedor(Vendedor v) {
        vendedores.add(v); }
    public void registrarVenta(Venta v) { ventas.add(v);
    }

    public double calcularIngresosTotales() {
        double ingresos = 0;
        for (Venta v : ventas) {
            ingresos += v.calcularTotalVenta();
        }
        return ingresos;
    }

    public boolean existeVendedor(String documento) {
        for (Vendedor v : vendedores) {
            if (v.getDocumentoIdentidad().equals(documento)) return true;
        }
        return false;
    }



}
