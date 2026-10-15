package uniquindio.edu.co;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombreComercial;
    private String nit;
    private String direccionPrincipal;
    private List<Sucursal> sucursales;
    private List<Prenda> catalogoPrendas;

    public Empresa(String nombreComercial, String nit, String direccionPrincipal) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccionPrincipal = direccionPrincipal;
        this.sucursales = new ArrayList<>();
        this.catalogoPrendas = new ArrayList<>();
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

}
