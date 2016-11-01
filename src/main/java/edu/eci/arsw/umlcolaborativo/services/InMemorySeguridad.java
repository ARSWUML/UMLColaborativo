/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniela Sepulveda
 */
@Service
public class InMemorySeguridad implements PersistenciaSeguridad{
    
    private Map<String, String> usuarios;
    
    public InMemorySeguridad(){
        usuarios=new HashMap<>();
        agregarUsuarios();
    }
    
    @Override
    public boolean consultarUsuario(String name, String passw) {
        return usuarios.get(name).equals(passw);
    }

    @Override
    public boolean registrarUsuario(String name, String passw) {
       //Implementacion proximo script
       return false;
    }
      
    /**
     * Poblar los usuarios
     */
    
    private void agregarUsuarios(){
        //Daniela
        usuarios.put("Daniela Sepulveda", "007b5cacc3da4dd637b0e8b03185311dcb3f8d21");
        //German
        usuarios.put("German Lopez", "da91388c72d3e31da19dcd85c97374197748485d");
        //Julian
        usuarios.put("Julian Devia", "a01126e0ab758adb773d3c296ead692444669234");
        
    }
}
