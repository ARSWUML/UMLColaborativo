/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniela Sepulveda
 */
public class PruebasDiagramasTest {

    //Clase equivalencia 1, Deberia poder agregar un elemento al diagrama
    @Test
    public void CE1deberiaAgregarElementoDiagrama() throws Exception {
        Date fecha = new Timestamp(new Date().getTime());
        DiagramaClases dg = new DiagramaClases("Diagrama 1", "diagrama uno", fecha);
        Interface infa = new Interface("A");
        ClaseAbstracta claseAbs = new ClaseAbstracta("AB");
        dg.agregarElemento(infa);
        dg.agregarElemento(claseAbs);
        Elemento a = dg.consultarElemento(infa.getNombre());
        Elemento b = dg.consultarElemento(claseAbs.getNombre());
        Assert.assertTrue("No agrego los elementos",infa.equals(a) && claseAbs.equals(b));
    }

    //Clase equivalencia 2, Deberia poder modificar titulo y descripcion del diagrama
    @Test
    public void CE2deberiaModificarDiagrama() throws Exception {
        String nombre = "Diagrama de clases OP";
        String descri = "diagrama uno especificar clases OP";
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        dg.setTitulo(nombre);
        dg.setDescripcion(descri);
        Assert.assertTrue("No modifico titulo, ni descripcion diagrama", dg.getTitulo().equals(nombre) && dg.getDescripcion().equals(descri));
    }

    //Clase equivalencia 3, Deberia poder actualizar la fecha de modificacion del diagrama
    @Test
    public void CE3deberiaModificarFechaModificacionDiagrama() throws Exception {
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        Date FechaCreacion = dg.getFechaUltimaModificacion();
        Interface infa = new Interface("A");
        Thread.sleep(500);
        dg.agregarElemento(infa);
        Date fechaModi = dg.getFechaUltimaModificacion();
        ClaseAbstracta claseAbs = new ClaseAbstracta("AB");
        dg.agregarElemento(claseAbs);
        Date fechamodif = dg.getFechaUltimaModificacion();
        Assert.assertTrue("No cambio la fecha de modificacion!", FechaCreacion.getTime()!=fechaModi.getTime() && FechaCreacion.getTime()!=fechamodif.getTime());
    }

    //Clase equivalencia 4, Deberia poder consultar un elemento su nombre
    @Test
    public void CE4deberiaConsultarElemento() throws Exception {
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        Interface infa = new Interface("A");
        Elemento consul = dg.consultarElemento(infa.getNombre());
        dg.agregarElemento(infa);
        Elemento consu = dg.consultarElemento(infa.getNombre());
        Assert.assertTrue("Fallo comparacion entre elementos", consu.equals(infa));
    }

    //Clase equivalencia 5, Deberia poder agregar una lista de elementos al diagrama
    @Test
    public void CE5deberiaAgregarListaElementos() throws Exception {
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        Map<String,Elemento> elementos = new HashMap<>();
        Interface infa = new Interface("A");
        Interface infa1 = new Interface("B");
        Interface infa2 = new Interface("C");
        Interface infa3 = new Interface("D");
        ClaseAbstracta claseAbs = new ClaseAbstracta("AB");
        ClaseAbstracta claseAbs1 = new ClaseAbstracta("BB");
        ClaseAbstracta claseAbs2 = new ClaseAbstracta("CC");
        elementos.put("A", infa);
        elementos.put("B", infa1);
        elementos.put("C", infa2);
        elementos.put("D", infa3);
        elementos.put("AB", claseAbs);
        elementos.put("BB", claseAbs);
        elementos.put("CC", claseAbs);
        dg.setElementos(elementos);
        Assert.assertEquals("No agrego todos los elementos",elementos, dg.getElementos());
    }

    //Clase equivalencia 6, Deberia eliminar un elemento del diagrama
    /// implementacion proximo sprint
    @Test
    public void CE6deberiaEliminarElemento() throws Exception {

    }
    //Clase equivalencia 7, Deberia poder agregar elemento por el nombre de la clase al diagrama

    @Test
    public void CE7deberiaAgregarElementoDiagrama() throws Exception {
       
    }
    //Clase equivalencia 8, No deberia agregar elementos con el mismo nombre
    @Test
    public void CE8NoDeberiaAgregarElementoRepetido() throws Exception {
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        Interface infa1 = new Interface("A");
        Interface infa2 = new Interface("B");
        Interface infa3 = new Interface("B");
        dg.agregarElemento(infa1);
        dg.agregarElemento(infa2);
        try{
            dg.agregarElemento(infa3);
            Assert.fail("Agrego elementos con mismo nombre");
        }catch(ProyectoExcepcion e){
            Assert.assertEquals("No agrego el elemento", "El elemento con nombre "+infa3.getNombre()+" ya existe por favor cambiele el nombre",e.getMessage());
        }
    }
       //Clase equivalencia 9, No deberia agregar elementos con el mismo nombre
    @Test
    public void CE9NoDeberiaAgregarElementoDiferenteRepetido() throws Exception {
        DiagramaClases dg = new DiagramaClases("Diagrama1", "agregar descripcion");
        Interface infa1 = new Interface("A");
        Interface infa2 = new Interface("B");
        ClaseAbstracta claseAbs = new ClaseAbstracta("A");
        dg.agregarElemento(infa1);
        dg.agregarElemento(infa2);
        try{
            dg.agregarElemento(claseAbs);
            Assert.fail("Agrego elemento diferente con mismo nombre");
        }catch(ProyectoExcepcion e){
            Assert.assertEquals("No agrego el elemento", "El elemento con nombre "+claseAbs.getNombre()+" ya existe por favor cambiele el nombre",e.getMessage());
        }
    }
}
