/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniela Sepulveda
 */
public class PruebasDiagramas {
    
    //Clase equivalencia 1, Deberia poder agregar un elemento al diagrama
    @Test
    public void CE1deberiaAgregarElementoDiagrama() throws Exception {
        Date fecha=new Timestamp(new Date().getTime());
        DiagramaClases dg=new DiagramaClases("Diagrama 1","diagrama uno",fecha);
        Interface infa= new Interface("A");
        ClaseAbstracta claseAbs=new ClaseAbstracta("AB");   
        dg.agregarElemento(infa);
        dg.agregarElemento(claseAbs);
        Elemento a=dg.consultarElemento(infa.getNombre());
        Elemento b=dg.consultarElemento(claseAbs.getNombre());
        Assert.assertTrue(infa.equals(a) && claseAbs.equals(b));
    }
    
     //Clase equivalencia 2, Deberia poder consultar un diagrama dentro de un proyecto por su nombre
    @Test
    public void CE2deberiaConsultarDiagrama() throws Exception {
        
    }
    //Clase equivalencia 3, Deberia poder eleminar un elemento al diagrama
    @Test
    public void CE3deberiaEliminarDiagrama() throws Exception {
        
    }
    
     //Clase equivalencia 4, Deberia poder consultar un proyecto por su nombre
    @Test
    public void CE4deberiaConsultarProyecto() throws Exception {
        
    }
}
