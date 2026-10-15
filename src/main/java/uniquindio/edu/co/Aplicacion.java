package uniquindio.edu.co;

import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Inicialización de la Empresa y datos base
        Empresa modastyle = new Empresa("ModaStyle S.A.S.", "900.123.456-1", "Calle Real # 15-20");

        // Sucursales
        Sucursal sucursalArmenia = new Sucursal("SUC-01", "ModaStyle Centro", "Carrera 14 # 19-10", "Armenia");
        Sucursal sucursalCalarca = new Sucursal("SUC-02", "ModaStyle Calarcá", "Calle 30 # 25-05", "Calarcá");

        modastyle.agregarSucursal(sucursalArmenia);
        modastyle.agregarSucursal(sucursalCalarca);

        // Prendas iniciales en el catálogo general
        Prenda prenda1 = new Prenda("CM001", "Camiseta Básica", "Nike", 40000.0, CategoriaPrenda.CAMISETA);
        Prenda prenda2 = new Prenda("VS002", "Vestido Verano", "Zara", 120000.0, CategoriaPrenda.VESTIDO);
        Prenda prenda3 = new Prenda("CH003", "Chaqueta Cuero", "Levis", 250000.0, CategoriaPrenda.CHAQUETA);

        modastyle.agregarPrendaCatalogo(prenda1);
        modastyle.agregarPrendaCatalogo(prenda2);
        modastyle.agregarPrendaCatalogo(prenda3);

        // Inventario inicial en la sucursal de Armenia
        Inventario inv1 = new Inventario("M", "Negro", 10, prenda1);
        Inventario inv2 = new Inventario("S", "Blanco", 0, prenda2); // Agotada para probar la consulta
        Inventario inv3 = new Inventario("L", "Marrón", 5, prenda3);

        sucursalArmenia.agregarInventario(inv1);
        sucursalArmenia.agregarInventario(inv2);
        sucursalArmenia.agregarInventario(inv3);

        // Vendedores y Clientes iniciales
        Vendedor vendedor1 = new Vendedor("1094000111", "Carlos Pérez", "carlos@modastyle.com", "3101234567", "EMP-001", "2025-01-15", 1300000.0);
        sucursalArmenia.agregarVendedor(vendedor1);

        Cliente cliente1 = new Cliente("1094222333", "Ana Gómez", "ana@gmail.com", "3209876543", "CLI-100", CategoriaCliente.ORO);

        int opcion;
        do {
            System.out.println("\n====== MODASTYLE - MENÚ PRINCIPAL ======");
            System.out.println("1. Registrar prenda en el catálogo");
            System.out.println("2. Registrar cliente o vendedor");
            System.out.println("3. Registrar existencias en inventario");
            System.out.println("4. Consultar prendas disponibles en una sucursal");
            System.out.println("5. Registrar una venta");
            System.out.println("6. Consultar prendas agotadas");
            System.out.println("7. Consultar ventas realizadas por un vendedor");
            System.out.println("8. Consultar compras realizadas por un cliente");
            System.out.println("9. Calcular ingresos totales de una sucursal");
            System.out.println("10. Identificar la prenda más vendida en la empresa");
            System.out.println("11. reabastecer o reponer stock");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR PRENDA EN CATÁLOGO ---");
                    System.out.print("Código: ");
                    String codP = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomP = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marcaP = scanner.nextLine();
                    System.out.print("Precio base: ");
                    double precioP = scanner.nextDouble();
                    System.out.println("Categorías: CAMISETA, PANTALON, VESTIDO, CHAQUETA, CALZADO");
                    System.out.print("Ingrese categoría: ");
                    String catStr = scanner.next().toUpperCase();
                    scanner.nextLine(); // Limpiar buffer

                    try {
                        CategoriaPrenda catP = CategoriaPrenda.valueOf(catStr);
                        Prenda nuevaPrenda = new Prenda(codP, nomP, marcaP, precioP, catP);

                        // Lógica de validación de código duplicado
                        boolean agregado = true;
                        for (Prenda p : modastyle.getCatalogoPrendas()) {
                            if (p.getCodigo().equalsIgnoreCase(codP)) {
                                agregado = false;
                                break;
                            }
                        }

                        if (agregado) {
                            modastyle.agregarPrendaCatalogo(nuevaPrenda);
                            System.out.println("¡Prenda registrada exitosamente en el catálogo!");
                        } else {
                            System.out.println("Error: Ya existe una prenda registrada con el código " + codP);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Categoría inválida.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR ACTOR (CLIENTE / VENDEDOR) ---");
                    System.out.println("1. Registrar Cliente");
                    System.out.println("2. Registrar Vendedor");
                    System.out.print("Seleccione el tipo de actor a registrar: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, ingrese un número válido (1 o 2).");
                        scanner.next();
                    }
                    int tipoActor = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (tipoActor != 1 && tipoActor != 2) {
                        System.out.println("Error: Opción no válida. Ingrese 1 para Cliente o 2 para Vendedor.");
                        break;
                    }

                    System.out.print("Documento de identidad: ");
                    String doc = scanner.nextLine().trim();

                    // Validación previa para no pedir el resto de datos si el documento ya existe
                    if (modastyle.buscarClientePorDocumento(doc) != null || modastyle.buscarVendedorPorDocumento(doc) != null) {
                        System.out.println("Error: Ya existe una persona (cliente o vendedor) registrada con el documento de identidad " + doc);
                        break;
                    }

                    System.out.print("Nombre completo: ");
                    String nombre = scanner.nextLine().trim();
                    System.out.print("Correo electrónico: ");
                    String correo = scanner.nextLine().trim();
                    System.out.print("Número de teléfono: ");
                    String telefono = scanner.nextLine().trim();

                    if (tipoActor == 1) {
                        System.out.print("Código de afiliación: ");
                        String codAfiliacion = scanner.nextLine().trim();

                        System.out.println("Categorías de cliente disponibles: BRONCE, PLATA, ORO");
                        System.out.print("Ingrese categoría de cliente: ");
                        String catCStr = scanner.next().toUpperCase().trim();
                        scanner.nextLine(); 

                        try {
                            CategoriaCliente catC = CategoriaCliente.valueOf(catCStr);
                            Cliente nuevoCliente = new Cliente(doc, nombre, correo, telefono, codAfiliacion, catC);

                            boolean clienteRegistrado = modastyle.registrarCliente(nuevoCliente);
                            if (clienteRegistrado) {
                                System.out.println("¡Cliente registrado exitosamente en la empresa!");
                            } else {
                                System.out.println("Error: Ya existe un cliente registrado con ese documento de identidad o código de afiliación.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Categoría de cliente no válida. Intente con BRONCE, PLATA u ORO.");
                        }

                    } else {
                        System.out.print("Código de empleado: ");
                        String codEmp = scanner.nextLine().trim();
                        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
                        String fechaContratacion = scanner.nextLine().trim();
                        System.out.print("Salario básico: ");

                        while (!scanner.hasNextDouble()) {
                            System.out.println("Por favor, ingrese un número válido para el salario.");
                            scanner.next();
                        }
                        double salario = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar buffer

                        Vendedor nuevoVendedor = new Vendedor(doc, nombre, correo, telefono, codEmp, fechaContratacion, salario);

                        boolean vendedorRegistrado = modastyle.registrarVendedorGlobal(nuevoVendedor);
                        if (vendedorRegistrado) {

                            sucursalArmenia.agregarVendedor(nuevoVendedor);
                            System.out.println("¡Vendedor registrado exitosamente en la empresa y asignado a la sucursal!");
                        } else {
                            System.out.println("Error: Ya existe un vendedor registrado con ese documento de identidad o código de empleado.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- REGISTRAR EXISTENCIAS EN INVENTARIO (Sucursal Armenia) ---");
                    System.out.print("Ingrese el código de la prenda existente en el catálogo: ");
                    String codPrendaBusqueda = scanner.nextLine();

                    Prenda prendaEncontrada = null;
                    for (Prenda p : modastyle.getCatalogoPrendas()) {
                        if (p.getCodigo().equalsIgnoreCase(codPrendaBusqueda)) {
                            prendaEncontrada = p;
                            break;
                        }
                    }

                    if (prendaEncontrada != null) {
                        System.out.print("Talla (ej: S, M, L, 38): ");
                        String talla = scanner.nextLine();
                        System.out.print("Color: ");
                        String color = scanner.nextLine();
                        System.out.print("Cantidad disponible: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();

                        // Validar que no exista inventario duplicado (misma prenda, talla y color)
                        boolean existeInventario = false;
                        for (Inventario inv : sucursalArmenia.getInventarios()) {
                            if (inv.getPrenda().getCodigo().equalsIgnoreCase(codPrendaBusqueda) &&
                                    inv.getTalla().equalsIgnoreCase(talla) &&
                                    inv.getColor().equalsIgnoreCase(color)) {
                                existeInventario = true;
                                break;
                            }
                        }

                        if (!existeInventario) {
                            Inventario nuevoInv = new Inventario(talla, color, cantidad, prendaEncontrada);
                            sucursalArmenia.agregarInventario(nuevoInv);
                            System.out.println("¡Inventario registrado con éxito en la sucursal!");
                        } else {
                            System.out.println("Error: Ya existe un registro de inventario con esa misma talla y color para esta prenda en la sucursal.");
                        }
                    } else {
                        System.out.println("Error: No se encontró ninguna prenda con ese código en el catálogo general.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- PRENDAS DISPONIBLES EN SUCURSAL ARMENIA ---");
                    if (sucursalArmenia.getInventarios().isEmpty()) {
                        System.out.println("No hay inventario registrado en esta sucursal.");
                    } else {
                        for (Inventario inv : sucursalArmenia.getInventarios()) {
                            System.out.println("Prenda: " + inv.getPrenda().getNombre() +
                                    " | Marca: " + inv.getPrenda().getMarca() +
                                    " | Talla: " + inv.getTalla() +
                                    " | Color: " + inv.getColor() +
                                    " | Cantidad: " + inv.getCantidadDisponible());
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- REGISTRAR UNA VENTA ---");
                    Sucursal sucVenta = sucursalArmenia; // O la sucursal que desees gestionar

                    if (sucVenta.getInventarios().isEmpty()) {
                        System.out.println("No hay inventario en esta sucursal para realizar ventas.");
                        break;
                    }

                    //seleccionar vendedor
                    if (sucVenta.getVendedores().isEmpty()) {
                        System.out.println("Error: No hay vendedores registrados en esta sucursal.");
                        break;
                    }

                    System.out.println("\nSeleccione el vendedor que atiende la venta:");
                    for (int i = 0; i < sucVenta.getVendedores().size(); i++) {
                        Vendedor v = sucVenta.getVendedores().get(i);
                        System.out.println((i + 1) + ". " + v.getNombreCompleto() + " [Código: " + v.getCodigoEmpleado() + "]");
                    }
                    System.out.print("Elija el número del vendedor: ");
                    int idxVendedor = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (idxVendedor < 1 || idxVendedor > sucVenta.getVendedores().size()) {
                        System.out.println("Selección de vendedor inválida.");
                        break;
                    }
                    Vendedor vendedorVenta = sucVenta.getVendedores().get(idxVendedor - 1);

                    //seleccionar cliente
                    System.out.print("Ingrese el documento de identidad del cliente: ");
                    String docClienteBusqueda = scanner.nextLine();


                    Cliente clienteVenta = null;

                    if (cliente1.getDocumentoIdentidad().equals(docClienteBusqueda)) {
                        clienteVenta = cliente1;
                    } else {
                        System.out.println("El cliente no está registrado. Vamos a registrarlo rápidamente:");
                        System.out.print("Nombre completo: ");
                        String nomC = scanner.nextLine();
                        System.out.print("Correo: ");
                        String corrC = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telC = scanner.nextLine();
                        System.out.print("Código de afiliación: ");
                        String codAf = scanner.nextLine();
                        System.out.println("Categorías de cliente: BRONCE, PLATA, ORO");
                        System.out.print("Ingrese categoría: ");
                        String catCStr = scanner.next().toUpperCase();
                        scanner.nextLine();

                        try {
                            CategoriaCliente catC = CategoriaCliente.valueOf(catCStr);
                            clienteVenta = new Cliente(docClienteBusqueda, nomC, corrC, telC, codAf, catC);
                            System.out.println("¡Cliente registrado y seleccionado con éxito!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Categoría inválida. Se asignará categoría BRONCE por defecto.");
                            clienteVenta = new Cliente(docClienteBusqueda, nomC, corrC, telC, "CLI-DEF", CategoriaCliente.BRONCE);
                        }
                    }

                    // ciclo de compra del carrito
                    Venta nuevaVenta = new Venta("V-" + (System.currentTimeMillis() % 1000), "2026-10-14", vendedorVenta, clienteVenta);
                    boolean continuarComprando = true;

                    while (continuarComprando) {
                        System.out.println("\n--- INVENTARIO DISPONIBLE EN SUCURSAL ---");
                        java.util.List<Inventario> inventariosDisponibles = new java.util.ArrayList<>();
                        int index = 1;

                        for (Inventario inv : sucVenta.getInventarios()) {
                            if (inv.getCantidadDisponible() > 0) {
                                inventariosDisponibles.add(inv);
                                System.out.println(index + ". Prenda: " + inv.getPrenda().getNombre() +
                                        " | Marca: " + inv.getPrenda().getMarca() +
                                        " | Talla: " + inv.getTalla() +
                                        " | Color: " + inv.getColor() +
                                        " | Stock: " + inv.getCantidadDisponible() +
                                        " | Precio Base: $" + inv.getPrenda().getPrecioBase());
                                index++;
                            }
                        }

                        if (inventariosDisponibles.isEmpty()) {
                            System.out.println("¡Lo sentimos, no hay stock disponible de ninguna prenda en este momento!");
                            break;
                        }

                        System.out.print("Seleccione el número de la opción que desea llevar: ");
                        int seleccion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        if (seleccion < 1 || seleccion > inventariosDisponibles.size()) {
                            System.out.println("Opción inválida. Intente de nuevo.");
                            continue;
                        }

                        Inventario invSeleccionado = inventariosDisponibles.get(seleccion - 1);

                        System.out.print("Ingrese la cantidad que desea comprar de este artículo: ");
                        int cantidadComprar = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        if (cantidadComprar > invSeleccionado.getCantidadDisponible()) {
                            System.out.println("Error: No hay suficiente stock disponible. Stock actual: " + invSeleccionado.getCantidadDisponible());
                        } else {
                            // Cálculo del precio unitario final (recargo de la prenda + descuento del cliente)
                            double precioConRecargo = invSeleccionado.getPrenda().calcularPrecioConRecargo();
                            double descuentoCliente = clienteVenta.getCategoria().getDescuento();
                            double precioUnitarioFinal = precioConRecargo - (precioConRecargo * descuentoCliente);

                            // Crear detalle de venta y agregarlo
                            DetalleVenta detalle = new DetalleVenta(cantidadComprar, precioUnitarioFinal, invSeleccionado);
                            nuevaVenta.agregarDetalle(detalle);

                            // Descontar del inventario de la sucursal y registrar en el historial del inventario
                            invSeleccionado.descontarStock(cantidadComprar);
                            invSeleccionado.agregarDetalleVendido(detalle);

                            System.out.println("¡Artículo agregado exitosamente al carrito de compra!");
                        }

                        System.out.print("¿Desea seguir comprando otra prenda? (s/n): ");
                        String respuesta = scanner.nextLine().trim().toLowerCase();
                        if (!respuesta.equals("s")) {
                            continuarComprando = false;
                        }
                    }

                    // emitir el recibo de venta
                    if (!nuevaVenta.getDetalles().isEmpty()) {
                        sucVenta.registrarVenta(nuevaVenta);
                        vendedorVenta.registrarVenta(nuevaVenta);
                        clienteVenta.agregarCompra(nuevaVenta);

                        System.out.println("\n========================================");
                        System.out.println("         RECIBO DE VENTA - MODASTYLE      ");
                        System.out.println("========================================");
                        System.out.println("Código Factura : " + nuevaVenta.getCodigo());
                        System.out.println("Fecha          : " + nuevaVenta.getFecha());
                        System.out.println("Sucursal       : " + sucVenta.getNombre() + " (" + sucVenta.getCiudad() + ")");
                        System.out.println("Cliente        : " + clienteVenta.getNombreCompleto() + " [Cat: " + clienteVenta.getCategoria() + "]");
                        System.out.println("Vendedor       : " + vendedorVenta.getNombreCompleto());
                        System.out.println("----------------------------------------");
                        System.out.println("ITEMS COMPRADOS:");
                        for (DetalleVenta d : nuevaVenta.getDetalles()) {
                            System.out.println("- " + d.getInventario().getPrenda().getNombre() +
                                    " (Talla: " + d.getInventario().getTalla() +
                                    ", Color: " + d.getInventario().getColor() + ")" +
                                    " x" + d.getCantidadComprada() +
                                    " | P. Unit Final: $" + d.getPrecioUnitarioFinal() +
                                    " | Subtotal: $" + d.getSubtotal());
                        }
                        System.out.println("----------------------------------------");
                        System.out.println("VALOR TOTAL A PAGAR: $" + nuevaVenta.calcularTotalVenta());
                        System.out.println("========================================");
                        System.out.println("¡Gracias por su compra en ModaStyle!");
                    } else {
                        System.out.println("La venta fue cancelada o no se seleccionó ningún producto.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- PRENDAS AGOTADAS EN LA EMPRESA ---");
                    boolean hayAgotadas = false;
                    for (Sucursal suc : modastyle.getSucursales()) {
                        for (Inventario inv : suc.getInventarios()) {
                            if (inv.estaAgotado()) {
                                System.out.println("Sucursal: " + suc.getNombre() + " | Prenda: " + inv.getPrenda().getNombre() + " (Talla: " + inv.getTalla() + ", Color: " + inv.getColor() + ")");
                                hayAgotadas = true;
                            }
                        }
                    }
                    if (!hayAgotadas) {
                        System.out.println("No hay prendas agotadas actualmente.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- VENTAS REALIZADAS POR EL VENDEDOR (Carlos Pérez) ---");
                    if (vendedor1.getVentasRegistradas().isEmpty()) {
                        System.out.println("El vendedor no tiene ventas registradas.");
                    } else {
                        for (Venta v : vendedor1.getVentasRegistradas()) {
                            System.out.println("Venta Código: " + v.getCodigo() + " | Fecha: " + v.getFecha() + " | Total: $" + v.calcularTotalVenta());
                        }
                    }
                    break;

                case 8:
                    System.out.println("\n--- COMPRAS REALIZADAS POR EL CLIENTE (Ana Gómez) ---");
                    if (cliente1.getHistorialCompras().isEmpty()) {
                        System.out.println("El cliente no tiene compras registradas.");
                    } else {
                        for (Venta v : cliente1.getHistorialCompras()) {
                            System.out.println("Factura: " + v.getCodigo() + " | Fecha: " + v.getFecha() + " | Total Pagado: $" + v.calcularTotalVenta());
                        }
                    }
                    break;

                case 9:
                    System.out.println("\n--- INGRESOS TOTALES SUCURSAL ARMENIA ---");
                    System.out.println("Total generado: $" + sucursalArmenia.calcularIngresosTotales());
                    break;

                case 10:
                    System.out.println("\n--- PRENDA MÁS VENDIDA EN LA EMPRESA ---");
                    Prenda masVendida = modastyle.obtenerPrendaMasVendida();
                    if (masVendida != null) {
                        System.out.println("Prenda líder: " + masVendida.getNombre() + " (Marca: " + masVendida.getMarca() + ")");
                    } else {
                        System.out.println("Aún no se han registrado ventas en la empresa.");
                    }
                    break;

                case 11:
                    System.out.println("\n--- REABASTECER STOCK EN SUCURSAL ---");
                    if (sucursalArmenia.getInventarios().isEmpty()) {
                        System.out.println("No hay registros en el inventario para reabastecer.");
                        break;
                    }

                    System.out.println("Inventario actual en sucursal Armenia:");
                    int idx = 1;
                    for (Inventario inv : sucursalArmenia.getInventarios()) {
                        System.out.println(idx + ". " + inv.getPrenda().getNombre() +
                                " (Talla: " + inv.getTalla() +
                                ", Color: " + inv.getColor() + ")" +
                                " | Stock Actual: " + inv.getCantidadDisponible());
                        idx++;
                    }

                    System.out.print("Seleccione el número del artículo que desea reabastecer: ");
                    int seleccionInv = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (seleccionInv < 1 || seleccionInv > sucursalArmenia.getInventarios().size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }

                    Inventario invSeleccionado = sucursalArmenia.getInventarios().get(seleccionInv - 1);

                    System.out.print("Ingrese la cantidad de unidades que desea agregar al stock: ");
                    int cantidadAgregada = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (cantidadAgregada > 0) {
                        invSeleccionado.aumentarStock(cantidadAgregada);
                        System.out.println("¡Stock actualizado con éxito! Nuevo stock disponible: " + invSeleccionado.getCantidadDisponible());
                    } else {
                        System.out.println("La cantidad a agregar debe ser mayor a cero.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
