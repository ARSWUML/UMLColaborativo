/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

/**
 *
 * @author ger9410
 */
public abstract class Elemento {
    
   protected String nombre;
   /**
    * Crear el elemento con el nombre especificado
    * @param nombre
    */
    
    /**
     * Crear el elemento con el nombre especificado
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Elemento(String nombre) throws ProyectoExcepcion{
       if(nombre.equals("")) throw new ProyectoExcepcion("Favor colocar un nombre adecuado al elemento");
       this.nombre=nombre;
   }
   /**
    * Consultar nombre elemento
     * @return nombre
    */
   
   public String getNombre(){
       return nombre;
   }
   /**
    * Enviar un nombre al elemento
    * @param nombre
    **/
   public void setNombre(String nombre){
       this.nombre=nombre;
   }
}
