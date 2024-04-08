package controller;

import entity.Cliente;
import entity.Tienda;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class TiendaController {

    public static void listAll(){
        String listString = listAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, listString);
    }

    public static String listAll(List<Object> list){
        String listString = " -- List -- " + "\n";

        for (Object temp : list) {
            Tienda obj = (Tienda) temp;
            listString += "- " + obj.toString() + "\n";
        }

        return listString;
    }

    public static TiendaModel instanceModel(){
        return new TiendaModel();
    }

}
