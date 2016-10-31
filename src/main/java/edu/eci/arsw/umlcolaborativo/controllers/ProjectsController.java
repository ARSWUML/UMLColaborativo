/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.controllers;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import edu.eci.arsw.umlcolaborativo.services.ManejadorProyectos;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ger9410
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    ManejadorProyectos manProyectos;
    
    /**
     * @pre: Ninguna
     * @post: Retorna todos los proyectos
     * @return ResponseEntity<?> 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> consultarProyectos(){
        Map<String,Map<String,Proyecto>> proyectos= manProyectos.consultarProyectos();
      return new ResponseEntity<>(proyectos,HttpStatus.ACCEPTED);
    }
    
    /**
     * @pre: Usuario existente, proyectos existentes
     * @post: Retorna los proyectos en los que un usuario esta colaborando
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path = "/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarProyectosUsuario(@PathVariable String userid){
        try {
            Map<String,Proyecto> proyectosUsuario=manProyectos.consultarProyectosUsuario(userid);
            return new ResponseEntity<>(proyectosUsuario,HttpStatus.ACCEPTED);
        } catch (ProyectoExcepcion ex) {
           return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    
    /**
     * @pre: Usuario existente, proyecto existente
     * @post: Retorna un proyecto en el cual un usuario esta colaborando
     * @param projectid
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path = "/users/{userid}/{projectid}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarProyectoUsuario(@PathVariable String projectid, @PathVariable String userid){
        try {
            Proyecto proyectoUsuario=manProyectos.consultarProyectoUsuario(userid, projectid);
            return new ResponseEntity<>(proyectoUsuario,HttpStatus.ACCEPTED);
        } catch (ProyectoExcepcion ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
    }
    /**
     * @pre: Ninguna
     * @pos: Agrega un proyecto con su usuario respectivo
     * @param project
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path="/users/{userid}" , method = RequestMethod.POST)
    public ResponseEntity<?> agregarProyecto(@RequestBody Proyecto project, @PathVariable String userid){
        try {
            manProyectos.agregarProyecto(userid, project);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ProyectoExcepcion ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_MODIFIED);
        }
    }
    
    /**
     * Actualiza un proyecto de un usario dado
     * @param userid
     * @param project
     * @return ResponseEntity<?>
     */
    @RequestMapping(path="/users/{userid}", method=RequestMethod.PUT)
    public ResponseEntity<?> actualizarProyecto(@PathVariable String userid, @RequestBody Proyecto project){
        try{
            manProyectos.actualizarProyecto(userid, project);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(ProyectoExcepcion ex){
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_MODIFIED);
        }
    }
    
    /**
 * Agrega un usuario 
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path="/users", method=RequestMethod.PUT)
    public ResponseEntity<?> agregarUsuario(@PathVariable String userid){
        try{
            manProyectos.agregarUsuario(userid);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(ProyectoExcepcion ex){
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_MODIFIED);
        }
    }
}
