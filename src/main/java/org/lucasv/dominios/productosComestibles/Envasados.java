package org.lucasv.dominios.productosComestibles;


public class Envasados extends ProductoComestibles {

    private final String tipoEnvase ;
    private static int contadorEnvasado = 100;

    public Envasados( String descripcion, int stock, double precioCompra, int porcentajeGanancia, boolean importado, double calorias, String fechaVencimiento, String tipoEnvase) {
        super( descripcion, stock, precioCompra, porcentajeGanancia,   importado, calorias,  fechaVencimiento);

        if (tipoEnvase.length() < 4){
            throw new IllegalArgumentException("El tipo de envase ingresado debe ser mayor a 4 caracteres.");
        }

        this.tipoEnvase = tipoEnvase;
        this.sku = generarSku();
        this.precioVenta = calcularPrecioFinal();
    }

    @Override
    protected String generarSku() {
        return "AB" + (contadorEnvasado++);
    }

    @Override
    protected double getPorcentajeMaximoDescuento() {
        return 15;
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }

    @Override
    public String toString() {
        return "{" +
                " sku='" + sku + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", porcentajeGanancia=" + porcentajeGanancia +
                ", disponibleVenta=" + disponibleVenta +
                ", importado=" + importado +
                ", tipoEnvase='" + tipoEnvase + '\'' +
                ", calorias=" + calorias +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", porcentajeDescuento= " + porcentajeDescuentoVenta +
                '}';
    }
}


