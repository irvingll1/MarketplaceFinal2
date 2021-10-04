/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author PC
 */
@Entity
public class direccion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String descripcion;
    @ManyToOne
    @JoinColumn(name="iddistrito")
    private distrito distrito;

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(distrito distrito) {
        this.distrito = distrito;
    }

    public direccion() {
    }

    public direccion(int id, String descripcion, distrito distrito) {
        this.id = id;
        this.descripcion = descripcion;
        this.distrito = distrito;
    }
    
    

    @Override
    public String toString() {
        return "direccion{" + "id=" + id + ", descripcion=" + descripcion + ", distrito=" + distrito + '}';
    }

    
    
    
    
}
