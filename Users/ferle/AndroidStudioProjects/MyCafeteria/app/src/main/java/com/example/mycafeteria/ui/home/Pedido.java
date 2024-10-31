package com.example.mycafeteria.ui.home;

public class Pedido {
    private String nombreCliente;
    private String producto;
    private int cantidad;
    private String status;

    public Pedido(String nombreCliente, String producto, int cantidad, String status) {
        this.nombreCliente = nombreCliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.status = status;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getStatus() {
        return status;
    }
}
