/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ger9410
 */
@Service
public class ManejadorRelaciones{
    
    @Autowired
    private PersistenciaRelaciones relacionesM;
    /**
     * Constructor de manejador de relaciones
     */
    public ManejadorRelaciones(){
    }
    /**
     * Constructor de manejador de relaciones
     * @param pR
     */
    public ManejadorRelaciones(PersistenciaRelaciones pR){
        relacionesM = pR;
    }
    /**
     * Consultar las relaciones 
     * @return 
     */
    public Map<String, Relacion> getRelaciones() {
        return getRelacionesM().getRelaciones();
    }
    /**
     * Enviar relaciones
     * @param relaciones
     */
    public void setRealciones(Map<String, Relacion> relaciones) {
        getRelacionesM().setRealciones(relaciones);
    }
    /**
     * Consultar la relacion
     * @param nombre
     * @return 
     */
    public Relacion consultarRelacion(String nombre) {
       return getRelacionesM().consultarRelacion(nombre);
    }

    /**
     * @return the relacionesM
     */
    public PersistenciaRelaciones getRelacionesM() {
        return relacionesM;
    }

    /**
     * @param relacionesM the relacionesM to set
     */
    public void setRelacionesM(PersistenciaRelaciones relacionesM) {
        this.relacionesM = relacionesM;
    }
    
}
