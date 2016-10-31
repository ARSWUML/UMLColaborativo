/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.util.Date;

/**
 *
 * @author ger9410
 */
public abstract class DiagramaEstructural extends Diagrama{
 
    public DiagramaEstructural(String titulo,String descripcion,Date dateCreacion) throws ProyectoExcepcion {
        super(titulo,descripcion,dateCreacion);
    }
     public DiagramaEstructural(String titulo,String descripcion) throws ProyectoExcepcion {
        super(titulo,descripcion);
    }
     public DiagramaEstructural(){
     }
}
