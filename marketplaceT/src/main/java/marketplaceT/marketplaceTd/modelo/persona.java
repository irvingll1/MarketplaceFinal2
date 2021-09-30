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
public class persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String apellidos;
    private String sexo;
    private Date fechanacimieno;
    private String email;
    private int telefono;
    private int dni;
    @ManyToOne
    @JoinColumn(name="iddireccion")
    private direccion direccion;
    @ManyToOne
    @JoinColumn(name="idusuario")
    private usuario usuario;
    @ManyToOne
    @JoinColumn(name="idtienda")
    private tienda tienda;
    @ManyToOne
    @JoinColumn(name="idtipopersona")
    private tipopersona tipopersona;

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechanacimieno() {
        return fechanacimieno;
    }

    public void setFechanacimieno(Date fechanacimieno) {
        this.fechanacimieno = fechanacimieno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(direccion direccion) {
        this.direccion = direccion;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public tienda getTienda() {
        return tienda;
    }

    public void setTienda(tienda tienda) {
        this.tienda = tienda;
    }

    public tipopersona getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(tipopersona tipopersona) {
        this.tipopersona = tipopersona;
    }

    @Override
    public String toString() {
        return "persona{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sexo=" + sexo + ", fechanacimieno=" + fechanacimieno + ", email=" + email + ", telefono=" + telefono + ", dni=" + dni + ", direccion=" + direccion + ", usuario=" + usuario + ", tienda=" + tienda + ", tipopersona=" + tipopersona + '}';
    }

    
}
