/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketplace.s.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author PC
 */
@Entity
public class atencionpedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date Fechapedido;
    private int idpedido;
    private int orden;
    private int idpersona;

    public atencionpedido() {
    }

    public atencionpedido(int id, Date Fechapedido, int idpedido, int orden, int idpersona) {
        this.id = id;
        this.Fechapedido = Fechapedido;
        this.idpedido = idpedido;
        this.orden = orden;
        this.idpersona = idpersona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechapedido() {
        return Fechapedido;
    }

    public void setFechapedido(Date Fechapedido) {
        this.Fechapedido = Fechapedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }
    
    
    
}
