package org.lucasv.entities;

import org.lucasv.entities.productos.*;

import java.util.*;
import java.util.stream.Stream;

public class Tienda {

    private String nombre;
    private int maxProductosStock;
    protected double saldoCaja;
    private List<Envasados> listaEnvasados;
    private List<Bebida> listaBebidas;
    private List<Limpieza> listaLimpieza;
    private List<List<? extends Producto>> listaTotalProductos;

    public Tienda(String nombre, int maxProductStock, long saldoCaja) {

        if (nombre.length() < 4) {
            throw new IllegalArgumentException("El nombre debe ser mayor a 4 caracteres");
        }
        if (maxProductStock <= 0) {
            throw new IllegalArgumentException("El maximo de productos en stock tiene que ser mayor a 0.");
        }
        if (saldoCaja <= 0) {
            throw new IllegalArgumentException("El saldo en caja debe ser mayor a 0.");
        }
        this.nombre = nombre;
        this.maxProductosStock = maxProductStock;
        this.saldoCaja = saldoCaja;
        this.listaEnvasados = new ArrayList<>();
        this.listaBebidas = new ArrayList<>();
        this.listaLimpieza = new ArrayList<>();

        this.listaTotalProductos = new ArrayList<>();
        listaTotalProductos.add(listaBebidas);
        listaTotalProductos.add(listaLimpieza);
        listaTotalProductos.add(listaEnvasados);
    }

    public List<List<? extends Producto>> getListaTotalProductos() {
        return listaTotalProductos;
    }

    public void agregarProducto(Producto producto) {

        int cantidadActual = getCantidadTotalProductos();
        int cantidadMaximaAAgregar = maxProductosStock - cantidadActual;
        int cantidadAAgregar = Math.min(producto.getStock(), cantidadMaximaAAgregar);


        double costoTotal = cantidadAAgregar * producto.getPrecioCompra();

        if (saldoCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            return;
        }

        if (cantidadAAgregar < producto.getStock()) {
            System.out.println("No se pueden agregar nuevos  productos a la tienda ya que se alcanzó el máximo de stock");

        }

        producto.setStock(cantidadAAgregar);

        Map<Class<? extends Producto>, List<? extends Producto>> productoMap = Map.of(
                Envasados.class, listaEnvasados,
                Bebida.class, listaBebidas,
                Limpieza.class, listaLimpieza
        );

        productoMap.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(producto))
                .findFirst()
                .ifPresent(entry -> ((List<Producto>) entry.getValue()).add(producto));

        System.out.println(producto.getSku() + " " + producto.getDescripcion() + " " + producto.getStock() + " x " + producto.getPrecioCompra());
        saldoCaja -= costoTotal;
        System.out.println("COSTO TOTAL: " + costoTotal);
    }

    public void realizarVenta(Map<Producto, Integer> productosVenta) {
        double totalVenta = 0;

        if (productosVenta.size() > 3) {
            throw new IllegalArgumentException("No se puede realizar la venta. Solo se permite un máximo de 3 productos por persona.");
        }

        for (Map.Entry<Producto, Integer> entry : productosVenta.entrySet()) {
            Producto producto = entry.getKey();

            if (entry.getValue() > 12) {
                throw new IllegalArgumentException("No puede comprar mas de 12 unidades por producto.");
            }

            if (!producto.isDisponibleVenta()) {
                System.out.println("El producto SKU: " + producto.getSku() + " " + producto.getDescripcion() + " no se encuentra disponible.");

                continue;
            }

            int cantidadDeseada = entry.getValue();
            int cantidadAAgregar = Math.min(cantidadDeseada, producto.getStock());
            int stockDisponible = producto.getStock();

            if (cantidadDeseada > stockDisponible) {
                cantidadDeseada = stockDisponible;
                System.out.println("Hay productos con stock disponible menor al solicitado.");
            }

            producto.setStock(stockDisponible - cantidadAAgregar);

            if (producto.getStock() == 0) {
                producto.setDisponibleVenta(false);
            }

            double costoProducto = cantidadDeseada * producto.getPrecioVenta();
            totalVenta += costoProducto;

            System.out.println(producto.getSku() + " " + producto.getDescripcion() + " " + cantidadDeseada + " x " + producto.getPrecioVenta());
        }

        System.out.println("TOTAL VENTA: " + totalVenta);
        saldoCaja += totalVenta;
    }

    private int getCantidadTotalProductos() {
        return Stream.of(listaEnvasados, listaBebidas, listaLimpieza)
                .flatMap(List::stream)
                .mapToInt(Producto::getStock)
                .sum();
    }

    public void mostrarBalanceTienda() {
        listaTotalProductos.stream()
                .flatMap(List::stream)
                .forEach(producto -> {
                    System.out.println("SKU: " + producto.getSku()
                            + " | Descripción: " + producto.getDescripcion()
                            + " | Stock: " + producto.getStock());

                });
        System.out.println("SALDO CAJA: " + saldoCaja);
    }

    public void obtenerComestiblesConMenorDescuento(double descuento) {
        Stream.of(listaEnvasados, listaBebidas)
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .map(p -> (ProductoComestibles) p)
                .filter(p -> !p.isImportado())
                .filter(p -> p.getPorcentajeDescuentoVenta() < descuento)
                .sorted(Comparator.comparingDouble(ProductoComestibles::getPrecioVenta))
                .map(p -> p.getDescripcion().toUpperCase())
                .toList()
                .forEach(System.out::println);


    }
}
