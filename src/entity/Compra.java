package entity;

import java.sql.Timestamp;

public class Compra {

    public int id;
    public Integer idCliente;
    public Cliente objClient;
    public Integer idProducto;
    public Producto objProduct;
    public Timestamp fec_compra;
    public int cantidad;

    public Compra() {
    }

    public Compra(int id, Integer idCliente, Cliente objCliente, Integer idProducto, Producto objProduct, Timestamp fec_compra, int cantidad) {
        this.id = id;
        this.idCliente = idCliente;
        this.objClient = objCliente;
        this.idProducto = idProducto;
        this.objProduct = objProduct;
        this.fec_compra = fec_compra;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getObjClient() {
        return objClient;
    }

    public void setObjClient(Cliente objClient) {
        this.objClient = objClient;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto getObjProduct() {
        return objProduct;
    }

    public void setObjProduct(Producto objProduct) {
        this.objProduct = objProduct;
    }

    public Timestamp getFec_compra() {
        return fec_compra;
    }

    public void setFec_compra(Timestamp fec_compra) {
        this.fec_compra = fec_compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n" +
                " idCliente: " + idCliente + "\n" +
                " objCliente: " + objClient + "\n" +
                " idProducto: " + idProducto + "\n" +
                " objCompra: " + objProduct + "\n" +
                " fec_compra: " + fec_compra + "\n" +
                " cantidad:" + cantidad + "\n"
                +"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    }

    public String toString2() {
        return  "id: " + id +
                ", idProducto: " + idProducto +
                ", objProduct: " + objProduct +
                ", fec_compra: " + fec_compra +
                ", cantidad: " + cantidad;
    }

    public <valor> String bill(valor valor){
        return  "nombre producto: " + objProduct.getNombre() + " Precio completo: " +valor + "\n" +
                " Nombre tienda: " + objProduct.getObjTienda().getNombre() + " Ubicación tienda: " + objProduct.getObjTienda().getUbicacion() + "\n" +
                " Nombre cliente: " + objClient.getNombre() + " Apellido: " + objClient.getApellido() + " Email: " + objClient.getEmail();
    }
}
