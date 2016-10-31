/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.ArrayList;
import java.util.HashMap;
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

    private Map<String,List<String>> usuarios;
    private Map<String,Proyecto> proyectos;
    
    public InMemoryProjects(){
        usuarios= new ConcurrentHashMap<>();
        proyectos= new ConcurrentHashMap<>();
    }
    
    public Map<String,Proyecto> consultarProyectosUsuario(String usuario) throws ProyectoExcepcion{
        validarUsuario(usuario);
        Map<String,Proyecto> proy= new HashMap<>();
        for(String name:usuarios.get(usuario)){
            proy.put(name, proyectos.get(name));
        }
        return proy;
    }
    
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
    
    public Proyecto consultarProyectoUsuario(String usuario,String proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        if(!usuarios.get(usuario).contains(proyecto)) throw new ProyectoExcepcion("El usuario "+usuario+" no colabora en el proyecto "+proyecto);
        return proyectos.get(proyecto);
    }
    
    public void agregarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        if(usuarios.get(usuario).contains(proyecto)) throw new ProyectoExcepcion("El usuario "+usuario+" ya colabora en el proyecto "+proyecto);
        usuarios.get(usuario).add(proyecto.getNombre());
        proyectos.put(proyecto.getNombre(), proyecto);
    }
    
    public void actualizarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        validarUsuario(usuario);
        if(!usuarios.get(usuario).contains(proyecto)) throw new ProyectoExcepcion("El usuario "+usuario+" ya colabora en el proyecto "+proyecto);
        usuarios.get(usuario).add(proyecto.getNombre());
        proyectos.put(proyecto.getNombre(), proyecto);
    }
    
    @Override
    public void agregarUsuario(String usuario) throws ProyectoExcepcion {
        validarUsuario(usuario);
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
    
}
