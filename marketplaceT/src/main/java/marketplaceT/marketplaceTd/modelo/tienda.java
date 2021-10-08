/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.modelo;

import java.io.Serializable;
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
public class tienda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private int telefono;
    private String descripcion;
    private String latitud;
    private String longitud; 
    private int estado;
    private String direccion;
    @ManyToOne
    @JoinColumn(name="iddistrito")
    private distrito distrito;

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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

  
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(distrito distrito) {
        this.distrito = distrito;
    }

    public tienda(int id,String nombre, int telefono, String descripcion, String latitud, String longitud, int estado, String direccion, distrito distrito) {
        this.id=id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.direccion = direccion;
        this.distrito = distrito;
    }

    public tienda() {
    }
    
    @Override
    public String toString() {
        return "tienda{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", descripcion=" + descripcion + ", latitud=" + latitud + ", longitud=" + longitud + ", estado=" + estado + ", direccion=" + direccion + ", distrito=" + distrito + '}';
    }
    
}
