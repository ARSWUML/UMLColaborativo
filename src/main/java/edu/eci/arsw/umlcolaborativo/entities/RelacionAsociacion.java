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
public class RelacionAsociacion extends Relacion{
    
    private HashMap<Clase,int[]> multiplicidad;
    private String atributoB;
    private String atributoA;
    
    /**
     * Creacion relacion asociacion
    */
    public RelacionAsociacion(){
          multiplicidad=new HashMap<>();
          isbidireccional=true; 
          atributoB = "";
          atributoA = "";
    }
    
    public RelacionAsociacion(String nombre){
        super(nombre);
        multiplicidad=new HashMap<>();
        isbidireccional=true; 
    }
    /**
     * Creacion de la clase Asociacion 
     * @param A
     * @param B
     * @param nombre
     * @param multA
     * @param multB
     * @param atributoA
     * @param atributoB
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
     public RelacionAsociacion(Clase A, Clase B, String nombre, int[] multA, int[] multB, String atributoA,String atributoB) throws ProyectoExcepcion{
        super(A,B,nombre);
        multiplicidad=new HashMap<>();
        multiplicidad.put(A, multA);
        multiplicidad.put(B, multB);
        isbidireccional=true;   
        this.atributoB = atributoB;
        this.atributoA = atributoA;
     }
     /**
      * Consultar la multiplicidad de la relacion
      * @return 
      */
    public HashMap<Clase,int[]> getMultiplicidad() {
        return multiplicidad;
    }
     /**
      * Enviar multiplicidad
      * @param multiplicidad
      */
    public void setMultiplicidad(HashMap<Clase,int[]> multiplicidad) {
        this.multiplicidad = multiplicidad;
    }
     /**
      * Consultar nombre del atributo en el elemento B
      * @return 
      */
    public String getAtributoB() {
        return atributoB;
    }
     /**
      * Enviar nombre del atributo para el elemento B
      * @param atributoB
      */
    public void setAtributoB(String atributoB) {
        this.atributoB = atributoB;
    }
     /**
      * Consultar nombre del atributo en el elemento A
      * @return 
      */
    public String getAtributoA() {
        return atributoA;
    }
     /**
      * Enviar nombre del atributo para el elemento A
      * @param atributoA
      */
    public void setAtributoA(String atributoA) {
        this.atributoA = atributoA;
    }
     
}
