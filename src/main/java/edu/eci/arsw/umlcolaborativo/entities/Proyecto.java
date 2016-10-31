/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author amoto
 */
public class Proyecto {
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Map<String,Diagrama> diagramas;
    
    public Proyecto(String name, String description){
        nombre=name;
        descripcion=description;
        fechaCreacion=new Timestamp(new Date().getTime());
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
        diagramas= new HashMap<>();
    }
    
    public Proyecto(){
    }
    
    /**
     * Agrega diagramas al proyecto
     * @param d
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public void agregarDiagrama(Diagrama d) throws ProyectoExcepcion{
        if(diagramas.get(d.getTitulo())==null){
            diagramas.put(d.getTitulo(), d);
            fechaUltimaModificacion=new Timestamp(new Date().getTime());
        }else{
            throw new ProyectoExcepcion("El diagrama con titulo "+d.getTitulo()+" ya existe por favor cambie el nombre");
        }
    }
    
    /**
     * @pos: Retorna un diagrama por su nombre
     * @param nombre
     * @return diagrama
     */
    public Diagrama consultarDiagrama(String nombre){
            return getDiagramas().get(nombre); 
        }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }

    /**
     * @return the fechaUltimaModificacion
     */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    /**
     * @param fechaUltimaModificacion the fechaUltimaModificacion to set
     */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }

    /**
     * @return the diagramas
     */
    public Map<String,Diagrama> getDiagramas() {
        return diagramas;
    }

    /**
     * @param diagramas the diagramas to set
     */
    public void setDiagramas(Map<String,Diagrama> diagramas) {
        this.diagramas = diagramas;
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }
}
