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
public class distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDistrito;
    
    private String descripcion;
    private int idProvincia;

    public distrito() {
    }

    public distrito(int idDistrito, String descripcion, int idProvincia) {
        this.idDistrito = idDistrito;
        this.descripcion = descripcion;
        this.idProvincia = idProvincia;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
    
    
    
}
