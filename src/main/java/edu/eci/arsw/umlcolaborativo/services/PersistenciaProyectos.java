/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.Map;

/**
 *
 * @author Julian Devia
 */
public interface PersistenciaProyectos {
    
    /**
     * Consulta todos los proyectos del usuario seleccionado
     * @param usuario el usuario de quin se conocer los proyectps
     * @return todos los proyectos del usuario seleccionado por nombre
     * @throws ProyectoExcepcion si el usuario no existe
     */
    public Map<String,Proyecto> consultarProyectosUsuario(String usuario) throws ProyectoExcepcion;
    
    /**
     * Consulta todos los proyectos de todos los usuarios
     * @return todos los proyectos de cada usuario
     */
    public Map<String,Map<String,Proyecto>> consultarProyectos();
    
    /**
     * consulta el proyecto seleccionado del usuario dado
     * @param usuario el usuario de quien se consultará el proyecto
     * @param proyecto el nombre del proyecto que se quiere consultar
     * @return el proyecto seleccionado del usuario dado
     * @throws ProyectoExcepcion si el usuario no existe, o el usuario existe pero no colabora en el proyecto
     */
    public Proyecto consultarProyectoUsuario(String usuario,String proyecto) throws ProyectoExcepcion;
    
    /**
     * Arega el proyecto dado al usuario dado
     * @param usuario el usuario al que se le agregará el proyecto
     * @param proyecto el nuevo proyecto
     * @throws ProyectoExcepcion si el usuario no existe o el proyecto no existe
     */
    public void agregarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion;
    
    /**
     * Actualiza el proyecto dado de el usuario dado
     * @param usuario el usuario al que se e quiere actualizar el proyecto
     * @param proyecto el proyecto que se quiere actualizar
     * @throws ProyectoExcepcion si el usuario no existe o si no posee el proyecto
     */
    public void actualizarProyecto(String usuario,Proyecto proyecto) throws ProyectoExcepcion;
    
    /**
     * Agrega un usuario a lousuarios disponibles
     * @param usuario el nombre del usuario que se desea agregar
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public void agregarUsuario(String usuario) throws ProyectoExcepcion;
    
    /**
     * Vacia los atributos
     */
    public void vaciar();
}