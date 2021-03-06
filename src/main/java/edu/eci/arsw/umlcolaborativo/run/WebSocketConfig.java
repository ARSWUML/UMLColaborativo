/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.run;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author Julian Devia
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
    @Value("${activeMQ.ip}")
    private String ip;
    @Value("${activeMQ.port}")
    private String port;
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        //config.enableStompBrokerRelay("/topic/").setRelayHost(ip).setRelayPort(Integer.parseInt(port));
        /*config.enableStompBrokerRelay("/topic/").setRelayHost("antelope.rmq.cloudamqp.com").setRelayPort(61613).
                setClientLogin("rxxxnxuj").
                setClientPasscode("tRQw2K11b8jXwspblkzl5k2MXD7NSCBk").
                setSystemLogin("rxxxnxuj").
                setSystemPasscode("tRQw2K11b8jXwspblkzl5k2MXD7NSCBk").
                setVirtualHost("rxxxnxuj");*/
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();
        
    }
    

}
