/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
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
        at.setNombre(nombre);
        Date fechaModi=at.getFechaUltimaModificacion();
        at.agregarDiagrama(dg);
        Thread.sleep(1000);
        at.agregarDiagrama(dg2);
        Date fechamodif=at.getFechaUltimaModificacion();
        Assert.assertTrue("No cambio la fecha de modificacion del proyecto!", FechaCreacion.getTime()!=fechaModi.getTime() && FechaCreacion.getTime()!=fechamodif.getTime());
    }
    
   //Clase equivalencia 4, Deberia poder consultar un diagrama su nombre
    @Test
    public void CE4deberiaConsultarElemento() throws Exception {
        String descri = "Agregar descripcion proyecto OP";
        String nombre = "Diagrama OP";
        Proyecto at=new Proyecto("Project 1", descri);
        Diagrama dg = new DiagramaClases(nombre, "agregar descripcion");
        at.agregarDiagrama(dg);
        Diagrama consul=at.consultarDiagrama(nombre);
        Assert.assertTrue("Fallo comparacion entre elementos", consul.equals(dg));
    }
}
