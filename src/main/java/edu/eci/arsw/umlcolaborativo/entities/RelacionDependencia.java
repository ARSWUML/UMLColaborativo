/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.util.HashMap;

/**
 *
 * @author Daniela Sepulveda
 */
public class RelacionDependencia extends Relacion{
    
    private HashMap<String,Integer> multiplicidad;
    
    /**
     * Creacion de la clase 
     */
    public RelacionDependencia(){
         multiplicidad=new HashMap<>();
         isbidireccional=false; 
    }
    
    public RelacionDependencia(String nombre){
        super(nombre);
        multiplicidad=new HashMap<>();
        isbidireccional=false;
    }
     /**
     * Creacion de la clase Relacion dependencia 
     * @param A
     * @param B
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
     public RelacionDependencia(Elemento A, Elemento B, String nombre) throws ProyectoExcepcion{
        super(A,B,nombre);
        multiplicidad=new HashMap<>();
        multiplicidad.put(A.getNombre(), 1);
        multiplicidad.put(B.getNombre(), 1);
        isbidireccional=false;   
     }
      /**
      * Consultar la multiplicidad de la relacion
      * @return 
      */
    public HashMap<String,Integer> getMultiplicidad() {
        return multiplicidad;
    }
     /**
      * Enviar multiplicidad
      * @param multiplicidad
      */
    public void setMultiplicidad(HashMap<String,Integer> multiplicidad) {
        this.multiplicidad = multiplicidad;
    }
}
