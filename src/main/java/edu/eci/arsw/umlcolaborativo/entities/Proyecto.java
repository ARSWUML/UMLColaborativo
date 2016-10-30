/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Proyecto(String name, String description){
        nombre=name;
        descripcion=description;
        fechaCreacion=new Date();
        fechaUltimaModificacion=new Date();
        diagramas= new HashMap<>();
    }
    
    public Proyecto(){
    }
    
    /**
     * @pos: Agrega diagramas al proyecto
     * @param className
     * @param nombre
     * @param descrip
     * @throws InstantiationException 
     */
    public void agregarDiagrama(String className, String nombre, String descrip) throws InstantiationException{
        
        try {
            Class<?> clase = Class.forName(className);
            Constructor<?> cons = clase.getConstructor(String.class);
            Object object = cons.newInstance(nombre, descrip);
            Diagrama newDiagrama = (Diagrama) object;
            diagramas.put(nombre, newDiagrama);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
    }
    
    /**
     * @pos: Retorna un diagrama por su nombre
     * @param nombre
     * @return diagrama
     */
    public Diagrama consultarDiagrama(String nombre){
            return diagramas.get(nombre); 
        }
}
