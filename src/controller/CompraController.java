package controller;

import Utils.Utils;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import entity.Tienda;
import model.CompraModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CompraController {
    public static void create() {

        double iva = 1.19;

        Compra objCompra = new Compra();

        Object[] optionsCliente = Utils.listToArray(ClienteController.instanceModel().findAll());
        Cliente selectedClient = (Cliente) JOptionPane.showInputDialog(null, "Selecciona un cliente", "", JOptionPane.QUESTION_MESSAGE, null, optionsCliente, optionsCliente[0]);

        Object[] optionsProduct = Utils.listToArray(ProductoController.instanceModel().findAll());
        Producto selectedProduct = (Producto) JOptionPane.showInputDialog(null, "Selecciona un producto", "", JOptionPane.QUESTION_MESSAGE, null, optionsProduct, optionsProduct[0]);

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de producto"));


        if (cantidad > selectedProduct.getCantidadProducto()) {
            JOptionPane.showMessageDialog(null, "La cantidad ingresada es mayor al stock, disponible: " + selectedProduct.getCantidadProducto());
            cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de producto"));
            if (cantidad > selectedProduct.getCantidadProducto()) {
                JOptionPane.showMessageDialog(null, "Intentalo de nuevo mas tarde");
            }
        } else {

            objCompra.setIdCliente(selectedClient.getId());
            objCompra.setObjClient(selectedClient);
            objCompra.setIdProducto(selectedProduct.getId());
            objCompra.setObjProduct(selectedProduct);
            objCompra.setCantidad(cantidad);

            instanceModel().insert(objCompra);
            double precioCompleto = (objCompra.getObjProduct().getPrecio() * objCompra.getCantidad()) * iva;
            JOptionPane.showMessageDialog(null,objCompra.bill(precioCompleto));
        }




    }

    public static void update() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Compra compraSelected = (Compra) JOptionPane.showInputDialog(null, "Selecciona una compra a modificar a modificar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Object[] optionsProduct = Utils.listToArray(ProductoController.instanceModel().findAll());
        compraSelected.setObjProduct((Producto) JOptionPane.showInputDialog(null, "Selecciona un producto", "", JOptionPane.QUESTION_MESSAGE, null, optionsProduct, optionsProduct[0]));

        compraSelected.setIdProducto(compraSelected.getObjProduct().getId());

        Object[] optionsClient = Utils.listToArray(ClienteController.instanceModel().findAll());
        compraSelected.setObjClient((Cliente) JOptionPane.showInputDialog(null, "Selecciona un cliente", "", JOptionPane.QUESTION_MESSAGE, null, optionsClient, optionsClient[0]));

        compraSelected.setIdCliente(compraSelected.getObjClient().getId());

        compraSelected.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad", compraSelected.getCantidad())));

        instanceModel().update(compraSelected);
    }

    public static void delete() {
        int confirm = 1;
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Compra productSelected = (Compra) JOptionPane.showInputDialog(null, "Selecciona la compra a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        if (productSelected == null) {
            JOptionPane.showMessageDialog(null, "Compra no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la compra: \n" + productSelected.toString2());

            if (confirm == 0) {
                instanceModel().delete(productSelected);
            } else {
                JOptionPane.showMessageDialog(null, "la compra no se elimino");
            }
        }
    }

    public static void listAll() {
        String listString = listAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String listAll(List<Object> list) {
        String listString = " -- List -- " + "\n";

        for (Object temp : list) {
            Compra obj = (Compra) temp;
            listString += "- " + obj.toString() + "\n";
        }

        return listString;
    }

    public static CompraModel instanceModel() {
        return new CompraModel();
    }

}
