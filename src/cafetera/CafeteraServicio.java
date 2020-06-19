/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafetera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MartinOviedo
 */
public class CafeteraServicio {

    Scanner scan = new Scanner(System.in);
    
    List<Cafetera> cafeteras = new ArrayList();
   
    public void crearCafetera(){
        
        int cantidadActual = scan.nextInt();
        int capacidadMaxima = scan.nextInt();
        
        Cafetera c1 = new Cafetera();
        
        c1.setCantidadActual(capacidadMaxima);
        c1.setCantidadActual(cantidadActual);
       
        
        cafeteras.add(c1);
        
        
    }
    
    
    

    public void llenarCafetera() {
        
        for (Cafetera cafetera : cafeteras) {
            
            if(cafetera.getCantidadActual() == 100){
                
                
            }
              
        }

    }

    
}
