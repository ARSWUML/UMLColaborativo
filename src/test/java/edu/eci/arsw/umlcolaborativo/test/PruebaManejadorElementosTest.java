/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import edu.eci.arsw.umlcolaborativo.services.InMemoryElements;
import edu.eci.arsw.umlcolaborativo.services.ManejadorElementos;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniela Sepulveda
 */
public class PruebaManejadorElementosTest {
    
    //Clase equivalencia 1, Deberia poder agregar elementos
    @Test
    public void CE1deberiaConsultarElementos() throws Exception {
       ManejadorElementos mn=new ManejadorElementos(new InMemoryElements());
        String intername="A";
        String intername1="AAA";
        Interface infa= new Interface(intername);
        ClaseAbstracta claseAbs=new ClaseAbstracta(intername1);
        Map<String,Elemento> elementos = new HashMap<>();
        elementos.put(intername, infa);
        elementos.put(intername1, claseAbs);
        mn.setElementos(elementos);
        Assert.assertEquals("No agrego los elementos",elementos,mn.getElementos());
    }
    
    //Clase equivalencia 2, Deberia porder consultar los elementos en el manejador
    @Test
    public void CE2deberiaConsultarElementos() throws Exception {
        ManejadorElementos mn=new ManejadorElementos(new InMemoryElements());
        String intername="A";
        Interface infa= new Interface(intername);
        Map<String,Elemento> elementos = new HashMap<>();
        elementos.put(intername, infa);
        mn.setElementos(elementos);
        Assert.assertEquals("No consulto el elemento",infa,mn.consultarElemento(intername));
    }
}
