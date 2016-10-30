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
 * @author amoto
 */
public class ManejadorelEmentos {

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
    private Map<String,Elemento> elementos;
    
    /**
     * 
     * @param nombre el nombre o tipo del elemento a consultar
     * @return el elemento solicitado
     */
    public Elemento consultarElemento(String nombre){
        return null;
    }
    
}
