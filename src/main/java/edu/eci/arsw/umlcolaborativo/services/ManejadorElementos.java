/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author amoto
 */
@Service
public class ManejadorElementos {
    
    @Autowired
    PersistenciaElementos elementosM;
    public ManejadorElementos(){
    }
    /**
     * @return the elementos
     */
    public Map<String,Elemento> getElementos() {
        return elementosM.getElementos();
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(Map<String,Elemento> elementos) {
         elementosM.setElementos(elementos);
    }
    
    /**
     * 
     * @param nombre el nombre o tipo del elemento a consultar
     * @return el elemento solicitado
     */
    public Elemento consultarElemento(String nombre){
        return elementosM.consultarElemento(nombre);
    }
    
    
    
}
