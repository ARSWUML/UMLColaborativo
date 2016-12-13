/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.util.Date;

/**
 *
 * @author German Lopez
 */
public abstract class DiagramaEstructural extends Diagrama{
    /**
     * Constructor del DiagramaEstructural 
     * @param titulo
     * @param descripcion
     * @param dateCreacion
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public DiagramaEstructural(String titulo,String descripcion,Date dateCreacion) throws ProyectoExcepcion {
        super(titulo,descripcion,dateCreacion);
    }
    /**
     * Constructor del DiagramaEstructural
     * @param titulo
     * @param descripcion
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
     public DiagramaEstructural(String titulo,String descripcion) throws ProyectoExcepcion {
        super(titulo,descripcion);
    }
    /**
     * Constructor del DiagramaEstructural
    */
     public DiagramaEstructural(){
     }
}
