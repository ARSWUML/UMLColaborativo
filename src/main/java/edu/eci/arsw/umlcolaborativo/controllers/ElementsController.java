/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.controllers;

import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.services.ManejadorElementos;
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
@RequestMapping(value = "/elements")
public class ElementsController {
    @Autowired
    ManejadorElementos manElementos;
    /**
     * @pos: retorna la lista de elementos disponibles para usar
     * @return ResponseEntity<?>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listaElementos(){
        Map<String,Elemento> elementosDisp=manElementos.getElementos();
        return new ResponseEntity<>(elementosDisp,HttpStatus.ACCEPTED);           
    }
    
    /**
     * @pos: Retorna un elemento consultado por su nombre
     * @param elementid
     * @return 
     */
    @RequestMapping(path="/{elementid}" ,method = RequestMethod.GET)
    public ResponseEntity<?> obtenerElementos(@PathVariable String elementid){
        Elemento elemento=manElementos.consultarElemento(elementid);
        return new ResponseEntity<>(elemento,HttpStatus.ACCEPTED);           
    }
}
