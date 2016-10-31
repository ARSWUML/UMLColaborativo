/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import java.util.Map;

/**
 *
 * @author ger9410
 */
public interface PersistenciaElementos {
    
    public abstract Map<String,Elemento> getElementos();
    
    public abstract void setElementos(Map<String,Elemento> elementos);
    
    public abstract Elemento consultarElemento(String nombre);
}
