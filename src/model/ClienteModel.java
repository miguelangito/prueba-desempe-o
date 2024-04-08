package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {
    @Override
    public Object insert(Object object) {

        Connection objConnection = ConfigDB.openConnection();

        Cliente objCliente = (Cliente) object;

        try {

            String sql = "INSERT INTO cliente (nombre, apellido, email) VALUES ( ? , ? , ? )";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,((Cliente) object).getNombre());
            objPrepare.setString(2,((Cliente) object).getApellido());
            objPrepare.setString(3,((Cliente) object).getEmail());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCliente.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();

        Cliente objCliente = (Cliente) object;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ? WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3,objCliente.getEmail());
            objPrepare.setInt(4,objCliente.getId());

            int totalRowsAffected = objPrepare.executeUpdate();

            if (totalRowsAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El cliente se actualizo correctamente");
            }else {
                JOptionPane.showMessageDialog(null,"El cliente no se actualizo");
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
            String sql = "DELETE FROM cliente WHERE id = ?";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, ((Cliente) object).getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El cliente se elimino correctamente");
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

        List<Object> listClientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Cliente objCliente = new Cliente();

                objCliente.setId(objResult.getInt("id"));
                objCliente.setEmail(objResult.getString("email"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));

                listClientes.add(objCliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();

        return listClientes;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
