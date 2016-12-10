/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ger9410
 */
public class DiagramaClases extends DiagramaEstructural{
    private Map<String,Elemento> elementos;
    private List<Relacion> relaciones;
    
    /**
     * Crear un diagrma de clases
     * @param titulo
     * @param descripcion
     * @param dateCreacion
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
    */
    public DiagramaClases(String titulo,String descripcion,Date dateCreacion) throws ProyectoExcepcion{
        super(titulo,descripcion,dateCreacion);
        elementos=new HashMap<>();
        relaciones=new ArrayList<>();
    }
    /**
     * Crear un diagrma de clases
     * @param titulo
     * @param descripcion
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
    */
    public DiagramaClases(String titulo,String descripcion) throws ProyectoExcepcion{
        super(titulo,descripcion);
        elementos=new HashMap<>();
        relaciones=new ArrayList<>();
    }
    /**
     * Crear un diagrma de clases
    */
    public DiagramaClases(){
        elementos=new HashMap<>();
        relaciones=new ArrayList<>();
    }
 
    /**
     * Agregar elemento al diagrama
     * @param e
     * @throws ProyectoExcepcion
     */
    public void agregarElemento(Elemento e) throws ProyectoExcepcion{
        if(elementos.get(e.getNombre())==null){
            fechaUltimaModificacion=new Timestamp(new Date().getTime());
            getElementos().put(e.getNombre(), e);
        }else{
             throw new ProyectoExcepcion("El elemento con nombre "+e.getNombre()+" ya existe por favor cambiele el nombre");
        }
    }
    /**
     * Consultar elemento del diagrama
     * @param nombre
     * @return 
    */
    public Elemento consultarElemento(String nombre){
        return getElementos().get(nombre);
    }
    
    /**
     * @return the elementos
     */
    public Map<String,Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(Map<String,Elemento> elementos) {
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
        this.elementos = elementos;
    }
     /**
     * Agregar relacion al diagrama
     * @param a
     */
    public void agregarRelacion(Relacion a){
        relaciones.add(a);
    }
    
    /**
     * Retorn la lista de relaciones 
     * @return relaciones
     */
    public List<Relacion> getRelaciones(){
        return relaciones;
    }
}
