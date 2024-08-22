package org.lucasv.test;
import org.lucasv.entities.productos.Bebida;
import org.lucasv.entities.productos.Envasados;
import org.lucasv.entities.productos.Limpieza;
import org.lucasv.entities.productos.Limpieza.TipoAplicacion;
import org.lucasv.entities.productos.Producto;
import org.lucasv.entities.Tienda;

import java.util.HashMap;
import java.util.Map;


public class TestTienda {
    public static void main(String[] args) {

        try {
            Tienda tiendaUno = new Tienda("Lucas", 32, 1000000);

            Limpieza detergente  = new Limpieza("Detergente", 12, 100, 10 ,false ,TipoAplicacion.ROPA);
            detergente.aplicarDescuento(5);
            tiendaUno.agregarProducto(detergente);

            Bebida cocaCola = new Bebida("Coca cola", 12, 120, 20, false, 100,"12-11-2024", 5  );
            cocaCola.aplicarDescuento(10);
            tiendaUno.agregarProducto(cocaCola);

            Envasados atun  =  new Envasados("Atún", 6, 110, 20,false, 1, "12-11-2024", "Vidrio");
            atun.aplicarDescuento(10);
            tiendaUno.agregarProducto(atun);

            Envasados choclo  =  new Envasados("Choclo", 6, 140, 20,false, 1, "12-11-2024", "Vidrio");
            choclo.aplicarDescuento(12);
            tiendaUno.agregarProducto(choclo);

            tiendaUno.mostrarBalanceTienda();

            Map<Producto, Integer> productosVenta = new HashMap<>();
            productosVenta.put(detergente, 12);
            productosVenta.put(cocaCola, 12);
            productosVenta.put(atun, 12);
            tiendaUno.realizarVenta(productosVenta);

            tiendaUno.mostrarBalanceTienda();

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



