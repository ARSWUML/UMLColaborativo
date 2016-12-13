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
public class Clase extends Elemento{
    /**
     * Constructor del elemento clase con nombre
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Clase(String nombre) throws ProyectoExcepcion {
        super(nombre);
    }
    /**
     * Constructor del elemento clase con nombre
     */
    public Clase(){
        super();
    }
    /**
     * Constructor del elemento clase con nombre y coordenadas tal
     * @param nombre
     * @param x
     * @param y
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Clase(String nombre, int x, int y) throws ProyectoExcepcion{
        super(nombre,x,y);
    }
    
}
