/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.Clase;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionAsociacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionDependencia;
import edu.eci.arsw.umlcolaborativo.services.InMemoryRelations;
import edu.eci.arsw.umlcolaborativo.services.ManejadorRelaciones;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ger9410
 */
public class PruebasManejadorRelacionesTest {
    
    //Prueba de equivalencia 1, deberia agregar relaciones 
    @Test
    public void CE1deberiaAgregarRelaciones(){
        ManejadorRelaciones mR = new ManejadorRelaciones(new InMemoryRelations());
        Relacion r1 = new RelacionAsociacion("Relacion Asociacion");
        Relacion r2 = new RelacionDependencia("Relacion Dependencia");
        Map<String,Relacion> relaciones = new HashMap<>();
        relaciones.put(r1.getNombreRelacion(), r1);
        relaciones.put(r2.getNombreRelacion(), r2);
        mR.setRealciones(relaciones);
        Assert.assertEquals("No agrego correctamente las relacioenes",relaciones,mR.getRelaciones());
    }
    
    //Prueba de equivalencia 2, deberia consultar la relacion
    @Test
    public void CE2deberiaConsultarRelacion() throws ProyectoExcepcion{
        ManejadorRelaciones mR = new ManejadorRelaciones(new InMemoryRelations());
        Relacion r1 = new RelacionAsociacion("Relacion Asociacion");
        Relacion r2 = new RelacionDependencia("Relacion Dependencia");
        Clase A = new Clase("cA"); Clase B = new Clase("cB");
        r1.setElementoA(A); r1.setElementoB(B);
        Map<String,Relacion> relaciones = new HashMap<>();
        relaciones.put(r1.getNombreRelacion(), r1);
        relaciones.put(r2.getNombreRelacion(), r2);
        mR.setRealciones(relaciones);
        Assert.assertEquals("No agrego consulto correctamente la relacion",r1,mR.consultarRelacion(r1.getNombreRelacion()));
    }
}
