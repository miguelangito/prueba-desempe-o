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

    public static void create(){
        Compra objCompra = new Compra();

        Object[] optionsCliente = Utils.listToArray(ClienteController.instanceModel().findAll());
        Cliente selectedClient = (Cliente) JOptionPane.showInputDialog(null, "Selecciona un cliente", "", JOptionPane.QUESTION_MESSAGE, null, optionsCliente, optionsCliente[0]);

        Object[] optionsProduct = Utils.listToArray(ProductoController.instanceModel().findAll());
        Producto selectedProduct = (Producto) JOptionPane.showInputDialog(null, "Selecciona un producto", "", JOptionPane.QUESTION_MESSAGE, null, optionsProduct, optionsProduct[0]);

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor del producto"));

        objCompra.setIdCliente(selectedClient.getId());
        objCompra.setObjClient(selectedClient);
        objCompra.setIdProducto(selectedProduct.getId());
        objCompra.setObjProduct(selectedProduct);
        objCompra.setCantidad(cantidad);

        instanceModel().insert(objCompra);
    }

    public static void listAll(){
        String listString = listAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String listAll(List<Object> list){
        String listString = " -- List -- " + "\n";

        for (Object temp : list) {
            Compra obj = (Compra) temp;
            listString += "- " + obj.toString() + "\n";
        }

        return listString;
    }

    public static CompraModel instanceModel(){
        return new CompraModel();
    }

}
