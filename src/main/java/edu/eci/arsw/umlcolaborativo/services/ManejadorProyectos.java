/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian Devia
 */
@Service
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
     * Consulta todos los proyectos del usuario seleccionado
     * @param usuario el usuario de quin se conocer los proyectps
     * @return todos los proyectos del usuario seleccionado por nombre
     */
    public Map<String,Proyecto> consultarProyectos(String usuario){
        return null;
    }
    
    /**
     * consulta el proyecto seleccionado del usuario dado
     * @param usuario el usuario de quien se consultará el proyecto
     * @param proyecto el nombre del proyecto que se quiere consultar
     * @return el proyecto seleccionado del usuario dado
     */
    public Proyecto consultarProyecto(String usuario,String proyecto){
        return null;
    }
    
    /**
     * Arega e proyecto dado al usuario dado
     * @param usuario el usuario al que se le agregará el proyecto
     * @param proyecto el nuevo proyecto
     */
    public void agregarProyecto(String usuario,Proyecto proyecto){
        
    }
}
