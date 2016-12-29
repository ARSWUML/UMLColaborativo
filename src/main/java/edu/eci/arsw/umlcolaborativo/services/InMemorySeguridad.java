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
    /**
     * Constructor de la memoria de seguridad
    */
    public InMemorySeguridad(){
        usuarios=new HashMap<>();
        agregarUsuarios();
    }
    
    @Override
    public boolean consultarUsuario(String name, String passw) {
        return usuarios.containsKey(name) && usuarios.get(name).equals(passw);
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
        //1
        usuarios.put("1","356a192b7913b04c54574d18c28d46e6395428ab");
        //2
        usuarios.put("2","da4b9237bacccdf19c0760cab7aec4a8359010b0");
        //3
        usuarios.put("3","77de68daecd823babbb58edb1c8e14d7106e83bb");
        //4
        usuarios.put("4","1b6453892473a467d07372d45eb05abc2031647a");
        //5
        usuarios.put("5","ac3478d69a3c81fa62e60f5c3696165a4e5e6ac4");
    }
}
