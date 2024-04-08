package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Compra objCompra = (Compra) object;

        try {
            String sql = "INSERT INTO compra ( cantidad, id_cliente, id_producto) VALUES ( ?, ?, ? );";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objCompra.getCantidad());
            objPrepare.setInt(2, objCompra.getIdCliente());
            objPrepare.setInt(3, objCompra.getIdProducto());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCompra.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Producto agregado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCompra;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listCompras = new ArrayList<>();

        try {
            String sql = "SELECT * FROM compra INNER JOIN cliente on cliente.id = compra.id_cliente INNER JOIN producto on producto.id = compra.id_producto INNER JOIN tienda on tienda.id = producto.id_tienda;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();
                Compra objCompra = new Compra();
                Tienda objTienda = new Tienda();

                objCompra.setId(objResult.getInt("compra.id"));
                objCompra.setIdProducto(objResult.getInt("compra.id_producto"));
                objCompra.setIdCliente(objResult.getInt("compra.id_cliente"));
                objCompra.setFec_compra(objResult.getTimestamp("compra.fecha_compra"));
                objCompra.setCantidad(objResult.getInt("compra.cantidad"));

                objCliente.setId(objResult.getInt("cliente.id"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setCantidadProducto(objResult.getInt("producto.stock"));
                objProducto.setIdTienda(objResult.getInt("producto.id_tienda"));

                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setObjTienda(objTienda);

                objCompra.setObjProduct(objProducto);
                objCompra.setObjClient(objCliente);

                listCompras.add(objCompra);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();

        return listCompras;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
