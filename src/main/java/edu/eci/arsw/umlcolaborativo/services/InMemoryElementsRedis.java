/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.umlcolaborativo.entities.Clase;
import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import edu.eci.arsw.umlcolaborativo.util.InterfaceAdapter;
import edu.eci.arsw.umlcolaborativo.util.JedisUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 *
 * @author ger9410
 */
//@Service
public class InMemoryElementsRedis implements PersistenciaElementos{
    
    Gson gson;
    JsonParser jsonParser;
    JsonElement json;
    
    public InMemoryElementsRedis() throws ProyectoExcepcion{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(Elemento.class, new InterfaceAdapter<Elemento>());
        gson = builder.create();
        //gson = new Gson();  
        jsonParser = new JsonParser();
        cargarElementos();
        System.out.println("Cargo los elementos");
    }

    @Override
    public Map<String, Elemento> getElementos() {
        Jedis jedis = JedisUtil.getPool().getResource();
        String cadenaElementos = jedis.hget("Elementos", "todosE");
        json = jsonParser.parse(cadenaElementos);
        Map<String,Elemento> elementos = (Map<String,Elemento>) gson.fromJson(json,new TypeToken<Map<String,Elemento>>() {}.getType());
        jedis.close();
        return elementos;
    }

    @Override
    public void setElementos(Map<String, Elemento> elementos) {
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hset("Elementos", "todosE", gson.toJson(elementos));
        jedis.close();
    }

    @Override
    public Elemento consultarElemento(String nombre) {
        Jedis jedis = JedisUtil.getPool().getResource();
        String cadenaElementos = jedis.hget("Elementos", "todosE");
        json = jsonParser.parse(cadenaElementos);
        Map<String,Elemento> elementos = (Map<String,Elemento>) gson.fromJson(json,new TypeToken<Map<String,Elemento>>() {}.getType());
        Elemento e = elementos.get(nombre);
        return e;
    }
    
    public void cargarElementos() throws ProyectoExcepcion{
        Jedis jedis = JedisUtil.getPool().getResource();
        Map<String, Elemento> elementos = new HashMap<>();
        Elemento claseAsbtracta = new ClaseAbstracta("Abstract Class");
        Elemento interfaz = new Interface("Interface");
        Elemento clase = new Clase("Class");
        elementos.put(claseAsbtracta.getNombre(), claseAsbtracta);
        elementos.put(clase.getNombre(), clase);
        elementos.put(interfaz.getNombre(), interfaz);
        jedis.hset("Elementos", "todosE", gson.toJson(elementos));
        jedis.close();
    }
    
}
