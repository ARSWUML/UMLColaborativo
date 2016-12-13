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
public class ProyectoExcepcion extends Exception{
    /**
     * @param message
     * @param cause
     */
    public ProyectoExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * @param cause
     */
    public ProyectoExcepcion(String cause) {
        super(cause);
    }
    
    
}
