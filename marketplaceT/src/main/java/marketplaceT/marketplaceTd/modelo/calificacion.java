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
public class calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcalifica;
    
    private int numero;
    private int cometario;
    private int idpersona;
    private Date fecha;

    public calificacion() {
    }

    public calificacion(int idcalifica, int numero, int cometario, int idpersona, Date fecha) {
        this.idcalifica = idcalifica;
        this.numero = numero;
        this.cometario = cometario;
        this.idpersona = idpersona;
        this.fecha = fecha;
    }

    public int getIdcalifica() {
        return idcalifica;
    }

    public void setIdcalifica(int idcalifica) {
        this.idcalifica = idcalifica;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCometario() {
        return cometario;
    }

    public void setCometario(int cometario) {
        this.cometario = cometario;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
