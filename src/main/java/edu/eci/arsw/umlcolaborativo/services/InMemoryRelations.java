/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionAsociacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionDependencia;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ger9410
 */
public class InMemoryRelations implements PersistenciaRelaciones{
    
    Map<String,Relacion> relaciones;
    
    
    public InMemoryRelations(){
        relaciones = new HashMap<>();
        cargarRelaciones();
    }

    @Override
    public Map<String, Relacion> getRelaciones() {
        return relaciones;
    }

    @Override
    public void setRealciones(Map<String, Relacion> relaciones) {
        this.relaciones = relaciones;
    }

    @Override
    public Relacion consultarRelacion(String nombre) {
        return relaciones.get(nombre);
    }
    
    public void cargarRelaciones(){
        Relacion relacionAsociacion = new RelacionAsociacion("Relacion Asociacion");
        Relacion relacionDependencia = new RelacionDependencia("Relacion Dependencia");
        relaciones.put(relacionAsociacion.getNombreRelacion(), relacionAsociacion);
        relaciones.put(relacionDependencia.getNombreRelacion(), relacionDependencia);
    }
}
