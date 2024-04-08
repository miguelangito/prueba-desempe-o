import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import database.ConfigDB;
import entity.Producto;

import javax.swing.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        String option;
        do {
            option = JOptionPane.showInputDialog(""" 
                    Elige una opcion.
                    1. Administrar clientes.
                    2. Administrar compras.
                    3. administrar producto.
                    4. Salir.
                    """);
            switch (option){
                case "1":
                    do {
                        JOptionPane.showMessageDialog(null,"Bienvenido al administrador de clientes");
                        option = JOptionPane.showInputDialog("""
                                Elige una opcion.
                                1. Añadir nuevo cliente.
                                2. Eliminar cliente.
                                3. Actualizar clientes.
                                4. Mostrar todos los clientes.
                                5. Salir.
                                """);
                        switch (option){
                            case "1":
                                ClienteController.create();
                                break;
                            case "2":
                                ClienteController.delete();
                                break;
                            case "3":
                                ClienteController.update();
                                break;
                            case "4":
                                ClienteController.listAll();
                                break;
                        }
                    }while (!Objects.equals(option, "5"));
                    break;
                case "2":
                    do {
                        JOptionPane.showMessageDialog(null,"Bienvenido al administrador de compras");
                        option = JOptionPane.showInputDialog("""
                            Elige una opcion.
                            1. Agregar una compra.
                            2. Modificar una compra.
                            3. Eliminar una compra.
                            4. Mostrar todas las compras.
                            5. Salir.
                            """);
                        switch (option){
                            case "1":
                                CompraController.create();
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                CompraController.listAll();
                                break;
                        }
                    }while (!Objects.equals(option,"5"));
                    break;
                case "3":
                    do {
                        JOptionPane.showMessageDialog(null, "Bienvenido al administrador de productos");
                        option = JOptionPane.showInputDialog("""
                                 Elige una opcion.
                                 1. Agregar un nuevo producto.
                                 2. Modificar un producto.
                                 3. Eliminar un producto.
                                 4. Mostrar todos los productos.
                                 5. Salir.
                                 """);
                        switch (option){
                            case "1":
                                ProductoController.create();
                                break;
                            case "2":
                                ProductoController.update();
                                break;
                            case "3":
                                ProductoController.delete();
                                break;
                            case "4":
                                ProductoController.listAll();
                                break;
                        }
                    }while (!Objects.equals(option, "5"));
                    break;
            }
        }while (!option.equals("4"));

    }
}