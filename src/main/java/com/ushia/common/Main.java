package com.ushia.common;

import com.ushia.productos.factory.ProductoFactory;
import com.ushia.productos.model.*;
import com.ushia.productos.strategy.*;
import com.ushia.productos.validation.ProductoValidator;
import com.ushia.ventas.model.DetalleVenta;
import com.ushia.ventas.model.Pago;
import com.ushia.ventas.model.Venta;
import com.ushia.ventas.service.CalculoTotalService;
import com.ushia.ventas.state.*;
import com.ushia.ventas.strategy.*;
import com.ushia.clientes.model.Cliente;
import com.ushia.clientes.model.ItemRecomendacion;
import com.ushia.clientes.model.PreferenciaCliente;
import com.ushia.clientes.model.PreferenciaEstilo;
import com.ushia.clientes.strategy.RecomendacionPorPreferencias;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada de USHIA.
 * En este avance se prueban funcionalidades en memoria; no se inicializa JPA
 * ni se establece una conexión con PostgreSQL.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Colecciones temporales: los datos se pierden al cerrar el programa.
    private static final List<Producto> productos = new ArrayList<>();
    private static final List<Cliente> clientes = new ArrayList<>();

    // Clases existentes que no requieren EntityManager.
    private static final ProductoFactory productoFactory = new ProductoFactory();
    private static final ProductoValidator productoValidator = new ProductoValidator();
    private static final CalculoTotalService calculoTotalService = new CalculoTotalService();

    public static void main(String[] args) {
        mostrarBienvenida();
        ejecutarMenuPrincipal();
        scanner.close();
    }

    // Presenta el encabezado inicial del sistema.
    private static void mostrarBienvenida() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                       U S H I A                      ║");
        System.out.println("║             SISTEMA DE GESTIÓN COMERCIAL             ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    // Menú general para seleccionar uno de los tres módulos.
    private static void ejecutarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    MENÚ PRINCIPAL                    ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1. Gestión de productos e inventario                 ║");
            System.out.println("║  2. Gestión de ventas                                 ║");
            System.out.println("║  3. Gestión de clientes y recomendaciones             ║");
            System.out.println("║  4. Salir del sistema                                 ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> ejecutarMenuProductos();
                case 2 -> ejecutarMenuVentas();
                case 3 -> ejecutarMenuClientes();
                case 4 -> System.out.println("\nGracias por utilizar USHIA. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    // ==================== PRODUCTOS E INVENTARIO ====================

    private static void ejecutarMenuProductos() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║              PRODUCTOS E INVENTARIO                  ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1. Registrar producto                               ║");
            System.out.println("║  2. Listar productos de esta sesión                  ║");
            System.out.println("║  3. Probar estrategias de valorización               ║");
            System.out.println("║  4. Regresar al menú principal                       ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> listarProductos();
                case 3 -> probarValorizacionInventario();
                case 4 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // Usa ProductoFactory para crear el objeto y ProductoValidator para validarlo.
    private static void registrarProducto() {
        System.out.println("\n--- REGISTRO DE PRODUCTO ---");
        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre del vestido: ");
        String descripcion = leerTexto("Descripción: ");
        double precioVenta = leerDecimal("Precio de venta: S/ ");
        double precioCosto = leerDecimal("Precio de costo: S/ ");
        String nombreCategoria = leerTexto("Categoría: ");

        CategoriaProducto categoria = new CategoriaProducto(
                null, nombreCategoria, "Categoría temporal de la sesión", true);

        try {
            Producto producto = productoFactory.crearProducto(
                    codigo, nombre, descripcion, precioVenta, precioCosto,
                    categoria, null, null, EstadoProducto.ACTIVO);
            productoValidator.validar(producto);
            productos.add(producto);
            System.out.println("\nProducto creado correctamente.");
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: S/ " + producto.getPrecioVenta());
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo registrar: " + e.getMessage());
        }
    }

    // Lista los productos que se han creado desde que se inició el programa.
    private static void listarProductos() {
        System.out.println("\n--- PRODUCTOS DE ESTA SESIÓN ---");
        if (productos.isEmpty()) {
            System.out.println("Todavía no se han registrado productos.");
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ". " + p.getCodigo() + " | "
                    + p.getNombre() + " | S/ " + p.getPrecioVenta()
                    + " | " + p.getEstado());
        }
    }

    // Prueba las implementaciones Strategy de valorización con lotes ingresados.
    private static void probarValorizacionInventario() {
        System.out.println("\n--- VALORIZACIÓN DE INVENTARIO ---");
        int cantidadLotes = leerEntero("Cantidad de lotes: ");
        if (cantidadLotes <= 0) {
            System.out.println("Debe ingresar al menos un lote.");
            return;
        }

        List<LoteInventario> lotes = new ArrayList<>();
        for (int i = 1; i <= cantidadLotes; i++) {
            System.out.println("\nLote " + i);
            String codigo = leerTexto("Código: ");
            LocalDate fecha = leerFecha("Fecha de ingreso (AAAA-MM-DD): ");
            int cantidad = leerEntero("Cantidad disponible: ");
            double costo = leerDecimal("Costo unitario: S/ ");
            if (cantidad < 0 || costo < 0) {
                System.out.println("La cantidad y el costo no pueden ser negativos.");
                return;
            }
            lotes.add(new LoteInventario(null, null, codigo, fecha,
                    cantidad, cantidad, costo));
        }

        System.out.println("\n1. FIFO  2. LIFO  3. Promedio ponderado");
        System.out.println("4. Último costo  5. Costo estándar");
        int opcion = leerEntero("Seleccione estrategia: ");
        EstrategiaValorizacionInventario estrategia;
        switch (opcion) {
            case 1 -> estrategia = new EstrategiaFIFO();
            case 2 -> estrategia = new EstrategiaLIFO();
            case 3 -> estrategia = new EstrategiaPromedioPonderado();
            case 4 -> estrategia = new EstrategiaUltimoCosto();
            case 5 -> estrategia = new EstrategiaCostoEstandar(
                    leerDecimal("Costo estándar: S/ "));
            default -> {
                System.out.println("Estrategia no válida.");
                return;
            }
        }
        try {
            System.out.printf("Costo unitario calculado: S/ %.2f%n",
                    estrategia.calcularCostoUnitario(lotes));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo calcular: " + e.getMessage());
        }
    }

    // ============================== VENTAS ==============================

    private static void ejecutarMenuVentas() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║                       VENTAS                         ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1. Calcular descuento (Strategy)                    ║");
            System.out.println("║  2. Procesar pago (Strategy)                         ║");
            System.out.println("║  3. Probar estados de una venta (State)              ║");
            System.out.println("║  4. Calcular subtotal y total                        ║");
            System.out.println("║  5. Regresar al menú principal                       ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> calcularDescuento();
                case 2 -> procesarPago();
                case 3 -> probarEstadosVenta();
                case 4 -> calcularTotalVenta();
                case 5 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    // Ejecuta una estrategia de descuento porcentual o de monto fijo.
    private static void calcularDescuento() {
        BigDecimal subtotal = leerBigDecimal("Subtotal de la venta: S/ ");
        System.out.println("1. Porcentaje  2. Monto fijo");
        int opcion = leerEntero("Tipo de descuento: ");
        try {
            EstrategiaDescuento estrategia;
            if (opcion == 1) {
                estrategia = new DescuentoPorcentajeStrategy(
                        leerBigDecimal("Porcentaje: "));
            } else if (opcion == 2) {
                estrategia = new DescuentoMontoFijoStrategy(
                        leerBigDecimal("Monto del descuento: S/ "));
            } else {
                System.out.println("Opción no válida.");
                return;
            }

            BigDecimal descuento = estrategia.calcularDescuento(subtotal);
            System.out.println("Descuento: S/ " + descuento);
            System.out.println("Total: S/ " + subtotal.subtract(descuento));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo calcular: " + e.getMessage());
        }
    }

    // Prueba las estrategias de pago existentes sin guardar el pago en BD.
    private static void procesarPago() {
        BigDecimal monto = leerBigDecimal("Monto del pago: S/ ");
        System.out.println("1. Efectivo  2. Tarjeta");
        int opcion = leerEntero("Método de pago: ");
        EstrategiaPago estrategia;
        if (opcion == 1) {
            estrategia = new PagoEfectivoStrategy();
        } else if (opcion == 2) {
            estrategia = new PagoTarjetaStrategy();
        } else {
            System.out.println("Opción no válida.");
            return;
        }
        Pago pago = new Pago();
        pago.setMonto(monto);
        try {
            estrategia.procesarPago(pago);
            System.out.println("Pago procesado correctamente.");
            System.out.println("Método: " + pago.getMetodoPago());
            System.out.println("Estado: " + pago.getEstadoPago());
            System.out.println("Fecha: " + pago.getFechaPago());
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo procesar el pago: " + e.getMessage());
        }
    }

    // Comprueba las transiciones y restricciones del patrón State.
    private static void probarEstadosVenta() {
        Venta venta = new Venta();
        venta.setEstado("PENDIENTE");
        EstadoVentaState estado = new VentaPendienteState();
        System.out.println("1. Pagar venta  2. Anular venta");
        int opcion = leerEntero("Seleccione una acción: ");
        try {
            if (opcion == 1) {
                estado.pagar(venta);
                estado = new VentaPagadaState();
                System.out.println("Estado actual: " + venta.getEstado());
                System.out.println("Intentando pagar otra vez...");
                estado.pagar(venta);
            } else if (opcion == 2) {
                estado.anular(venta);
                estado = new VentaAnuladaState();
                System.out.println("Estado actual: " + venta.getEstado());
                System.out.println("Intentando pagar una venta anulada...");
                estado.pagar(venta);
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (IllegalStateException e) {
            System.out.println("Operación bloqueada por State: " + e.getMessage());
        }
    }

    // Usa CalculoTotalService con detalles temporales creados en consola.
    private static void calcularTotalVenta() {
        int cantidadDetalles = leerEntero("Cantidad de productos en la venta: ");
        if (cantidadDetalles <= 0) {
            System.out.println("Debe ingresar al menos un detalle.");
            return;
        }
        List<DetalleVenta> detalles = new ArrayList<>();
        for (int i = 1; i <= cantidadDetalles; i++) {
            System.out.println("Detalle " + i);
            int cantidad = leerEntero("Cantidad: ");
            BigDecimal precio = leerBigDecimal("Precio unitario: S/ ");
            if (cantidad <= 0 || precio.signum() < 0) {
                System.out.println("Cantidad o precio no válido.");
                return;
            }
            DetalleVenta detalle = new DetalleVenta();
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precio);
            detalles.add(detalle);
        }
        BigDecimal subtotal = calculoTotalService.calcularSubtotal(detalles);
        BigDecimal descuento = leerBigDecimal("Descuento total: S/ ");
        try {
            BigDecimal total = calculoTotalService.calcularTotal(subtotal, descuento);
            System.out.println("Subtotal: S/ " + subtotal);
            System.out.println("Descuento: S/ " + descuento);
            System.out.println("Total: S/ " + total);
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo calcular el total: " + e.getMessage());
        }
    }

    // ================= CLIENTES Y RECOMENDACIONES =================

    private static void ejecutarMenuClientes() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║           CLIENTES Y RECOMENDACIONES                 ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║  1. Registrar cliente para esta sesión               ║");
            System.out.println("║  2. Listar clientes de esta sesión                   ║");
            System.out.println("║  3. Generar recomendaciones por preferencias        ║");
            System.out.println("║  4. Regresar al menú principal                       ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> listarClientes();
                case 3 -> generarRecomendaciones();
                case 4 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // Registra un cliente temporal para utilizarlo en las pruebas de recomendación.
    private static void registrarCliente() {
        Cliente cliente = new Cliente();
        System.out.println("\n--- REGISTRO TEMPORAL DE CLIENTE ---");
        cliente.setNombres(leerTexto("Nombres: "));
        cliente.setApellidos(leerTexto("Apellidos: "));
        cliente.setDni(leerTexto("DNI: "));
        cliente.setCorreo(leerTexto("Correo: "));
        cliente.setActivo(true);
        clientes.add(cliente);
        System.out.println("Cliente agregado a la sesión.");
    }

    // Lista los clientes registrados durante la ejecución actual.
    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Todavía no hay clientes registrados.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.println((i + 1) + ". " + c.getNombres() + " "
                    + c.getApellidos() + " | DNI: " + c.getDni()
                    + " | Correo: " + c.getCorreo());
        }
    }

    // Ejecuta RecomendacionPorPreferencias con los productos de esta sesión.
    private static void generarRecomendaciones() {
        if (clientes.isEmpty()) {
            System.out.println("Primero registre un cliente.");
            return;
        }
        if (productos.isEmpty()) {
            System.out.println("Primero registre productos en el módulo de productos.");
            return;
        }
        listarClientes();
        int indice = leerEntero("Seleccione el número de cliente: ") - 1;
        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Cliente no válido.");
            return;
        }

        Cliente cliente = clientes.get(indice);
        String estilo = leerTexto("Estilo preferido (ej. elegante, casual): ");
        PreferenciaCliente preferencia = new PreferenciaCliente();
        PreferenciaEstilo preferenciaEstilo = new PreferenciaEstilo();
        preferenciaEstilo.setEstilo(estilo);
        preferencia.agregarEstilo(preferenciaEstilo);
        cliente.agregarPreferencia(preferencia);

        RecomendacionPorPreferencias estrategia = new RecomendacionPorPreferencias();
        List<ItemRecomendacion> recomendaciones = estrategia.recomendar(cliente, productos);
        System.out.println("\n--- RESULTADOS DE RECOMENDACIÓN ---");
        if (recomendaciones.isEmpty()) {
            System.out.println("No se encontraron productos que coincidan con esa preferencia.");
            return;
        }
        for (ItemRecomendacion item : recomendaciones) {
            System.out.println("Producto: " + item.getProducto().getNombre());
            System.out.println("Puntuación: " + item.getPuntuacion());
            System.out.println("Motivo: " + item.getMotivo());
            System.out.println("----------------------------------------");
        }
    }

    // ======================== ENTRADA DE DATOS ========================

    // Solicita texto no vacío.
    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("Este campo no puede quedar vacío.");
        }
    }

    // Solicita un entero y controla entradas no numéricas.
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    // Solicita un decimal y acepta coma o punto decimal.
    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal válido.");
            }
        }
    }

    // Utiliza BigDecimal para representar importes monetarios.
    private static BigDecimal leerBigDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return new BigDecimal(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un importe válido.");
            }
        }
    }

    // Solicita una fecha con formato AAAA-MM-DD.
    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Fecha inválida. Use AAAA-MM-DD.");
            }
        }
    }
}
