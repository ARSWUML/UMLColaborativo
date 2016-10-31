/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import java.sql.Timestamp;
import java.util.Date;
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
        DiagramaClases dg = new DiagramaClases("Diagrama 1", "diagrama uno", fecha);
        DiagramaClases dg2 = new DiagramaClases("Diagrama 2", "diagrama dos", fecha);
        Proyecto at=new Proyecto("Project 1", "Agregar descripcion");
        
        
    }
    
     //Clase equivalencia 2, Deberia poder consultar un proyecto por su nombre
    @Test
    public void CE2deberiaConsultarProyecto() throws Exception {
        
    }
    //Clase equivalencia 3, Deberia poder agregar un proyecto
    @Test
    public void CE3deberiaAgregarProyecto() throws Exception {
        
    }
    
     //Clase equivalencia 2, Deberia poder consultar un proyecto por su nombre
    @Test
    public void CE4deberiaConsultarProyecto() throws Exception {
        
    }
}
