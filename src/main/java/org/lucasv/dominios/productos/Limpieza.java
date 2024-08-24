package org.lucasv.dominios.productos;

import org.lucasv.enums.TipoAplicacion;

public class Limpieza extends Producto {

    private static int contadorLimpieza = 100;
    private final TipoAplicacion tipoAplicacion;

    public Limpieza( String descripcion, int stock, double precioCompra, int porcentajeGanancia, boolean importado, TipoAplicacion tipoAplicacion) {
        super( descripcion, stock, precioCompra, porcentajeGanancia, importado);

        if (importado){
            throw new IllegalArgumentException("Los productos de limpieza no son importados");
        }

        this.tipoAplicacion = tipoAplicacion;
        this.sku = generarSku();
        this.precioVenta =calcularPrecioFinal() ;
        this.porcentajeGanancia = calculoPorcentajeGanancia();
    }

    @Override
    protected double getPorcentajeMaximoDescuento() {
        return 20;
    }

    @Override
    protected int calculoPorcentajeGanancia() {
        if (porcentajeGanancia > 25 ){
            throw new IllegalArgumentException("El porcentaje de ganancia de los productos de limpieza no puede se mayor a 25%.");
        }
        if (tipoAplicacion == TipoAplicacion.COCINA || tipoAplicacion == TipoAplicacion.MULTIUSO) {
            return porcentajeGanancia;
        } else {
            if (porcentajeGanancia < 10) {
                throw new IllegalArgumentException("El porcentaje de ganancia para ROPA y BAÑO  no puede ser menor a 10%.");
            }
            return porcentajeGanancia;
        }
    }

    @Override
    protected String generarSku() {
        return "AZ" + (contadorLimpieza++);
    }

    @Override
    public String toString() {
        return "{" +
                " sku= '" + sku + '\'' +
                ", descripcion= '" + descripcion + '\'' +
                ", stock=" + stock +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", porcentajeGanancia=" + porcentajeGanancia +
                ", disponibleVenta=" + disponibleVenta +
                ", importado=" + importado +
                ", tipo Aplicacion=" + tipoAplicacion +
                ", porcentajeDescuento= " + porcentajeDescuentoVenta +
                '}';
    }
}
