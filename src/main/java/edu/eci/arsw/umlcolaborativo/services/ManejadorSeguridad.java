/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniela Sepulveda
 */
@Service
public class ManejadorSeguridad {

    @Autowired
    private PersistenciaSeguridad memory;

    /**
     * @return the memory
    */
    public PersistenciaSeguridad getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(PersistenciaSeguridad memory) {
        this.memory = memory;
    }
    
    public ManejadorSeguridad(){
    
    }
    /**
     * Permite la autentificacion de un usuario
     * @param name, nombre del usuario
     * @param passw, contrasena usuario
     * @return 
     */
     public boolean consultarUsuario(String name, String passw) {
        return memory.consultarUsuario(name, passw);
    }

    /**
     * Permite registrar un usuario
     * @param name, nombre del usuario
     * @param passw, contrasena usuario
     * @return 
     */
    public boolean registrarUsuario(String name, String passw) {
       //Implementacion proximo script
       return false;
    }
}
