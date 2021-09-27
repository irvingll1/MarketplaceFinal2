/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace.s.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author PC
 */
@Entity
public class direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddireccion;
    
    private String descripcion;
    private int iddistrito;

    public direccion() {
    }

    public direccion(int iddireccion, String descripcion, int iddistrito) {
        this.iddireccion = iddireccion;
        this.descripcion = descripcion;
        this.iddistrito = iddistrito;
    }

    public int getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(int iddistrito) {
        this.iddistrito = iddistrito;
    }
    
    
    
}
