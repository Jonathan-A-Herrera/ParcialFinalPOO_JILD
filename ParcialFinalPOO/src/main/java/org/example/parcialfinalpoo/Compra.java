package org.example.parcialfinalpoo;

public class Compra {
    private int idCompra; // 00085720 ID de la compra
    private int idCliente; // 00085720 ID del cliente
    private int idTarjeta; // 00085720 ID de la tarjeta
    private String fechaCompra; // 00085720 Fecha de la compra
    private double monto; // 00085720 Monto de la compra
    private String descripcion; // 00085720 Descripcion de la compra

    // Constructor, getters y setters
    public Compra(int idCompra, int idCliente, int idTarjeta, String fechaCompra, double monto, String descripcion) {
        this.idCompra = idCompra; // 00085720 Inicializa el ID de la compra
        this.idCliente = idCliente; // 00085720 Inicializa el ID del cliente
        this.idTarjeta = idTarjeta; // 00085720 Inicializa el ID de la tarjeta
        this.fechaCompra = fechaCompra; // 00085720 Inicializa la fecha de la compra
        this.monto = monto; // 00085720 Inicializa el monto de la compra
        this.descripcion = descripcion; // 00085720 Inicializa la descripción de la compra
    }

    public int getIdCompra() { return idCompra; } // 00085720 Obtiene el ID de la compra
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; } // 00085720 Establece el ID de la compra
    public int getIdCliente() { return idCliente; } // 00085720 Obtiene el ID del cliente
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } // 00085720 Establece el ID del cliente
    public int getIdTarjeta() { return idTarjeta; } // 00085720 Obtiene el ID de la tarjeta
    public void setIdTarjeta(int idTarjeta) { this.idTarjeta = idTarjeta; } // 00085720 Establece el ID de la tarjeta
    public String getFechaCompra() { return fechaCompra; } // 00085720 Obtiene la fecha de la compra
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; } // 00085720 Establece la fecha de la compra
    public double getMonto() { return monto; } // 00085720 Obtiene el monto de la compra
    public void setMonto(double monto) { this.monto = monto; } // 00085720 Establece el monto de la compra
    public String getDescripcion() { return descripcion; } // 00085720 Obtiene la descripcion de la compra
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } // 00085720 Establece la descripcion de la compra
}