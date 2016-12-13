/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

/**
 *
 * @author German Lopez
 */
public class ClaseAbstracta extends Clase{
    /**
     * Constructor de la claseAbstracta 
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public ClaseAbstracta(String nombre) throws ProyectoExcepcion {
        super(nombre);
    }
    /**
     * Constructor de la claseAbstracta 
     */
    public ClaseAbstracta(){
        super();
    }
    /**
     * Constructor de la claseAbstracta 
     * @param nombre
     * @param x
     * @param y
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public ClaseAbstracta(String nombre, int x, int y) throws ProyectoExcepcion{
        super(nombre,x,y);
    }
    
}
