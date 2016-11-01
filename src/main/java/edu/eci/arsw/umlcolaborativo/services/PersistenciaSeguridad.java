/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

/**
 *
 * @author Daniela Sepulveda
 */
public interface PersistenciaSeguridad {
    /**
     * Permite la autentificacion de un usuario
     * @param name, nombre del usuario
     * @param passw, contrasena usuario
     * @return 
     */
    boolean consultarUsuario(String name, String passw);
    
    /**
     * Permite registrar un usuario
     * @param name, nombre del usuario
     * @param passw, contrasena usuario
     * @return 
     */
    boolean registrarUsuario(String name, String passw);
  
}
