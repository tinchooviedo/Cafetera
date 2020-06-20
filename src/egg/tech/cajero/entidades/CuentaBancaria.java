package egg.tech.cajero.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CuentaBancaria {
    
    @Id
    private String id;
    private double saldo;

    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo += saldo;
    }
}
