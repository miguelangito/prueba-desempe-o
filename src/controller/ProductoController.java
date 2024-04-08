package controller;

import Utils.Utils;
import entity.Cliente;
import entity.Producto;
import entity.Tienda;
import model.ProductoModel;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class ProductoController {

    public static void create(){
        Producto objProducto = new Producto();

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del producto"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de stock que hay del producto"));
        Object[] optionsTienda = Utils.listToArray(TiendaController.instanceModel().findAll());
        Tienda selectedProduct = (Tienda) JOptionPane.showInputDialog(null, "Selecciona una tienda", "", JOptionPane.QUESTION_MESSAGE, null, optionsTienda, optionsTienda[0]);


        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setCantidadProducto(stock);
        objProducto.setIdTienda(selectedProduct.getId());
        objProducto.setObjTienda(selectedProduct);

        instanceModel().insert(objProducto);
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Producto productSelected = (Producto) JOptionPane.showInputDialog(null, "Selecciona un producto a modificar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        productSelected.setNombre(JOptionPane.showInputDialog("Ingresa el nuevo nombre del producto"));
        productSelected.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingresa el nuevo precio del producto")));

        Object[] optionsShop = Utils.listToArray(TiendaController.instanceModel().findAll());
        productSelected.setObjTienda((Tienda) JOptionPane.showInputDialog(null, "Selecciona una tienda", "", JOptionPane.QUESTION_MESSAGE, null, optionsShop, optionsShop[0]));

        productSelected.setIdTienda(productSelected.getObjTienda().getId());

        instanceModel().update(productSelected);
    }

    public static void delete(){
        int confirm = 1;
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Producto productSelected = (Producto) JOptionPane.showInputDialog(null, "Selecciona el Producto a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        if (productSelected == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar el Producto: \n" + productSelected);

            if (confirm == 0) {
                instanceModel().delete(productSelected);
            } else {
                JOptionPane.showMessageDialog(null, "El Producto no se elimino");
            }
        }
    }

    public static void listAll(){
        String listString = listAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String listAll(List<Object> list){
        String listString = " -- List -- " + "\n";

        for (Object temp : list) {
            Producto obj = (Producto) temp;
            listString += "- " + obj.toString() + "\n";
        }

        return listString;
    }

    public static ProductoModel instanceModel(){
        return new ProductoModel();
    }

}
