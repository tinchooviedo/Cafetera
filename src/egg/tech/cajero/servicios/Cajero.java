package egg.tech.cajero.servicios;

import egg.tech.cajero.interfaces.Interfaz;
import egg.tech.cajero.entidades.Cliente;
import egg.tech.cajero.entidades.CuentaBancaria;
import egg.tech.cajero.enumeraciones.TipoOperacion;
import egg.tech.cajero.errores.ErrorConexion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Cajero {

    private RedBancaria red;
    private Interfaz pantalla;

    private Cliente cliente;
    private CuentaBancaria cuenta;

    public Cajero() throws ErrorConexion {
        try {
            this.pantalla = new Interfaz();
            this.red = new RedBancaria();
        } catch (ErrorConexion ex) {
            pantalla.informar("Cajero Fuera de Servicio. " + ex.getMessage());
            throw ex;
        }
    }

    public void encender() {
        while (true) {
            autenticar();
            menu();
        }
    }

    public void autenticar() {

        while (cliente == null) {

            pantalla.informar("POR FAVOR INGRESE TARJETA:");
            String id = pantalla.leerCadena();

            pantalla.informar("POR FAVOR INGRESE SU CLAVE:");
            String clave = pantalla.leerCadena();

            if (id != null) {
                cliente = red.buscarCliente(id);
                if (cliente != null) {
                    if (cliente.getClave().equals(clave)) {
                        pantalla.informar("USUARIO AUTENTICADO");
                        pantalla.informar("BIENVENIDO " + cliente.getNombre() + " " + cliente.getApellido());

                        if (cliente.getCuentas().size() > 1) {
                            int opcion = 0;
                            pantalla.informar("ELIJA LA CUENTA CON LA QUE VA A OPERAR: ");
                            for (CuentaBancaria cuentaBancaria : cliente.getCuentas()) {
                                pantalla.informar(opcion++ + " - " + cuentaBancaria.getId() + " SALDO: " + cuentaBancaria.getSaldo(), false);
                            }

                            boolean answer = true;
                            while (answer) {

                                opcion = pantalla.leerNumero();

                                if (opcion >= 0 && opcion <= cliente.getCuentas().size()) {

                                    answer = false;
                                } else {
                                    System.out.println("Ingrese el numero de la cuenta");

                                }

                            }

                            cuenta = cliente.getCuentas().get(opcion);
                            pantalla.informar("CUENTA ELEGIDA: " + cuenta.getId());
                            
                        }else{
                        
                        cuenta = cliente.getCuentas().get(0);
                        }

                    } else {
                        pantalla.informar("ACCESO DENEGADO");
                        cliente = null;
                        cuenta = null;
                    }
                }
            }
        }
    }

    public void menu() {
        TipoOperacion tipo = null;
        while (tipo != TipoOperacion.SALIR) {
            pantalla.informar("¿QUE OPERACION DESEA REALIZAR?", true);
            pantalla.informar(" 1- CONSULTA DE SALDO", false);
            pantalla.informar(" 2- DEPOSITO", false);
            pantalla.informar(" 3- EXTRACCION", false);
            pantalla.informar(" 0- SALIR", false);

            int operacion = pantalla.leerNumero();
            if (operacion >= 0 && operacion <= 3) {
                tipo = TipoOperacion.values()[operacion];
                switch (tipo) {
                    case CONSULTA:
                        consultar();
                        break;
                    case DEPOSITO:
                        depositar();
                        break;
                    case EXTRACION:
                        extraer();
                        break;
                    case SALIR:
                        salir();
                        break;
                }
            }
        }
    }

    public void extraer() {
        pantalla.informar("INGRESE EL MONTO A EXTRAER: ");
        double saldo = pantalla.leerNumero();
        if (saldo > cuenta.getSaldo()) {
            pantalla.error("EL MONTO QUE INTENTA EXTRAER ES SUPERIOR AL SALDO DISPONIBLE EN SU CUENTA.");
        } else {
            red.extraer(cuenta, saldo);
        }
    }

    public void consultar() {
        pantalla.informar("EL SALDO DE LA CUENTA ES: " + cuenta.getSaldo());
    }

    public void depositar() {
        pantalla.informar("INGRESE EL MONTO A DEPOSITAR: ");
        double saldo = pantalla.leerNumero();
        if (saldo <= 0) {
            pantalla.error("EL MONTO QUE INTENTA DEPOSITAR ES MENOR O IGUAL A CERO.");
        } else {
            red.depositar(cuenta, saldo);
        }
    }

    public void salir() {
        this.cliente = null;
        this.cuenta = null;
        this.pantalla.limpiar();
    }

}
