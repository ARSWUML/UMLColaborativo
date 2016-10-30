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
    
    public DiagramaClases(){
        elementos=new HashMap<>();
    }
    public DiagramaClases(String titulo,String descripcion,Date dateCreacion){
        super(titulo,descripcion,dateCreacion);
        elementos=new HashMap<>();
    }
    public DiagramaClases(String titulo,String descripcion){
        super(titulo,descripcion);
        elementos=new HashMap<>();
    }
 
    public void agregarElemento(String className, String nombre) throws ProyectoExcepcion{
        try {
            Class<?> clase = Class.forName(className);
            Constructor<?> cons = clase.getConstructor(String.class);
            Object object = cons.newInstance(nombre);
            Elemento newElemento = (Elemento) object; 
            getElementos().put(nombre, newElemento);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ProyectoExcepcion(ex.getLocalizedMessage());
        }
    }
    /**
     * Agregar elemento al diagrama
     * @param e
     */
    public void agregarElemento(Elemento e){
            getElementos().put(e.getNombre(), e);
    }
    public Elemento consultarElemento(String nombre){
        return getElementos().get(nombre);
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
