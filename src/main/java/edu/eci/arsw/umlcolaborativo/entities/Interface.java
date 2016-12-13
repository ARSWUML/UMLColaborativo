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
public class Interface extends Elemento{
    /**
     * Constructor del elemento Interface
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Interface(String nombre) throws ProyectoExcepcion {
        super(nombre);
    }
    /**
     * Constructor del elemento Interface
     */
    public Interface(){
        super();
    }
    /**
     * Constructor del elemento Interface
     * @param nombre
     * @param x
     * @param y
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Interface(String nombre, int x, int y) throws ProyectoExcepcion{
        super(nombre,x,y);
    }
    
}
