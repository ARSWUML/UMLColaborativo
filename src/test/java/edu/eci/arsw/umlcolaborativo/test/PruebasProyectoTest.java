/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
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

public class PruebasProyectoTest {
    
    //Clase equivalencia 1, Deberia poder agregar diagrama al proyecto
    @Test
    public void CE1deberiaAgregarDiagramaProyecto() throws Exception {
        Date fecha = new Timestamp(new Date().getTime());
        Map<String,Diagrama> diag=new HashMap<>();
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno", fecha);
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos", fecha);
        diag.put(dg.getTitulo(), dg);
        diag.put(dg2.getTitulo(), dg2);
        Proyecto at=new Proyecto("Project 1", "Agregar descripcion");
       at.agregarDiagrama(dg);
       at.agregarDiagrama(dg2);
       Map<String,Diagrama> consul=at.getDiagramas();
       Assert.assertEquals("Los diagramas del proyecto no son iguales", diag, consul);    
    }
    
      //Clase equivalencia 2, Deberia poder modificar titulo y descripcion del proyecto
    @Test
    public void CE2deberiaModificarProyecto() throws Exception {
        String nombre = "Proyecto OP";
        String descri = "Agregar descripcion proyecto OP";
       Proyecto at=new Proyecto("Project 1", "Agregar descripcion");
        at.setNombre(nombre);
        at.setDescripcion(descri);
        Assert.assertTrue("No modifico titulo, ni descripcion proyecto", at.getNombre().equals(nombre) && at.getDescripcion().equals(descri));
    }
    //Clase equivalencia 3, Deberia poder actualizar la fecha de modificacion del diagrama
    @Test
    public void CE3deberiaModificarFechaModificacionProyecto() throws Exception {
        String nombre = "Proyecto OP";
        String descri = "Agregar descripcion proyecto OP";
        Proyecto at=new Proyecto("Project 1", descri);
        Date FechaCreacion = at.getFechaUltimaModificacion();
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        Thread.sleep(2000);
        at.setNombre(nombre);
        at.agregarDiagrama(dg);
        Date fechaModi=at.getFechaUltimaModificacion();
        Thread.sleep(2000);
        at.agregarDiagrama(dg2);
        Date fechamodif=at.getFechaUltimaModificacion();
        Assert.assertTrue("No cambio la fecha de modificacion del proyecto!", FechaCreacion.getTime()!=fechaModi.getTime() && FechaCreacion.getTime()!=fechamodif.getTime());
    }
    
   //Clase equivalencia 4, Deberia poder consultar un diagrama su nombre
    @Test
    public void CE4deberiaConsultarDiagrama() throws Exception {
        String descri = "Agregar descripcion proyecto OP";
        String nombre = "Diagrama OP";
        Proyecto at=new Proyecto("Project 1", descri);
        Diagrama dg = new DiagramaClases(nombre, "agregar descripcion");
        at.agregarDiagrama(dg);
        Diagrama consul=at.consultarDiagrama(nombre);
        Assert.assertTrue("No consulto el diagrama", consul.equals(dg));
    }
      //Clase equivalencia 5, Deberia poder agregar una lista de elementos al diagrama
    @Test
    public void CE5deberiaAgregarListaDiagramas() throws Exception {
       Proyecto at=new Proyecto("Project 1", "a");
        Map<String,Diagrama> dgs = new HashMap<>();
        Diagrama dg0 = new DiagramaClases("A", "agregar descripcion");
        Diagrama dg1 = new DiagramaClases("B", "agregar descripcion");
        Diagrama dg2 = new DiagramaClases("C", "agregar descripcion");
        Diagrama dg3 = new DiagramaClases("D", "agregar descripcion");
        Diagrama dg4 = new DiagramaClases("E", "agregar descripcion");
       dgs.put("A", dg0);
       dgs.put("B", dg1);
       dgs.put("C", dg2);
       dgs.put("D", dg3);
       dgs.put("E", dg4);
       at.setDiagramas(dgs);
       Assert.assertEquals("No agrego los diagramas al proyecto",dgs, at.getDiagramas());
    }
    //Clase equivalencia 6, No deberia agregar diagramas repetidos
     
    @Test
    public void CE6NoDeberiaAgregarDiagramasRepetidos() throws Exception {
        Proyecto at=new Proyecto("Project 1", "a");
        Diagrama dg0 = new DiagramaClases("A", "agregar descripcion");
        Diagrama dg1 = new DiagramaClases("B", "agregar descripcion");
        Diagrama dg2 = new DiagramaClases("C", "agregar descripcion");
        Diagrama dg3 = new DiagramaClases("A", "agregar descripcion");
        try{
              at.agregarDiagrama(dg0);
              at.agregarDiagrama(dg1);
              at.agregarDiagrama(dg2);
              at.agregarDiagrama(dg3);
            Assert.fail("Agrego diagrama diferente con mismo nombre");
        }catch(ProyectoExcepcion e){
            Assert.assertEquals("El diagrama con titulo "+dg3.getTitulo()+" ya existe por favor cambie el nombre",e.getMessage());
        }
    }
    //Clase equivalencia 7, Deberia poder eliminar diagramas
    ///implementacion proximo sprint
    @Test
    public void CE7deberiaEliminarDiagrama() throws Exception {
        
    }
}
