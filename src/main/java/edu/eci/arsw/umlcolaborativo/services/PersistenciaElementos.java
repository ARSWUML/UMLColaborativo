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
 * @author German Lopez
 */
public interface PersistenciaElementos {
    /**
     * Consultar los elementos
     * @return 
     */
    public abstract Map<String,Elemento> getElementos();
    /**
     * Enviar los elementos
     * @param elementos
     */
    public abstract void setElementos(Map<String,Elemento> elementos);
    /**
     * Consultar los elementos
     * @param nombre
     * @return 
     */
    public abstract Elemento consultarElemento(String nombre);
}
