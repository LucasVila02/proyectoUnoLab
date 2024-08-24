package org.lucasv.test;
import org.lucasv.dominios.productosComestibles.Bebida;
import org.lucasv.dominios.productosComestibles.Envasados;
import org.lucasv.dominios.productos.Limpieza;
import org.lucasv.dominios.productos.Producto;
import org.lucasv.dominios.tienda.Tienda;
import org.lucasv.enums.TipoAplicacion;

import java.util.HashMap;
import java.util.Map;


public class TestTienda {
    public static void main(String[] args) {

        try {
            Tienda tiendaUno = new Tienda("Lucas", 32, 100000);

            Limpieza detergente  = new Limpieza("Detergente", 12, 100, 10 ,false , TipoAplicacion.ROPA);
            detergente.aplicarDescuento(5);
            tiendaUno.realizarCompra(detergente);

            Bebida cocaCola = new Bebida("Coca cola", 12, 120, 20, false, 100,"12-11-2024", 5  );
            cocaCola.aplicarDescuento(10);
            tiendaUno.realizarCompra(cocaCola);

            Envasados atun  =  new Envasados("Atún", 6, 110, 20,false, 1, "12-11-2024", "Vidrio");
            atun.aplicarDescuento(10);
            tiendaUno.realizarCompra(atun);

            Envasados choclo  =  new Envasados("Choclo", 6, 140, 20,false, 1, "12-11-2024", "Vidrio");
            choclo.aplicarDescuento(12);
            tiendaUno.realizarCompra(choclo);

            tiendaUno.mostrarBalanceTienda();

            Map<Producto, Integer> productosVenta = new HashMap<>();
            productosVenta.put(detergente, 12);
            productosVenta.put(cocaCola, 12);
            productosVenta.put(atun, 12);
            tiendaUno.realizarVenta(productosVenta);

            tiendaUno.mostrarBalanceTienda();
//            tiendaUno.getListaTotalProductos().forEach(System.out::println);

            Map<Producto, Integer> productosVenta1 = new HashMap<>();
            productosVenta1.put(cocaCola, 12);
            productosVenta1.put(detergente, 10);
            productosVenta1.put(atun, 10);
            tiendaUno.realizarVenta(productosVenta1);

            tiendaUno.mostrarBalanceTienda();

            tiendaUno.obtenerComestiblesConMenorDescuento(13);
        }catch (IllegalArgumentException e){
            System.out.println("Error al crear el producto: " + e.getMessage());
        }
    }
    }



