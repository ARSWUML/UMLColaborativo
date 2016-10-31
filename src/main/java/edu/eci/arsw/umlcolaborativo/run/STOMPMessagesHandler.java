/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.run;

import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @throws Exception 
     */
    @MessageMapping("/newelement")
    public void getElement(Elemento e) throws Exception {
        System.out.println("Nuevo elemento recibido en el servidor! :"+e.getNombre());
        msgt.convertAndSend("/topic/newelement", e);
    }
}
