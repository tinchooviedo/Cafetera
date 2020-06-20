/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tech.cajero;

import egg.tech.cajero.errores.ErrorConexion;
import egg.tech.cajero.interfaces.Interfaz;
import egg.tech.cajero.servicios.Cajero;
import egg.tech.cajero.servicios.Creador;

/**
 *
 * @author sebastian
 */
public class Ejecutable {

    public static void main(String[] args) throws ErrorConexion {
   
        Integer opcion = 0; 
        Interfaz interfaz = new Interfaz();
        
        System.out.println("Seleccione la opción:");
        System.out.println("=====================================");

        System.out.println(" 1- Creador");
        System.out.println(" 2- Consultas");
        opcion = interfaz.leerNumero();
        
        if(opcion == 1){
        Creador creador = new Creador();
        while(true){
            creador.menu();
        }
        
        }else{
        try {
            Cajero cajero = new Cajero();
            cajero.encender();
        } catch (ErrorConexion ex) {
            ex.printStackTrace();
            System.exit(0);
        }
       
    }
    
    }
}
