/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Proyecto;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.umlcolaborativo.util.JedisUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import org.springframework.stereotype.Service;

/**
 *
 * @author ger9410
 */
//@Service
public class InMemoryProjectsRedis implements PersistenciaProyectos{
    
    public InMemoryProjectsRedis(){
         Jedis jedis = JedisUtil.getPool().getResource();
         Gson gson = new Gson();
         Map<String, Proyecto> proyectos = new HashMap<>();
         Map<String, List<String>> usuarios = new HashMap<>();
         usuarios.put("1", new ArrayList<>());
         jedis.hset("Usuarios", "todosU", gson.toJson(usuarios));
         jedis.hset("Proyectos", "todosP", gson.toJson(proyectos));
         jedis.close();
    }

    @Override
    public Map<String, Proyecto> consultarProyectosUsuario(String usuario) throws ProyectoExcepcion {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        Map<String,Proyecto> proyectos = new HashMap<>();
        validarUsuario(usuario);
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
        String cadenaProyectos = jedis.hget("Proyectos", "todosP");
        json = jsonParser.parse(cadenaProyectos);
        Map<String,Proyecto> proyectoUsuario = (Map<String,Proyecto>) gson.fromJson(json,new TypeToken<Map<String,Proyecto>>() {}.getType());
        for(String proc : usuarios.get(usuario)){
            proyectos.put(proc, proyectoUsuario.get(proc));
        }
        jedis.close();
        return proyectos;
    }

    @Override
    public Map<String, Map<String, Proyecto>> consultarProyectos() {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        Map<String,Map<String,Proyecto>> todos = new HashMap<>();
        JsonParser jsonParser = new JsonParser();
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
        for(String nombre : usuarios.keySet()){
            try {
                todos.put(nombre,consultarProyectosUsuario(nombre));
            } catch (ProyectoExcepcion ex) {
                Logger.getLogger(InMemoryProjectsRedis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jedis.close();
        return todos;
    }

    @Override
    public Proyecto consultarProyectoUsuario(String usuario, String proyecto) throws ProyectoExcepcion {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        validarUsuario(usuario);
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
        if(!usuarios.get(usuario).contains(proyecto)){
            throw new ProyectoExcepcion("El usuario " + usuario + " no colabora en el proyecto " + proyecto);
        }
        String cadenaProyectos = jedis.hget("Proyectos", "todosP");
        json = jsonParser.parse(cadenaProyectos);
        Map<String,Proyecto> proyectoUsuario = (Map<String,Proyecto>) gson.fromJson(json,new TypeToken<Map<String,Proyecto>>() {}.getType());
        Proyecto p = proyectoUsuario.get(proyecto);
        jedis.close();
        return p;
    }

    @Override
    public void agregarProyecto(String usuario, Proyecto proyecto) throws ProyectoExcepcion {
         Jedis jedis = JedisUtil.getPool().getResource();
         Gson gson = new Gson();
         JsonParser jsonParser = new JsonParser();
         validarUsuario(usuario);
         String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
         JsonElement json = jsonParser.parse(cadenaUsuarios);
         Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
         if (usuarios.get(usuario).contains(proyecto.getNombre())) {
            throw new ProyectoExcepcion("El usuario " + usuario + " ya colabora en el proyecto " + proyecto.getNombre());
        }
         usuarios.get(usuario).add(proyecto.getNombre());
         jedis.hset("Usuarios", "todosU", gson.toJson(usuarios));
         String cadenaProyectos = jedis.hget("Proyectos", "todosP");
         json = jsonParser.parse(cadenaProyectos);
         Map<String,Proyecto> proyectoUsuario = (Map<String,Proyecto>) gson.fromJson(json,new TypeToken<Map<String,Proyecto>>() {}.getType());
         proyectoUsuario.put(proyecto.getNombre(), proyecto);
         jedis.hset("Proyectos", "todosP", gson.toJson(proyectoUsuario));
         jedis.close();
    }

    @Override
    public void actualizarProyecto(String usuario, Proyecto proyecto) throws ProyectoExcepcion {
         Jedis jedis = JedisUtil.getPool().getResource();
         Gson gson = new Gson();
         JsonParser jsonParser = new JsonParser();
         validarUsuario(usuario);
         String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
         JsonElement json = jsonParser.parse(cadenaUsuarios);
         Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
         if (!usuarios.get(usuario).contains(proyecto.getNombre())) {
            throw new ProyectoExcepcion("El usuario " + usuario + " aun no colabora en el proyecto " + proyecto.getNombre());
         }
         String cadenaProyectos = jedis.hget("Proyectos", "todosP");
         json = jsonParser.parse(cadenaProyectos);
         Map<String,Proyecto> proyectoUsuario = (Map<String,Proyecto>) gson.fromJson(json,new TypeToken<Map<String,Proyecto>>() {}.getType());
         proyectoUsuario.put(proyecto.getNombre(), proyecto);
         jedis.hset("Proyectos", "todosP", gson.toJson(proyectoUsuario));
         jedis.close();
    }

    @Override
    public void agregarUsuario(String usuario) throws ProyectoExcepcion {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
        if (usuarios.containsKey(usuario)) {
            throw new ProyectoExcepcion("El usuario " + usuario + " ya se encuentra registrado");
        }
        usuarios.put(usuario, new ArrayList<>());
        jedis.hset("Usuarios", "todosU", gson.toJson(usuarios));
        jedis.close();
    }

    @Override
    public void vaciar() {
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String, List<String>> usuarios = (Map<String, List<String>>) gson.fromJson(json, new TypeToken<Map<String, List<String>>>() {}.getType());
        usuarios.clear();
        jedis.hset("Usuarios", "todosU", gson.toJson(usuarios));
        String cadenaProyectos = jedis.hget("Proyectos", "todosP");
        json = jsonParser.parse(cadenaProyectos);
        Map<String, Proyecto> proyectoUsuario = (Map<String, Proyecto>) gson.fromJson(json, new TypeToken<Map<String, Proyecto>>() {}.getType());
        proyectoUsuario.clear();
        jedis.hset("Proyectos", "todosP", gson.toJson(proyectoUsuario));
        jedis.close();
    }
    
    private void validarUsuario(String usuario) throws ProyectoExcepcion{
        Jedis jedis = JedisUtil.getPool().getResource();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        String cadenaUsuarios = jedis.hget("Usuarios", "todosU");
        JsonElement json = jsonParser.parse(cadenaUsuarios);
        Map<String,List<String>> usuarios = (Map<String,List<String>>) gson.fromJson(json,new TypeToken<Map<String,List<String>>>() {}.getType());
        if (!usuarios.containsKey(usuario)) {
            throw new ProyectoExcepcion("El usuario " + usuario + " no se encuentra registrado");
        }
        jedis.close();
    }
    
}
