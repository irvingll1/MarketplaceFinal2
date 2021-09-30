/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author PC
 */
@Entity
public class detallepedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int estado;
    private int cantidad;
    private double precio;
    private double subtotal;
    private int idpedido;

    public detallepedido() {
    }

    public detallepedido(int id, int estado, int cantidad, double precio, double subtotal, int idpedido) {
        this.id = id;
        this.estado = estado;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.idpedido = idpedido;
    }

    public int getIddetallepedido() {
        return id;
    }

    public void setIddetallepedido(int iddetallepedido) {
        this.id = iddetallepedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }
    
    
}
