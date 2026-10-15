package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombreComercial;
    private String nit;
    private String direccionPrincipal;
    private List<Sucursal> sucursales;
    private List<Prenda> catalogoPrendas;
    private List<Cliente> clientes;
    private List<Vendedor> vendedoresGlobales;

    public Empresa(String nombreComercial, String nit, String direccionPrincipal) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccionPrincipal = direccionPrincipal;
        this.sucursales = new ArrayList<>();
        this.catalogoPrendas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendedoresGlobales = new ArrayList<>();
    }

    public void agregarSucursal(Sucursal sucursal) {

        this.sucursales.add(sucursal);
    }

    public boolean agregarPrendaCatalogo(Prenda nuevaPrenda) {
        for (Prenda p : catalogoPrendas) {
            if (p.getCodigo().equalsIgnoreCase(nuevaPrenda.getCodigo())) {
                return false;
            }
        }
        catalogoPrendas.add(nuevaPrenda);
        return true;
    }

    public String getNombreComercial() {

        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {

        this.nombreComercial = nombreComercial;
    }

    public String getNit() {

        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccionPrincipal() {

        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public List<Sucursal> getSucursales() {

        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {

        this.sucursales = sucursales;
    }

    public List<Prenda> getCatalogoPrendas() {

        return catalogoPrendas;
    }

    public void setCatalogoPrendas(List<Prenda> catalogoPrendas) {

        this.catalogoPrendas = catalogoPrendas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Vendedor> getVendedoresGlobales() {
        return vendedoresGlobales;
    }

    public void setVendedoresGlobales(List<Vendedor> vendedoresGlobales) {
        this.vendedoresGlobales = vendedoresGlobales;
    }

    //Identifica la prenda con mayor cantidad de unidades vendidas en toda la empresa
    public Prenda obtenerPrendaMasVendida() {
        Prenda prendaMasVendida = null;
        int maxUnidades = -1;

        for (Prenda prenda : catalogoPrendas) {
            int totalUnidadesPrenda = 0;
            for (Sucursal suc : sucursales) {
                for (Inventario inv : suc.getInventarios()) {
                    if (inv.getPrenda().equals(prenda)) {
                        for (DetalleVenta det : inv.getDetallesVendidos()) {
                            totalUnidadesPrenda += det.getCantidadComprada();
                        }
                    }
                }
            }
            if (totalUnidadesPrenda > maxUnidades) {
                maxUnidades = totalUnidadesPrenda;
                prendaMasVendida = prenda;
            }
        }
        return prendaMasVendida;
    }
    public boolean registrarVendedorGlobal(Vendedor nuevoVendedor) {
        for (Vendedor v : vendedoresGlobales) {
            if (v.getDocumentoIdentidad().equalsIgnoreCase(nuevoVendedor.getDocumentoIdentidad()) ||
                    v.getCodigoEmpleado().equalsIgnoreCase(nuevoVendedor.getCodigoEmpleado())) {
                return false; // Ya existe por documento o por código de empleado
            }
        }
        vendedoresGlobales.add(nuevoVendedor);
        return true;
    }

    public Vendedor buscarVendedorPorDocumento(String documento) {
        for (Vendedor v : vendedoresGlobales) {
            if (v.getDocumentoIdentidad().equalsIgnoreCase(documento)) {
                return v;
            }
        }
        return null;
    }

    public boolean registrarCliente(Cliente nuevoCliente) {
        for (Cliente c : clientes) {
            if (c.getDocumentoIdentidad().equalsIgnoreCase(nuevoCliente.getDocumentoIdentidad())) {
                return false; // Ya existe un cliente con ese documento
            }
        }
        clientes.add(nuevoCliente);
        return true;
    }

    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumentoIdentidad().equalsIgnoreCase(documento)) {
                return c;
            }
        }
        return null;
    }

}
