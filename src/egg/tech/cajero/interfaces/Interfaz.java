/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egg.tech.cajero.interfaces;

import egg.tech.cajero.servicios.RedBancaria;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interfaz {

    private BufferedReader teclado;
    private RedBancaria red;

    public Interfaz() {
        this.teclado = new BufferedReader(new InputStreamReader(System.in));
    }

    public BufferedReader getTeclado() {
        return teclado;
    }
    
    
    public void informar(String texto) {
        informar(texto, true);
    }
    
    public void informar(String texto, boolean salto) {
        if(salto){
            System.out.println();
        }
        System.out.println(texto);
        
    }
    
     public String leerCadena() {
        String cadena = null;
        try {
            cadena = teclado.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }

    public Integer leerNumero() {
        String cadena = "0";
        try {
            cadena = teclado.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Integer(cadena);
    }
    
   public Double leerDoble() {
        String cadena = "0";
        try {
            cadena = teclado.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Double(cadena);
    }
    
   
    public void error(String texto) {
        System.err.println(texto);
    }
    
    
    public void limpiar(){
        for(int n = 0; n < 50; n++){
            System.out.println();
        }
    }
}
