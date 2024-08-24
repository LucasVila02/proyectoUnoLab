package org.lucasv.dominios.productosComestibles;

public class Bebida extends ProductoComestibles {

    private final double graduacionAlcoholica;
    private static  int contadorEnvasado = 100;

    public Bebida( String descripcion, int stock, double precioCompra, int porcentajeGanancia, boolean importado, double calorias, String fechaVencimiento, double graduacionAlcoholica) {
        super( descripcion, stock, precioCompra, porcentajeGanancia,   importado,calorias , fechaVencimiento);

        if (graduacionAlcoholica < 0){
            throw new IllegalArgumentException("La graduación alcohólica ingresada debe ser igual o mayor que 0.");
        }

        this.graduacionAlcoholica = graduacionAlcoholica;
        this.sku = generarSku();
        this.calorias = calcularCalorias();;
        this.precioVenta = calcularPrecioFinal();
    }

    public double calcularCalorias() {
        double graduacion = graduacionAlcoholica > 4.5 ? 1.5 : (graduacionAlcoholica > 2 ? 1.25 : 1.0);
        setCalorias(getCalorias() * graduacion);
        return getCalorias();
}

    @Override
    protected String generarSku() {
        return "AC" + (contadorEnvasado++);
    }

    @Override
    protected double getPorcentajeMaximoDescuento() {
        return 10;
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
                ", graduacionAlcoholica=" + graduacionAlcoholica +
                ", calorias=" + calorias +
                ", fechaVencimiento= '" + fechaVencimiento + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuentoVenta +
                '}';
    }
}
