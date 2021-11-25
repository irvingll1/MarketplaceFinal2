/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplaceT.marketplaceTd.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PC
 */
@Entity
public class atencionpedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date Fechapedido;
    @ManyToOne
    @JoinColumn(name="idpedido")
    private pedido pedido;
    private int orden;
    @ManyToOne
    @JoinColumn(name="idpersona")
    private persona persona;


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

    public pedido getPedido() {
        return pedido;
    }

    public void setPedido(pedido pedido) {
        this.pedido = pedido;
    }

    

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public persona getPersona() {
        return persona;
    }

    public void setPersona(persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "atencionpedido{" + "id=" + id + ", Fechapedido=" + Fechapedido + ", pedido=" + pedido + ", orden=" + orden + ", persona=" + persona + '}';
    }

    
    
    
    
}
