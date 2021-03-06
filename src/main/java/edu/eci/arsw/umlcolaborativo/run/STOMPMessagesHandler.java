/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.run;

import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.DiagramaClases;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author German Lopez
 */
@Controller
public class STOMPMessagesHandler {
    @Autowired
    SimpMessagingTemplate msgt;
    
    /**
     * Recibe un nuevo elemento de /app/newelement y lo publica en /topic/newelement
     * @param e
     * @param proyecto
     * @param diagrama
     */
    @MessageMapping("/newelement.{proyecto}.{diagrama}")
    public void getElement(Elemento e, @DestinationVariable String proyecto, @DestinationVariable String diagrama){
        System.out.println("Nuevo elemento recibido en el servidor! :"+e.getNombre());
        msgt.convertAndSend("/topic/newelement."+proyecto+"."+diagrama, e);
    }
      /**
     * Recibe una relacion de /app/newrelationship y lo publica en /topic/newrelationship
     * @param r
     * @param proyecto
     * @param diagrama
     */
    @MessageMapping("/newrelationship.{proyecto}.{diagrama}")
    public void getRelationship(Relacion r, @DestinationVariable String proyecto, @DestinationVariable String diagrama){
        System.out.println("Nueva relacion recibida en el servidor! :"+r.getNombreRelacion());
        msgt.convertAndSend("/topic/newrelationship."+proyecto+"."+diagrama, r);
    }
    /**
     * Recibe en /app/newproject.{usrId} el nuevo proyecto de un usuario y lo publica en /topic/newproject.{usrId}
     * @param p proyecto a publicar
     * @param usrId el nombre del usuario de quien es el nuevo proyecto
     */
    @MessageMapping("/newproject.{usrId}")
    public void getProject(Proyecto p,@DestinationVariable String usrId) {
        System.out.println("Nuevo proyecto recibido en el servidor! :"+p.getNombre());
        msgt.convertAndSend("/topic/newproject."+usrId, p);
    }
    
    /**
     * Recibe en /app/newdiagram.{proyid} el nuevo diagrama de un proyecto y lo publica en /topic/newdiagram.{proyid}
     * @param d
     * @param proyid 
     */
    @MessageMapping("/newdiagram.{proyid}")
    public void getDiagram(Diagrama d,@DestinationVariable String proyid){
        System.out.println("Nuevo diagrama recibido en el servidor! :"+d.getTitulo());
        msgt.convertAndSend("/topic/newdiagram."+proyid,d);
    }
}
