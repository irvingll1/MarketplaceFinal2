/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author PC
 */
@Entity
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String descripcion;
    private String marca;
    private String estado;
    private int precio;
    private String unidadmedida;
    private Date fechaadquision;
    private Date fechavencimiento;
    @ManyToOne
    @JoinColumn(name="iddetallepedido")
    private detallepedido detallepedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public Date getFechaadquision() {
        return fechaadquision;
    }

    public void setFechaadquision(Date fechaadquision) {
        this.fechaadquision = fechaadquision;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public detallepedido getDetallepedido() {
        return detallepedido;
    }

    public void setDetallepedido(detallepedido detallepedido) {
        this.detallepedido = detallepedido;
    }

    @Override
    public String toString() {
        return "producto{" + "idproducto=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marca=" + marca + ", estado=" + estado + ", precio=" + precio + ", unidadmedida=" + unidadmedida + ", fechaadquision=" + fechaadquision + ", fechavencimiento=" + fechavencimiento + ", detallepedido=" + detallepedido + '}';
    }

    
    
}
