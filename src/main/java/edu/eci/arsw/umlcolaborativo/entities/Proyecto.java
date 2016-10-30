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
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Map<String,Diagrama> diagramas;
    
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
            getDiagramas().put(nombre, newDiagrama);
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
            return getDiagramas().get(nombre); 
        }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaUltimaModificacion
     */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    /**
     * @param fechaUltimaModificacion the fechaUltimaModificacion to set
     */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    /**
     * @return the diagramas
     */
    public Map<String,Diagrama> getDiagramas() {
        return diagramas;
    }

    /**
     * @param diagramas the diagramas to set
     */
    public void setDiagramas(Map<String,Diagrama> diagramas) {
        this.diagramas = diagramas;
    }
}
