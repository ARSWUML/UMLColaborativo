/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author ger9410
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Clase.class, name = "clase"),
    @JsonSubTypes.Type(value = ClaseAbstracta.class, name = "claseAbstracta"),
    @JsonSubTypes.Type(value = Interface.class, name = "interface")  
})
public abstract class Elemento {
    
   protected String nombre;
   protected int x;
   protected int y;
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
     * Crear el elemento con coordenadas y un nombre especifico
     * @param nombre
     * @param x
     * @param y
     * @throws ProyectoExcepcion 
     */
    public Elemento(String nombre, int x, int y) throws ProyectoExcepcion{
        if(nombre.equals("")) throw new ProyectoExcepcion("Favor colocar un nombre adecuado al elemento");
        this.nombre=nombre;
        this.x=x;
        this.y=y;
    }
    
    /**
     * Crea un elemento
     */
    public Elemento(){
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
