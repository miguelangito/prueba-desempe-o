package controller;

import Utils.Utils;
import entity.Cliente;
import model.ClienteModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {

    public static void create(){
        Cliente objCliente = new Cliente();

        String nombre = JOptionPane.showInputDialog(null,"Ingrese el nombre del cliente");
        String apellido = JOptionPane.showInputDialog(null,"Ingrese el apellido del cliente");
        String email = JOptionPane.showInputDialog(null,"Ingrese el email del cliente");

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(email);


        instanceModel().insert(objCliente);
    }

    public static void delete(){
        int confirm = 1;
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Cliente clientSelected = (Cliente) JOptionPane.showInputDialog(null, "Selecciona el cliente a eliminar", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


        if (clientSelected == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar el cliente: \n" + clientSelected);

            if (confirm == 0) {
                instanceModel().delete(clientSelected);
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no se elimino");
            }
        }
    }

    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Cliente clientSelected = (Cliente) JOptionPane.showInputDialog(null, "Select the Appointment to delete", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        clientSelected.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre" , clientSelected.getNombre()));
        clientSelected.setApellido(JOptionPane.showInputDialog("Ingrese el nuevo apellido" , clientSelected.getApellido()));
        clientSelected.setEmail(JOptionPane.showInputDialog("Ingrese el nuevo email" , clientSelected.getEmail()));

        instanceModel().update(clientSelected);
    }

    public static void listAll(){
        String listString = listAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String listAll(List<Object> list){
        String listString = " -- List -- " + "\n";

        for (Object temp : list) {
            Cliente obj = (Cliente) temp;
            listString += "- " + obj.toString() + "\n";
        }

        return listString;
    }

    public static ClienteModel instanceModel(){
        return new ClienteModel();
    }
}
