/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.test;

import edu.eci.arsw.umlcolaborativo.entities.Clase;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionAsociacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionDependencia;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ger9410
 */
public class PruebasRelacionesTest {
    //Clase de equivalencia 1, deberia agregar relacion de asociacion a un diagrama
    @Test
    public void CE1debereiaAgregarRelacionAsociacion() throws Exception{
        DiagramaClases d = new DiagramaClases("diagrama1","descripcion1");
        Clase clase1 = new Clase("elemento1");
        Clase clase2 =new Clase("elemento2");
        int[] multClase1 = new int[2];
        multClase1[0]=1; multClase1[1]=Integer.MAX_VALUE;
        int[] multClase2 = new int[2];
        multClase2[0]=0; multClase2[1]=0;
        Relacion r = new RelacionAsociacion(clase1,clase2,"relacion1",multClase1,multClase2,"clase1","clase2");
        d.agregarElemento(clase1); d.agregarElemento(clase2);
        d.agregarRelacion(r);
        Relacion r1 = d.getRelaciones().get(0);
        Assert.assertEquals("No agrego correctamente la relacion al diagrama",r1,r);
    }
    
    //Clase de equivalencia 2, deberia guardar varias relaciones de asociacion en un diagrama
    @Test
    public void CE2debereiaAgregarRelacionesAsociacion() throws Exception{
        DiagramaClases d = new DiagramaClases("diagrama1","descripcion1");
        Clase clase1 = new Clase("elemento1");
        Clase clase2 = new Clase("elemento2");
        Clase clase3 = new Clase("elemento3");
        Clase clase4 = new Clase("elemento4");
        //Relacion 1
        int[] multClase1 = new int[2];
        multClase1[0]=1; multClase1[1]=Integer.MAX_VALUE;
        int[] multClase2 = new int[2];
        multClase2[0]=0; multClase2[1]=0;
        //Relacion2
        int[] multClase3 = new int[2]; int[] multClase4 = new int[1];
        multClase3[0] = 1; multClase3[1]=Integer.MAX_VALUE;
        multClase4[0] = 1;
        //Relacion3
        int[] multClase5 = new int[1]; int[] multClase6 = new int[1];
        multClase5[0]= 0; multClase6[0]= Integer.MAX_VALUE;
        Relacion r1 = new RelacionAsociacion(clase1,clase2,"relacion1",multClase1,multClase2,"clase1","clase2");
        Relacion r2 = new RelacionAsociacion(clase3,clase4,"relacion2",multClase3,multClase4,"clase3","clase4");
        Relacion r3 = new RelacionAsociacion(clase1,clase3,"relacion3",multClase5,multClase6,"clase1","clase3");
        d.agregarElemento(clase1); d.agregarElemento(clase2);d.agregarElemento(clase3);d.agregarElemento(clase4);
        d.agregarRelacion(r1); d.agregarRelacion(r2); d.agregarRelacion(r3);
        Assert.assertTrue("No agrego correctamente las relaciones al diagrama",d.getRelaciones().contains(r1) && d.getRelaciones().contains(r2) && d.getRelaciones().contains(r3));
    }
    
    //Prueba de equivalencia 3, deberia agregar relacion de depenedencia a un diagrama
    @Test
    public void CE3debereiaAgregarRelacionDeDependencia() throws Exception{
        DiagramaClases d = new DiagramaClases("diagrama1","descripcion1");
        Clase clase1 = new Clase("elemento1");
        Clase clase2 =new Clase("elemento2");
        Relacion r = new RelacionDependencia(clase1,clase2,"relacion1");
        d.agregarElemento(clase1); d.agregarElemento(clase2);
        d.agregarRelacion(r);
        Relacion r1 = d.getRelaciones().get(0);
        Assert.assertEquals("No agrego correctamente la relacion al diagrama",r1,r);
    }
    
    //Prueba de equivalenci 4, deberia agregar relaciones de dependencia en un diagrama
    @Test
    public void CE4debereiaAgregarRelacionesDeDependencia() throws Exception{
        DiagramaClases d = new DiagramaClases("diagrama1","descripcion1");
        Clase clase1 = new Clase("elemento1");
        Clase clase2 =new Clase("elemento2");
        Clase clase3 = new Clase("elemento3");
        Clase clase4 = new Clase("elemento4");
        Relacion r1 = new RelacionDependencia(clase1,clase2,"relacion1");
        Relacion r2 = new RelacionDependencia(clase3,clase4,"relacion2");
        Relacion r3 = new RelacionDependencia(clase2,clase4,"relacion3");
        d.agregarElemento(clase1); d.agregarElemento(clase2);d.agregarElemento(clase3);
        d.agregarElemento(clase4);
        d.agregarRelacion(r1); d.agregarRelacion(r2); d.agregarRelacion(r3);
        Assert.assertTrue("No agrego correctamente las relaciones al diagrama",d.getRelaciones().contains(r1) && d.getRelaciones().contains(r2) && d.getRelaciones().contains(r3));
    }
    
    //Prueba de equivalencia 5, deberia agregar relaciones de los dos tipos en un diagrama
    @Test
    public void CE5debereiaAgregarAmbasRelaciones() throws Exception{
        DiagramaClases d = new DiagramaClases("diagrama1","descripcion1");
        Clase clase1 = new Clase("elemento1");
        Clase clase2 =new Clase("elemento2");
        Clase clase3 = new Clase("elemento3");
        Clase clase4 = new Clase("elemento4");
        int[] multClase3 = new int[2]; int[] multClase4 = new int[1];
        multClase3[0] = 1; multClase3[1]=Integer.MAX_VALUE;
        multClase4[0] = 1;
        Relacion r1 = new RelacionDependencia(clase1,clase2,"relacion1");
        Relacion r2 = new RelacionAsociacion(clase3,clase4,"relacion2",multClase3,multClase4,"clase3","clase4");
        Relacion r3 = new RelacionDependencia(clase2,clase4,"relacion3");
        d.agregarElemento(clase1); d.agregarElemento(clase2);d.agregarElemento(clase3);
        d.agregarElemento(clase4);
        d.agregarRelacion(r1); d.agregarRelacion(r2); d.agregarRelacion(r3);
        Assert.assertTrue("No agrego correctamente las relaciones al diagrama",d.getRelaciones().contains(r1) && d.getRelaciones().contains(r2) && d.getRelaciones().contains(r3));
    }
}
