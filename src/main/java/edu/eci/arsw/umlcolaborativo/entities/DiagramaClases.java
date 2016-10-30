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
 * @author ger9410
 */
public class DiagramaClases extends DiagramaEstructural{
    private Map<String,Elemento> elementos;
    
    public DiagramaClases(String name, String descrip){
        nombre=name;
        descripcion=descrip;
        fechaCreacion=new Date();
        fechaUltimaModificacion=new Date();
        elementos=new HashMap<>();
    }
    
    public DiagramaClases(){
    }
    
    public void agregarElemento(String className, String nombre){
        try {
            Class<?> clase = Class.forName(className);
            Constructor<?> cons = clase.getConstructor(String.class);
            Object object = cons.newInstance(nombre);
            Elemento newElemento = (Elemento) object; 
            getElementos().put(nombre, newElemento);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DiagramaClases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Elemento consultarElemento(String nombre){
        return getElementos().get(nombre);
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
        this.nombre=n;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String d){
        this.descripcion=d;
    }
    
    public Date getFechaCreacion(){
        return fechaCreacion;
    }
    
    public void setFechaCreacion(Date fC){
        this.fechaCreacion=fC;
    }
    
    public Date getFechaUltimaModificacion(){
        return fechaUltimaModificacion;
    }
    
    public void setFechaUltimaModificacion(Date fUM){
        this.fechaUltimaModificacion=fUM;
    }

    /**
     * @return the elementos
     */
    public Map<String,Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(Map<String,Elemento> elementos) {
        this.elementos = elementos;
    }
    
}
