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
 * @author Julian Devia
 */
@Service
public class ManejadorElementos {
    
    @Autowired
    private PersistenciaElementos elementosM;

    public PersistenciaElementos getElementosM() {
        return elementosM;
    }

    public void setElementosM(PersistenciaElementos elementosM) {
        this.elementosM = elementosM;
    }
    /**
     * Constructor manejador
     */
    public ManejadorElementos(){
    }
    /**
     * Constructor manejador 
     * @param persistencia
     */
    public ManejadorElementos(PersistenciaElementos persistencia){
        this.elementosM=persistencia;
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
