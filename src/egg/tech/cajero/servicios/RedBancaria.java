package egg.tech.cajero.servicios;

import egg.tech.cajero.entidades.Banco;
import egg.tech.cajero.entidades.Cliente;
import egg.tech.cajero.entidades.CuentaBancaria;
import egg.tech.cajero.entidades.Operacion;
import egg.tech.cajero.errores.ErrorConexion;
import egg.tech.cajero.errores.ErrorOperacion;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class RedBancaria {

    public void crearBanco(String nombre) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        Banco banco = new Banco();
        banco.setId(UUID.randomUUID().toString());
        banco.setNombre(nombre);

        em.persist(banco);
        em.getTransaction().commit();
    }

    public void crearCuenta(Double saldo) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setId(UUID.randomUUID().toString());
        cuenta.setSaldo(saldo);

        em.persist(cuenta);
        em.getTransaction().commit();
    }

    public void crearCliente(String nombre, String apellido, Integer documento, String usuario, String clave, String idBanco, List<String> cuentas) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID().toString());
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);
        cliente.setUsuario(usuario);
        cliente.setClave(clave);

        Banco banco = em.find(Banco.class, idBanco);
        cliente.setBanco(banco);

        for (String id : cuentas) {

            CuentaBancaria cuenta = em.find(CuentaBancaria.class, id);
            cliente.addCuentas(cuenta);

        }

        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarCliente(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();

        Cliente cliente = em.find(Cliente.class, id);

        return cliente;

    }

    public RedBancaria() throws ErrorConexion {

    }

    public void extraer(CuentaBancaria cuenta, Double saldo) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        cuenta.setSaldo(-saldo);
        
        
        Operacion operacion = new Operacion();
        operacion.setId(UUID.randomUUID().toString());
        operacion.setFecha(new Date());
        operacion.setTipo("Extracción");
        operacion.setMonto(saldo);
        operacion.setCuenta(cuenta);
       

        em.merge(cuenta);
        em.persist(operacion);
        em.getTransaction().commit();
    }

    public void depositar(CuentaBancaria cuenta, Double saldo) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        cuenta.setSaldo(+saldo);
        
        
        Operacion operacion = new Operacion();
        operacion.setId(UUID.randomUUID().toString());
        operacion.setFecha(new Date());
        operacion.setTipo("Deposito");
        operacion.setMonto(saldo);
        operacion.setCuenta(cuenta);
       
        

        em.merge(cuenta);
        em.persist(operacion);
        em.getTransaction().commit();
    }

    public void agregarCuenta(List<String> cuentas, String id) {
        EntityManager em = Persistence.createEntityManagerFactory("CajeroAutomaticoPU").createEntityManager();
        em.getTransaction().begin();

        Cliente cliente = em.find(Cliente.class, id);

        for (String idC : cuentas) {

            CuentaBancaria cuenta = em.find(CuentaBancaria.class, idC);
            cliente.addCuentas(cuenta);

        }

        em.merge(cliente);
        em.getTransaction().commit();

    }

}
