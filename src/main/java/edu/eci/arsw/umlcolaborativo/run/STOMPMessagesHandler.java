/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.run;

import edu.eci.arsw.umlcolaborativo.entities.Diagrama;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ger9410
 */
@Controller
public class STOMPMessagesHandler {
    @Autowired
    SimpMessagingTemplate msgt;
    
    /**
     * Recibe un nuevo elemento de /app/newelement y lo publica en /topic/newelement
     * @param e
     */
    @MessageMapping("/newelement")
    public void getElement(Elemento e){
        System.out.println("Nuevo elemento recibido en el servidor! :"+e.getNombre());
        msgt.convertAndSend("/topic/newelement", e);
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
