package entity;

public class Tienda {

    public int id;
    public String nombre;
    public String ubicacion;

    public Tienda() {
    }

    public Tienda(int id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return  "id: " + id +
                ", nombre: " + nombre +
                ", ubicacion: " + ubicacion;
    }
}
