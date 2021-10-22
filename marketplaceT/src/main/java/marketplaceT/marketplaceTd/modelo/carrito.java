
package marketplaceT.marketplaceTd.modelo;


public class carrito {
    public int id;
    public String nombre;
    public String tipopago;
    public String fecha;
    public double total;

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

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "carrito{" + "id=" + id + ", nombre=" + nombre + ", tipopago=" + tipopago + ", fecha=" + fecha + ", total=" + total + '}';
    }

    

   
            
            
}
