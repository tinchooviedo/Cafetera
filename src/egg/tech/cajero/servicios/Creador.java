
package egg.tech.cajero.servicios;

import egg.tech.cajero.entidades.CuentaBancaria;
import egg.tech.cajero.errores.ErrorConexion;
import egg.tech.cajero.interfaces.Interfaz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Creador {

    private Interfaz pantalla;
    private RedBancaria red;

     public Creador() throws ErrorConexion{
        try {
            this.pantalla = new Interfaz();
            this.red = new RedBancaria();
        } catch (ErrorConexion ex) {
            pantalla.informar("Cajero Fuera de Servicio. " + ex.getMessage());
            throw ex;
        }
    }

    public void menu() {
        System.out.println("Seleccione la opción:");
        System.out.println("=====================================");

        System.out.println(" 1- Crear Banco");
        System.out.println(" 2- Crear Cuenta Bancaria");
        System.out.println(" 3- Crear Cliente");
        System.out.println(" 4- Agregar cuentas");

        System.out.println(" 0- Salir");

        int opcion = pantalla.leerNumero();
        switch (opcion) {
            case 1:
                crearBanco();
                break;
            case 2:
                crearCuenta();
                break;
            case 3:
                crearCliente();
                break;
            case 4:    
                agregarCuentas();
            case 0:
                System.exit(-1);
                break;

        }
    }
    
    public void crearBanco(){
        
        System.out.println("Ingrese el nombre del banco");
        String nombre = pantalla.leerCadena();
        
        red.crearBanco(nombre);
        
    }
    
    public void crearCuenta(){
        System.out.println("Ingrese su saldo");
        Double saldo = pantalla.leerDoble();
        
        red.crearCuenta(saldo);
        
    }
    
    public void crearCliente(){
      
        List<String> cuentas = new ArrayList<>();
        
       
        System.out.println("Ingrese su nombre");
        String nombre = pantalla.leerCadena();
        
        System.out.println("Ingrese su apellido");
        String apellido = pantalla.leerCadena();
        
        System.out.println("Ingrese su documento");
        Integer documento = pantalla.leerNumero();
        
        System.out.println("Ingrese su usuario");
        String usuario = pantalla.leerCadena();
        
        System.out.println("Ingrese su clave");
        String clave = pantalla.leerCadena();
        
        System.out.println("Ingrese el id del banco");
        String idBanco = pantalla.leerCadena();
        
        System.out.println("Ingrese la cantidad de cuentas que quiere ingresar");
        Integer cantidad = pantalla.leerNumero();
        
        for (int i = 0; i < cantidad; i++){
        
        System.out.println("Ingrese el id de la cuenta");
        String idCuenta = pantalla.leerCadena();
        cuentas.add(idCuenta);
        }
        
        red.crearCliente(nombre, apellido, documento, usuario, clave, idBanco, cuentas);
        
    }
    
    
    public void agregarCuentas(){
        List<String> cuentas = new ArrayList<>();
            
         System.out.println("Ingrese la tarjeta");
         String id = pantalla.leerCadena();
             
        System.out.println("Ingrese el id de la cuenta");
        String idCuenta = pantalla.leerCadena();
        cuentas.add(idCuenta);
        
        red.agregarCuenta(cuentas, id);
    }
}
