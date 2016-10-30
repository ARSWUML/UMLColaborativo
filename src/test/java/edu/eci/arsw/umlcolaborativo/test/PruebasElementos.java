/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Daniela Sepulveda
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebasElementos {
     
    //Clase equivalencia 1, Deberia poder agregar atributos
    ///implementacion proximo sprint
    @Test
    public void CE1deberiaAgregarAtributos() throws Exception {
        
    }
    //Clase equivalencia 2, Deberia poder agregar metodos
    ///implementacion proximo sprint
    @Test
    public void CE2deberiaAgregarMetodos() throws Exception {
        
    }
    //Clase equivalencia 3, Deberia poder crear el elemento y luego modificar el nombre del elemento
    @Test
    public void CE3deberiaCrearYModificarNombreElemento() throws Exception {
        String intername="A";
        String intername1="AAA";
        Interface infa= new Interface(intername);
        ClaseAbstracta claseAbs=new ClaseAbstracta(intername);    
        infa.setNombre(intername1);
        claseAbs.setNombre(intername1);
        assertTrue("Fallo! comparacion!", infa.getNombre().equals(intername1) && claseAbs.getNombre().equals(intername1));
    }
    //Clase equivalencia 4, Deberia poder modificar el nombre, tipo del atributo
     ///implementacion proximo sprint
    @Test
    public void CE4deberiaModificarAtributos() throws Exception {
        
    }
    //Clase equivalencia 5, Deberia poder modificar el nombre, tipo y retorno del metodo 
     ///implementacion proximo sprint
    @Test
    public void CE5deberiaModificarMetodos() throws Exception {
        
    }
    
     //Clase equivalencia 6, Deberia poder eliminar el atributo
     ///implementacion proximo sprint
    @Test
    public void CE6deberiaEliminarAtributos() throws Exception {
        
    }
    //Clase equivalencia 7, Deberia poder eliminar el metodo 
     ///implementacion proximo sprint
    @Test
    public void CE7deberiaEliminarMetodos() throws Exception {
        
    }
}
