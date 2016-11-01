/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Clase;
import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian Devia
 */
@Service
public class InMemoryProjects implements PersistenciaProyectos {
    
    private Map<String,List<String>> usuarios;
    private Map<String,Proyecto> proyectos;
    
    public InMemoryProjects() throws ProyectoExcepcion{
        usuarios= new ConcurrentHashMap<>();
        proyectos= new ConcurrentHashMap<>();
        cargarProyectos();
    }
    /**
     * @return the usuarios
     */
    public Map<String,List<String>> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Map<String,List<String>> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the proyectos
     */
    public Map<String,Proyecto> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(Map<String,Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
    
    @Override
    public void vaciar(){
        usuarios.clear();
        proyectos.clear();
    }
    
    @Override
    public Map<String,Proyecto> consultarProyectosUsuario(String usuario) throws ProyectoExcepcion{
        validarUsuario(usuario);
        Map<String,Proyecto> proy= new HashMap<>();
        for(String name:usuarios.get(usuario)){
            proy.put(name, proyectos.get(name));
        }
        return proy;
    }
    
    @Override
    public Map<String,Map<String,Proyecto>> consultarProyectos(){
        Map<String,Map<String,Proyecto>> todos= new HashMap<>();
        for(String nombre : usuarios.keySet()){
            try {
                todos.put(nombre, consultarProyectosUsuario(nombre));
            } catch (ProyectoExcepcion ex) {
                Logger.getLogger(ManejadorProyectos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return todos;
    }
    
    @Override
    public Proyecto consultarProyectoUsuario(String usuario,String proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        if(!usuarios.get(usuario).contains(proyecto)) throw new ProyectoExcepcion("El usuario "+usuario+" no colabora en el proyecto "+proyecto);
        return proyectos.get(proyecto);
    }
    
    @Override
    public void agregarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        System.out.println("user: "+usuario);
        if(usuarios.get(usuario).contains(proyecto.getNombre())) throw new ProyectoExcepcion("El usuario "+usuario+" ya colabora en el proyecto "+proyecto.getNombre());
        usuarios.get(usuario).add(proyecto.getNombre());
        proyectos.put(proyecto.getNombre(), proyecto);
    }
    
    @Override
    public void actualizarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        if(!usuarios.get(usuario).contains(proyecto.getNombre())) throw new ProyectoExcepcion("El usuario "+usuario+" aun no colabora en el proyecto "+proyecto.getNombre());
        proyectos.put(proyecto.getNombre(), proyecto);
    }
    
    @Override
    public void agregarUsuario(String usuario) throws ProyectoExcepcion {
        if(usuarios.containsKey(usuario)) throw new ProyectoExcepcion("El usuario "+usuario+" ya se encuentra registrado");
        usuarios.put(usuario, new ArrayList<>());
    }
    
    /**
     * Se encarga de validar que el nombre de usuario sea valido
     * @param usuario el usuario a consultar
     * @throws ProyectoExcepcion si el nombre de usuario no existe
     */
    private void validarUsuario(String usuario) throws ProyectoExcepcion{
       if(!usuarios.containsKey(usuario)) throw new ProyectoExcepcion("El usuario "+usuario+" no se encuentra registrado");
    }
    
    public void cargarProyectos() throws ProyectoExcepcion{
        List<String> nomProyectos1 = new ArrayList<>();
        List<String> nomProyectos2 = new ArrayList<>();
        List<String> nomProyectos3 = new ArrayList<>();
        //Proyectos
        Proyecto p0= new Proyecto("Vacio","proyecto sin elementos");
        Proyecto p1 = new Proyecto("Proyecto 1","primer proyecto");
        Proyecto p2 = new Proyecto("Proyecto 2","segundo proyecto");
        Proyecto p3 = new Proyecto("Proyecto 3","tercer proyecto");
        Proyecto p4 = new Proyecto("Proyecto 4","cuarto proyecto");
        //Crear Diagramas
        DiagramaClases d1 = new DiagramaClases("Diagrama 1","primer diagrama");
        DiagramaClases d2 = new DiagramaClases("Diagrama 2","segundo diagrama");
        DiagramaClases d3 = new DiagramaClases("Diagrama 3","tercer diagrama");
        DiagramaClases d4 = new DiagramaClases("Diagrama 4","cuarto diagrama");
        DiagramaClases d5 = new DiagramaClases("Diagrama 5","quinto diagrama");
        DiagramaClases d6 = new DiagramaClases("Diagrama 6","sexto diagrama");
        DiagramaClases d7 = new DiagramaClases("Diagrama 7","septimo diagrama");
        DiagramaClases d8 = new DiagramaClases("Diagrama 8","octavo diagrama");
        DiagramaClases d9= new DiagramaClases("Diagrama Vacio","Diagrama sin elementos");
        //Crear elementos
        Elemento e1 = new Clase("Clase1");
        Elemento e2 = new Clase("Clase2");
        Elemento e3 = new ClaseAbstracta("ClaseAbstracta1");
        Elemento e4 = new Interface("Interface1");
        Elemento e5 = new ClaseAbstracta("ClaseAbstracta2");
        Elemento e6 = new Clase("Clase3");
        Elemento e7 = new ClaseAbstracta("ClaseAbstracta3");
        Elemento e8 = new Interface("Interface2");
        Elemento e9 = new Clase("Clase4");
        Elemento e10 = new Clase("Clase5");
        Elemento e11 = new Interface("Interface3");
        Elemento e12 = new Interface("Interface4");
        Elemento e13 = new ClaseAbstracta("ClaseAbstracta4");
        Elemento e14 = new Clase("Clase6");
        Elemento e15 = new ClaseAbstracta("ClaseAbstracta5");
        Elemento e16 = new Interface("Interface5");
        //Agregar Elementos
        d1.agregarElemento(e1);
        d1.agregarElemento(e2);
        d1.agregarElemento(e3);
        d2.agregarElemento(e4);
        d2.agregarElemento(e5);
        d3.agregarElemento(e6);
        d3.agregarElemento(e16);
        d4.agregarElemento(e7);
        d4.agregarElemento(e8);
        d4.agregarElemento(e15);
        d5.agregarElemento(e9);
        d6.agregarElemento(e10);
        d7.agregarElemento(e11);
        d7.agregarElemento(e12);
        d8.agregarElemento(e13);
        d8.agregarElemento(e14);
        //Agregar diagramas
        p1.agregarDiagrama(d1);
        p1.agregarDiagrama(d2);
        p2.agregarDiagrama(d3);
        p2.agregarDiagrama(d4);
        p3.agregarDiagrama(d5);
        p4.agregarDiagrama(d6);
        p4.agregarDiagrama(d7);
        p4.agregarDiagrama(d8);
        p0.agregarDiagrama(d9);
        //Agregando proyectos
        proyectos.put(p1.getNombre(),p1);
        proyectos.put(p2.getNombre(),p2);
        proyectos.put(p3.getNombre(),p3);
        proyectos.put(p4.getNombre(),p4);
        proyectos.put(p0.getNombre(), p0);
        //Agregando usuarios y sus proyectos
        nomProyectos1.add(p2.getNombre());
        nomProyectos1.add(p3.getNombre());
        nomProyectos2.add(p2.getNombre());
        nomProyectos2.add(p4.getNombre());
        nomProyectos2.add(p3.getNombre());
        nomProyectos3.add(p1.getNombre());
        nomProyectos3.add(p0.getNombre());
        usuarios.put("German Lopez",nomProyectos1);
        usuarios.put("Daniela Sepulveda", nomProyectos2);
        usuarios.put("Julian Devia",nomProyectos3);
    }
    
}
