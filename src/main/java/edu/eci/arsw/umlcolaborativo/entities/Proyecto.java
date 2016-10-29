/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author amoto
 */
public class Proyecto {
    String nombre;
    String descripcion;
    Date fechaCreacion;
    Date fechaUltimaModificacion;
    Map<String,Diagrama> diagramas;
    
    public Proyecto(String name, String description, Date fCreation, Date fUModification){
        nombre=name;
        descripcion=description;
        fechaCreacion=fCreation;
        fechaUltimaModificacion=fUModification;
    }
    
    public Proyecto(){
    }
}
