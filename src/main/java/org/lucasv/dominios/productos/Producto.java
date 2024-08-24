package org.lucasv.dominios.productos;

public abstract class Producto {

    protected String sku;
    protected String descripcion;
    protected int stock;
    protected int porcentajeGanancia;
    protected double porcentajeDescuentoVenta;
    protected double precioCompra;
    protected double precioVenta;
    protected boolean disponibleVenta;
    protected boolean importado;

    public Producto( String descripcion, int stock, double precioCompra, int porcentajeGanancia, boolean importado) {

        if (stock <= 0){
            throw new IllegalArgumentException("El stock ingresado debe ser mayor que 0.");
        }
        if (precioCompra <= 0){
            throw new IllegalArgumentException("El precio ingresado debe ser mayor que 0.");
        }
        if(porcentajeGanancia <= 0){
            throw  new IllegalArgumentException("El porcentaje de ganancia ingresado debe ser mayor que 0.");
        }
        if(descripcion.length() < 4){
            throw new IllegalArgumentException("La descripción debe ser mayor a 5 caracteres.");
        }

        this.descripcion = descripcion;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponibleVenta = true;
        this.importado = importado;
    }

    public String getSku() {
        return sku;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }


    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }


    public double getPorcentajeDescuentoVenta() {
        return porcentajeDescuentoVenta;
    }

    public void setPorcentajeDescuentoVenta(double porcentajeDescuentoVenta) {
        this.porcentajeDescuentoVenta = porcentajeDescuentoVenta;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setDisponibleVenta(boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public boolean isDisponibleVenta() {
        return disponibleVenta;
    }

    protected  abstract int calculoPorcentajeGanancia();

    protected abstract String generarSku();

    protected abstract double getPorcentajeMaximoDescuento();

    public double calcularPrecioFinal() {
        double precioFinal = precioCompra + (precioCompra * calculoPorcentajeGanancia() / 100);
        if (importado) {
            precioFinal += precioFinal * 0.12;
        }
        return precioFinal;
    }

    public void aplicarDescuento(double porcentajeDescuento) {
        if (porcentajeDescuento > getPorcentajeMaximoDescuento() || porcentajeDescuento <= 0) {
            throw new IllegalArgumentException("El descuento debe estar entre 1% y " + getPorcentajeMaximoDescuento() + "%.");
        }

        double precioOriginal = getPrecioVenta();
        double nuevoPrecio = precioOriginal - (precioOriginal * porcentajeDescuento / 100);

        setPrecioVenta(nuevoPrecio);
        setPorcentajeDescuentoVenta(porcentajeDescuento);
    }

}
