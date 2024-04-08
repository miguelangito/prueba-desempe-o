package entity;

import java.text.DecimalFormat;

public class Producto {

    public int id;
    public String nombre;
    public double precio;
    public Integer idTienda;
    public Tienda objTienda;
    public int cantidadProducto;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, Integer idTienda, Tienda objTienda, int cantidadProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idTienda = idTienda;
        this.objTienda = objTienda;
        this.cantidadProducto = cantidadProducto;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                ", nombre: " + nombre +
                ", precio: " + precio +
                ", cantidad: " + cantidadProducto +
                ", idTienda: " + idTienda +
                ", objTienda: " + objTienda;
    }
}
