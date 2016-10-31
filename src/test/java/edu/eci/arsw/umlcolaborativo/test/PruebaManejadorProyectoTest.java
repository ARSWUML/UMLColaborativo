/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.services.ManejadorProyectos;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniela
 */
public class PruebaManejadorProyectoTest {
    //Clase equivalencia 1, Deberia poder agregar y consultar los proyectos con sus diagramas por nombre de usuario
    @Test
    public void CE1deberiaAgregarConsultarProyectosUsuario() throws Exception {
        String usuario="Juanito78";
        ManejadorProyectos mn=new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        Proyecto at=new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at.agregarDiagrama(dg);
        at.agregarDiagrama(dg2);
        mn.agregarProyecto(usuario, at);
        Assert.assertEquals("No agrego proyecto al usuario",at,mn.consultarProyectoUsuario(usuario,"Project 1"));
    }
}
