package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Producto objProduct = (Producto) object;

        try {
            String sql = "INSERT INTO producto ( nombre, precio,stock, id_tienda) VALUES ( ?, ?, ?, ? );";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objProduct.getNombre());
            objPrepare.setDouble(2, objProduct.getPrecio());
            objPrepare.setInt(3,objProduct.getCantidadProducto());
            objPrepare.setInt(4, objProduct.getIdTienda());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objProduct.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Producto agregado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objProduct;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Producto objProduct = (Producto) object;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE producto SET nombre = ?, precio = ?, cantidad = ?, id_tienda = ? WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objProduct.getNombre());
            objPrepare.setDouble(2,objProduct.getPrecio());
            objPrepare.setInt(3,objProduct.getCantidadProducto());
            objPrepare.setInt(4,objProduct.getIdTienda());
            objPrepare.setInt(5,objProduct.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El producto se actualizo correctamente");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        boolean isDeleted = false;

        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "DELETE FROM producto WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, ((Producto) object).getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El producto se elimino correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listProductos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto INNER JOIN tienda on tienda.id = producto.id_tienda;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setIdTienda(objResult.getInt("producto.id_tienda"));
                objProducto.setCantidadProducto(objResult.getInt("producto.stock"));

                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setObjTienda(objTienda);

                listProductos.add(objProducto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();

        return listProductos;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
