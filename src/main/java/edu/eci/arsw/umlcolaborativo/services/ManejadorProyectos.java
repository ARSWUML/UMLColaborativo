/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import java.util.Map;

/**
 *
 * @author Julian Devia
 */
public class ManejadorProyectos {

    /**
     * @return the proyectos
     */
    public Map<String,Map<String,Proyecto>> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(Map<String,Map<String,Proyecto>> proyectos) {
        this.proyectos = proyectos;
    }
    
    private Map<String,Map<String,Proyecto>> proyectos;
    
    /**
     * 
     */
    public Map<String,Proyecto> consultarProyectos(String usuario){
        return null;
    }
    
    /**
     * 
     */
    public Proyecto consultarProyecto(String usuario,String proyecto){
        return null;
    }
    
    /**
     * 
     */
    public void agregarProyecto(String usuario,Proyecto proyecto){
        
    }
}
