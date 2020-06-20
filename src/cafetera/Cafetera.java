/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafetera;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MartinOviedo
 */
public class Cafetera {

    private int capacidadMaxima;
    private int cantidadActual;
   

    public Cafetera() {

        this.capacidadMaxima = 1000;
        this.cantidadActual = 0;

    }

    public Cafetera(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadActual = capacidadMaxima;
    }

    public Cafetera(int capacidadMaxima, int cantidadActual) {
        if (cantidadActual > capacidadMaxima) {
            cantidadActual = capacidadMaxima;
        }
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadActual = cantidadActual;

    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    
    
    
    
    
}
