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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author PC
 */
@Entity
public class persona implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellidos;
    @NotEmpty
    private String sexo;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @NotEmpty
    private Date fechanacimieno;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp="[0-9]{9}")
    private int telefono;
    @NotEmpty
    @Pattern(regexp="[0-9]{9}")
    private int dni;
    @NotEmpty
    private String direccion;
    @ManyToOne
    @JoinColumn(name="idusuario")
    private usuario usuario;
    @ManyToOne
    @JoinColumn(name="idtienda")
    private tienda tienda;
    @ManyToOne
    @JoinColumn(name="iddistrito")
    @NotEmpty
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
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


    public distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(distrito distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return "persona{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sexo=" + sexo + ", fechanacimieno=" + fechanacimieno + ", email=" + email + ", telefono=" + telefono + ", dni=" + dni + ", direccion=" + direccion + ", usuario=" + usuario + ", tienda=" + tienda + ", distrito=" + distrito + '}';
    }

    
    
}
