/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julian Devia
 */
@Service
public class ManejadorProyectos {
    /**
     * @return the persistencia
     */
    public PersistenciaProyectos getPersistencia() {
        return persistencia;
    }

    /**
     * @param persistencia the persistencia to set
     */
    public void setPersistencia(PersistenciaProyectos persistencia) {
        this.persistencia = persistencia;
    }
    @Autowired 
    private PersistenciaProyectos persistencia;
    
    /**
     * Crea un manejador de proyectos vacío
     */
    public ManejadorProyectos(){
        //persistencia= new InMemoryProjects();
    }
    public ManejadorProyectos(PersistenciaProyectos persistencia){
        this.persistencia=persistencia;
    }
    
    /**
     * Consulta todos los proyectos del usuario seleccionado
     * @param usuario el usuario de quien se quieren conocer los proyectps
     * @return todos los proyectos del usuario seleccionado por nombre
     * @throws ProyectoExcepcion si el usuario no existe
     */
    public Map<String,Proyecto> consultarProyectosUsuario(String usuario) throws ProyectoExcepcion{
        return getPersistencia().consultarProyectosUsuario(usuario);
    }
    
    /**
     * Consulta todos los proyectos de todos los usuarios
     * @return todos los proyectos de cada usuario
     */
    public Map<String,Map<String,Proyecto>> consultarProyectos(){
        return getPersistencia().consultarProyectos();
    }
    
    /**
     * consulta el proyecto seleccionado del usuario dado
     * @param usuario el usuario de quien se consultará el proyecto
     * @param proyecto el nombre del proyecto que se quiere consultar
     * @return el proyecto seleccionado del usuario dado
     * @throws ProyectoExcepcion si el usuario no existe, o el usuario existe pero no colabora en el proyecto
     */
    public Proyecto consultarProyectoUsuario(String usuario,String proyecto) throws ProyectoExcepcion{
        return getPersistencia().consultarProyectoUsuario(usuario, proyecto);
    }
    
    /**
     * Arega el proyecto dado al usuario dado
     * @param usuario el usuario al que se le agregará el proyecto
     * @param proyecto el nuevo proyecto
     * @throws ProyectoExcepcion si el usuario no existe o el proyecto no existe
     */
    public void agregarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        getPersistencia().agregarProyecto(usuario, proyecto);
    }
    
    /**
     * Actualiza el proyecto dado de el usuario dado
     * @param usuario el usuario al que se e quiere actualizar el proyecto
     * @param proyecto el proyecto que se quiere actualizar
     * @throws ProyectoExcepcion si el usuario no existe o si no posee el proyecto
     */
    public void actualizarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion{
        getPersistencia().actualizarProyecto(usuario, proyecto);
    }
    
    /**
     * Agrega un usuario a los usuarios disponibles
     * @param usuario el nombre del usuario que se desea agregar
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public void agregarUsuario(String usuario) throws ProyectoExcepcion{
        getPersistencia().agregarUsuario(usuario);
    }
    /**
     * Eliminar elementos de la persistencia
    */
    public void vaciar(){
        persistencia.vaciar();
    }
}
