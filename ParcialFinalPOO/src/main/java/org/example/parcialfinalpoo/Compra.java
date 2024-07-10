package org.example.parcialfinalpoo;

public class Compra {
    private int idCompra;
    private int idCliente;
    private int idTarjeta;
    private String fechaCompra;
    private double monto;
    private String descripcion;

    // Constructor, getters y setters
    public Compra(int idCompra, int idCliente, int idTarjeta, String fechaCompra, double monto, String descripcion) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.idTarjeta = idTarjeta;
        this.fechaCompra = fechaCompra;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdTarjeta() { return idTarjeta; }
    public void setIdTarjeta(int idTarjeta) { this.idTarjeta = idTarjeta; }
    public String getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
