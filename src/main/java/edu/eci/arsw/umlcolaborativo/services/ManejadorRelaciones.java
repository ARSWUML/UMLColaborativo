/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ger9410
 */
public class ManejadorRelaciones{
    
    @Autowired
    private PersistenciaRelaciones relacionesM;
    
    public ManejadorRelaciones(){}
    
    public ManejadorRelaciones(PersistenciaRelaciones pR){
        relacionesM = pR;
    }
    
    public Map<String, Relacion> getRelaciones() {
        return getRelacionesM().getRelaciones();
    }

    public void setRealciones(Map<String, Relacion> relaciones) {
        getRelacionesM().setRealciones(relaciones);
    }

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
