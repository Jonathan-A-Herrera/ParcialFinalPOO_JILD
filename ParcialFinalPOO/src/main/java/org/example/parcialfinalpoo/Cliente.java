package org.example.parcialfinalpoo;

public class Cliente {
    private int ID_Cliente;
    private String nombre;
    private int cantidadCompras;
    private double totalGastado;

    public Cliente(int ID_Cliente, String nombre, int cantidadCompras, double totalGastado) {
        this.ID_Cliente = ID_Cliente;
        this.nombre = nombre;
        this.cantidadCompras = cantidadCompras;
        this.totalGastado = totalGastado;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }
}