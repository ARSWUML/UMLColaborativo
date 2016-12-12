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
import edu.eci.arsw.umlcolaborativo.entities.Relacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionAsociacion;
import edu.eci.arsw.umlcolaborativo.entities.RelacionDependencia;
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
public class InMemoryRelationsRedis implements PersistenciaRelaciones{
    
    Gson gson;
    JsonParser jsonParser;
    JsonElement json;
    
    public InMemoryRelationsRedis(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(Relacion.class, new InterfaceAdapter<Relacion>());
        gson = builder.create();
        //gson = new Gson();  
        jsonParser = new JsonParser();
    }

    @Override
    public Map<String, Relacion> getRelaciones() {
        Jedis jedis = JedisUtil.getPool().getResource();
        String cadenaRelaciones = jedis.hget("Relaciones", "todosR");
        json = jsonParser.parse(cadenaRelaciones);
        Map<String,Relacion> relaciones = (Map<String,Relacion>) gson.fromJson(json,new TypeToken<Map<String,Relacion>>() {}.getType());
        jedis.close();
        return relaciones;
    }

    @Override
    public void setRealciones(Map<String, Relacion> relaciones) {
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hset("Relaciones", "todosR", gson.toJson(relaciones));
        jedis.close();
    }

    @Override
    public Relacion consultarRelacion(String nombre) {
        Jedis jedis = JedisUtil.getPool().getResource();
        String cadenaRelaciones = jedis.hget("Relaciones", "todosR");
        json = jsonParser.parse(cadenaRelaciones);
        Map<String,Relacion> relaciones = (Map<String,Relacion>) gson.fromJson(json,new TypeToken<Map<String,Relacion>>() {}.getType());
        jedis.close();
        return relaciones.get(nombre);
    }
    
    public void cargarRelaciones(){
        Jedis jedis = JedisUtil.getPool().getResource();
        Map<String,Relacion> relaciones = new HashMap<>();
        Relacion relacionAsociacion = new RelacionAsociacion("Relacion Asociacion");
        Relacion relacionDependencia = new RelacionDependencia("Relacion Dependencia");
        relaciones.put(relacionAsociacion.getNombreRelacion(), relacionAsociacion);
        relaciones.put(relacionDependencia.getNombreRelacion(), relacionDependencia);
        jedis.hset("Relaciones", "todosR", gson.toJson(relaciones));
        jedis.close();
    }
    
}
