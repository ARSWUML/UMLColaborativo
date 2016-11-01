/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.controllers;

import edu.eci.arsw.umlcolaborativo.services.ManejadorSeguridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniela
 */
@RestController
@RequestMapping("/seguridad")
public class SeguridadController {
    
    @Autowired
    ManejadorSeguridad mn=new ManejadorSeguridad();
    
    /**
     * Valida un usuario
     * @param name
     * @param passw
     * @return ResponseEntity<?> 
     */
    @RequestMapping(path = "/{name}/{passw}", method = RequestMethod.GET)
    public ResponseEntity<?> validarUsuario(@PathVariable String name,@PathVariable String passw){
      return new ResponseEntity<>(mn.consultarUsuario(name, passw),HttpStatus.ACCEPTED);
    }
}
