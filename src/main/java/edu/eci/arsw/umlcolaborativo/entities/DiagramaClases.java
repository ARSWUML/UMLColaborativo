/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            if(elementos.get(nombre)==null){
                getElementos().put(nombre, newElemento);
            }else{
                throw new ProyectoExcepcion("El elemento con nombre "+nombre+" ya existe por favor cambiele el nombre");
            }
            fechaUltimaModificacion=new Timestamp(new Date().getTime());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ProyectoExcepcion(ex.getLocalizedMessage());
        }
    }
    /**
     * Agregar elemento al diagrama
     * @param e
     * @throws ProyectoExcepcion
     */
    public void agregarElemento(Elemento e) throws ProyectoExcepcion{
        if(elementos.get(e.getNombre())==null){
            fechaUltimaModificacion=new Timestamp(new Date().getTime());
            getElementos().put(e.getNombre(), e);
        }else{
             throw new ProyectoExcepcion("El elemento con nombre "+e.getNombre()+" ya existe por favor cambiele el nombre");
        }
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
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
        this.elementos = elementos;
    }
    
}
