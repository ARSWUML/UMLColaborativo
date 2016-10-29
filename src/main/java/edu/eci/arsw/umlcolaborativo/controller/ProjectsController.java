/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.controller;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
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
        Map<String,Map<String,Proyecto>> proyectos= manProyectos.getProyectos();
      return new ResponseEntity<>(proyectos,HttpStatus.ACCEPTED);
    }
    
    /**
     * @pre: Usuario existente, proyectos existentes
     * @post: Retorna los proyectos en los que un usuario esta colaborando
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path = "/{userid}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarProyectosUsuario(@PathVariable String userid){
        Map<String,Proyecto> proyectosUsuario=manProyectos.consultarProyectos(userid);
        return new ResponseEntity<>(proyectosUsuario,HttpStatus.ACCEPTED);
    }
    
    /**
     * @pre: Usuario existente, proyecto existente
     * @post: Retorna un proyecto en el cual un usuario esta colaborando
     * @param projectid
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping(path = "/{projectid}/{userid}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarProyectoUsuario(@PathVariable String projectid, @PathVariable String userid){
        Proyecto proyectoUsuario=manProyectos.consultarProyecto(userid, projectid);
        return new ResponseEntity<>(proyectoUsuario,HttpStatus.ACCEPTED);
    }
    /**
     * @pre: Ninguna
     * @pos: Agrega un proyecto con su usuario respectivo
     * @param project
     * @param userid
     * @return ResponseEntity<?>
     */
    @RequestMapping( method = RequestMethod.PUT)
    public ResponseEntity<?> agregarProyecto(@RequestBody Proyecto project, @RequestBody String userid){
        manProyectos.agregarProyecto(userid, project);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
