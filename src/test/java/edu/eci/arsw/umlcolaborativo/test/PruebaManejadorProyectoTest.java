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
import edu.eci.arsw.umlcolaborativo.services.ManejadorProyectos;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniela Sepulveda
 */
public class PruebaManejadorProyectoTest {

    //Clase equivalencia 1, Deberia poder agregar y consultar los proyectos con sus diagramas por nombre de usuario
    @Test
    public void CE1deberiaAgregarConsultarProyectosUsuario() throws Exception {
        String usuario = "Juanito78";
        ManejadorProyectos mn = new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at.agregarDiagrama(dg);
        at.agregarDiagrama(dg2);
        mn.agregarProyecto(usuario, at);
        Assert.assertEquals("No agrego proyecto al usuario", at, mn.consultarProyectoUsuario(usuario, "Project 1"));
    }

    //Clase equivalencia 2, Deberia poder consultar todos los proyectos de todos los usuarios
    @Test
    public void CE2deberiaConsultarProyectos() throws Exception {
        Map<String, Map<String, Proyecto>> consul = new HashMap<>();
        String usuario = "Juanito78";
        String usuario2 = "Juana852";
        ManejadorProyectos mn = new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        mn.agregarUsuario(usuario2);
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at.agregarDiagrama(dg);
        at.agregarDiagrama(dg2);
        mn.agregarProyecto(usuario, at);
        Proyecto at2 = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg3 = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg4 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at2.agregarDiagrama(dg3);
        at2.agregarDiagrama(dg4);
        mn.agregarProyecto(usuario2, at2);
        Map<String, Proyecto> p = new HashMap<>();
        p.put(at.getNombre(), at);
        Map<String, Proyecto> pa = new HashMap<>();
        pa.put(at2.getNombre(), at2);
        consul.put(usuario, p);
        consul.put(usuario2, pa);
        Assert.assertEquals("No consulto todos los proyectos", consul, mn.consultarProyectos());
    }

    //Clase equivalencia 3, No deberia poder agregar usuarios con nombres repetidos
    @Test
    public void CE3NoDeberiaAgregarUsuarioRepetido() throws Exception {
        String usuario = "Juanito78";
        ManejadorProyectos mn = new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at.agregarDiagrama(dg);
        at.agregarDiagrama(dg2);
        try {
            mn.agregarUsuario(usuario);
            Assert.fail("Agrego usuario repetido");
        } catch (ProyectoExcepcion e) {
            Assert.assertEquals("Agrega usuarios repetidos y lanza la excepcion incorrecta", "El usuario " + usuario + " ya se encuentra registrado", e.getMessage());
        }
    }

    //Clase equivalencia 4, No deberia poder agregar proyectos con el mismo nombre
    @Test
    public void CE4NoDeberiaAgregarProyectosRepetidos() throws Exception {
        String usuario = "Juanito78";
        ManejadorProyectos mn = new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        Diagrama dg2 = new DiagramaClases("Diagrama 2", "diagrama dos");
        at.agregarDiagrama(dg);
        at.agregarDiagrama(dg2);
        mn.agregarProyecto(usuario, at);
        try {
            mn.agregarProyecto(usuario, at);
            Assert.fail("Agrego Proyecto repetido");
        } catch (ProyectoExcepcion e) {
            Assert.assertEquals("Agrega proyectos repetidos y lanza la excepcion incorrecta", "El usuario " + usuario + " ya colabora en el proyecto " + at.getNombre(), e.getMessage());
        }
    }

    //Clase equivalencia 5, Deberia poder actulizar un proyecto
    @Test
    public void CE5deberiaActualizarProyecto() throws Exception {
        String usuario = "Juanito78";
        ManejadorProyectos mn = new ManejadorProyectos();
        mn.agregarUsuario(usuario);
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        Diagrama dg = new DiagramaClases("Diagrama 1", "diagrama uno");
        at.agregarDiagrama(dg);
        mn.agregarProyecto(usuario, at);
        Proyecto at2 = new Proyecto("Project 1", "Agregar descripcion");
        at2.setDescripcion("Proyecto de mineria de datos");
         mn.actualizarProyecto(usuario, at2);
        Assert.assertEquals("No actulizo el proyecto",at2,mn.consultarProyectoUsuario(usuario, at.getNombre()));
    }
     //Clase equivalencia 6, No deberia poder actulizar un proyecto que no existe
    @Test
    public void CE6NoDeberiaActualizarProyectoInexistente() throws Exception {
        String usuario = "Juanito78";
        ManejadorProyectos mn = new ManejadorProyectos();
        Proyecto at = new Proyecto("Project 1", "Agregar descripcion");
        mn.agregarUsuario(usuario);
        try{
            mn.actualizarProyecto(usuario,at);
            Assert.fail("Modifico un proyecto inexistente");
        }catch(ProyectoExcepcion e){
            Assert.assertEquals("No modifico un proyecto inexistente","El usuario "+usuario+" aun no colabora en el proyecto "+at.getNombre(), e.getMessage());
        }
    }
}
