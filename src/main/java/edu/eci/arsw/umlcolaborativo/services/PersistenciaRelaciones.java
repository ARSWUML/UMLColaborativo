/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import java.util.Map;

/**
 *
 * @author German Lopez
 */
public interface PersistenciaRelaciones {
    /**
     * Consultar las relaciones
     * @return 
     */
    public abstract Map<String,Relacion> getRelaciones();
    /**
     * Enviar las relaciones
     * @param relaciones
     */
    public abstract void setRealciones(Map<String,Relacion> relaciones);
    /**
     * Consultar relacion
     * @param nombre
     * @return 
     */
    public abstract Relacion consultarRelacion(String nombre);
    
}
