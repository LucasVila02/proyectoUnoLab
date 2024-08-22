package org.lucasv.entities.productos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProductoComestibles extends Producto  {

    protected double calorias;
    protected LocalDate fechaVencimiento;

    public ProductoComestibles(String descripcion, int stock, double precioCompra, int porcentajeGanancia,  boolean importado, double calorias, String fechaVencimiento) {
        super(descripcion, stock, precioCompra, porcentajeGanancia, importado);

        if (calorias < 0){
            throw  new IllegalArgumentException("Las calorías ingresadas debe ser igual o mayor que 0");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate fechaIngresada = LocalDate.parse(fechaVencimiento, formatter);

            LocalDate fechaActual = LocalDate.now();

            LocalDate fechaLimite = fechaActual.plusDays(30);

            if (!fechaIngresada.isAfter(fechaLimite)) {
                throw new IllegalArgumentException("La fecha debe ser al menos 30 días posterior a la fecha actual.");
            }

            this.fechaVencimiento = fechaIngresada;

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd-MM-yyyy.", e);
        }

        this.calorias = calorias;
        this.porcentajeGanancia = calculoPorcentajeGanancia();
    }

    public double getCalorias() {
        return calorias;
    }

    public double setCalorias(double calorias) {
        this.calorias = calorias;
        return calorias;
    }

    @Override
    protected int calculoPorcentajeGanancia() {
        if (porcentajeGanancia > 20 ){
            throw new IllegalArgumentException("El porcentaje de ganancia de los productos comestibles no puede se mayor a 20%.");
        }
            return porcentajeGanancia;

    }

    @Override
    protected String generarSku() {
        return "";
    }

    @Override
    protected double getPorcentajeMaximoDescuento() {
        return 0;
    }

}
