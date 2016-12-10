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
 * @author ger9410
 */
public interface PersistenciaRelaciones {
    
    public abstract Map<String,Relacion> getRelaciones();
    
    public abstract void setRealciones(Map<String,Relacion> relaciones);
    
    public abstract Relacion consultarRelacion(String nombre);
    
}
