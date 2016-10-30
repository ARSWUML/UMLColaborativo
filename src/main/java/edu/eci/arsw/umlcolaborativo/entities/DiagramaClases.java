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
    Map<String,Elemento> elementos;
    
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
            elementos.put(nombre, newElemento);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DiagramaClases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Elemento consultarElemento(String nombre){
        return elementos.get(nombre);
    }
    
}
