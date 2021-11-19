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
public class pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapedido;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaenvio;
    private int estado;
    @ManyToOne
    @JoinColumn(name="idtipopago")
    private tipopago tipopago;
    
    @ManyToOne
    @JoinColumn(name="idvendedor")
    private persona  persona;
    @ManyToOne
    @JoinColumn(name="idtienda")
    private tienda tienda;

    private int cantidad;
    private double total;

    public Date getFechapedido() {
        return fechapedido;
    }

    public void setFechapedido(Date fechapedido) {
        this.fechapedido = fechapedido;
    }

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public tipopago getTipopago() {
        return tipopago;
    }

    public void setTipopago(tipopago tipopago) {
        this.tipopago = tipopago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public persona getPersona() {
        return persona;
    }

    public void setPersona(persona persona) {
        this.persona = persona;
    }

    public tienda getTienda() {
        return tienda;
    }

    public void setTienda(tienda tienda) {
        this.tienda = tienda;
    }
    
    

    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "pedido{" + "id=" + id + ", fechapedido=" + fechapedido + ", fechaenvio=" + fechaenvio + ", estado=" + estado + ", tipopago=" + tipopago + ", persona=" + persona + ", tienda=" + tienda + ", cantidad=" + cantidad + ", total=" + total + '}';
    }

   

}
