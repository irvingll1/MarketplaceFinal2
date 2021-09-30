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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author PC
 */
@Entity
public class tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private int telefono;
    private String descripcion;
    private String coordenadas;
    private int estado;
    @ManyToOne
    @JoinColumn(name="iddireccion")
    private direccion direccion;
    @ManyToOne
    @JoinColumn(name="idcalifica")
    private calificacion calificacion;

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(direccion direccion) {
        this.direccion = direccion;
    }

    public calificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(calificacion calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "tienda{" + "idTienda=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", descripcion=" + descripcion + ", coordenadas=" + coordenadas + ", estado=" + estado + ", direccion=" + direccion + ", calificacion=" + calificacion + '}';
    }

   
    
}
