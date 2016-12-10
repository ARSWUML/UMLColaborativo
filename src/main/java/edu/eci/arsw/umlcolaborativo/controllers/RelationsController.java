/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.controllers;

import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import edu.eci.arsw.umlcolaborativo.services.ManejadorRelaciones;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ger9410
 */
@RestController
@RequestMapping(value = "/relations")
public class RelationsController {
    
    @Autowired
    ManejadorRelaciones mR;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listaRelaciones(){
        Map<String,Relacion> relacionesDisp= mR.getRelaciones();
        return new ResponseEntity<>(relacionesDisp,HttpStatus.ACCEPTED);           
    }
    
    @RequestMapping(path="/{relationid}" ,method = RequestMethod.GET)
    public ResponseEntity<?> obtenerRelacion(@PathVariable String relationid){
        Relacion relacion=mR.consultarRelacion(relationid);
        return new ResponseEntity<>(relacion,HttpStatus.ACCEPTED);           
    }
}
