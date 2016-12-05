/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ger9410
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DiagramaClases.class, name = "clases")
})
public abstract class Diagrama{
    
    protected String titulo;
    protected String descripcion;
    protected Date fechaCreacion;
    protected Date fechaUltimaModificacion;
    
   
   /**
    * Crear diagrama con el titulo que lo identifica, la  descripcion fecha de creacion que sera la misma para fecha de modificacion
    * @param titulo
     * @param descripcion
     * @param dateCreacion
    */
    
    /**
     * Crear diagrama con el titulo que lo identifica, la  descripcion fecha de creacion que sera la misma para fecha de modificacion
     * @param titulo
     * @param descripcion
     * @param dateCreacion
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Diagrama(String titulo,String descripcion,Date dateCreacion) throws ProyectoExcepcion{
       if(titulo.equals("")) throw new ProyectoExcepcion("Favor colocar un titulo apropiado al diagrama");
       if(descripcion.equals("")) throw new ProyectoExcepcion("Favor colocar una descripcion apropiada al diagrama");
       if(dateCreacion==null) throw new ProyectoExcepcion("Favor colocar una fecha de creacion al diagrama");
       this.titulo=titulo;
       this.descripcion=descripcion;
       this.fechaCreacion=dateCreacion;
       this.fechaUltimaModificacion=dateCreacion;
   }
      /**
    * Crear diagrama con el titulo que lo identifica, su descripcion. 
    * @param titulo
     * @param descripcion
    */
   public Diagrama(String titulo,String descripcion) throws ProyectoExcepcion{
       if(titulo.equals("")) throw new ProyectoExcepcion("Favor colocar un titulo apropiado al diagrama");
       if(descripcion.equals("")) throw new ProyectoExcepcion("Favor colocar una descripcion apropiada al diagrama");
      this.titulo=titulo;
       this.descripcion=descripcion;
       this.fechaCreacion=new Timestamp(new Date().getTime());
       this.fechaUltimaModificacion=new Timestamp(new Date().getTime());
    }
    /**
    * Crear diagrama 
    */
    public Diagrama(){
    }
   /**
    * Consultar el titulo 
     * @return 
    */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Modificar el titulo
     * @param titulo
    */
    public void setTitulo(String titulo) {
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
        this.titulo = titulo;
    }
    /**
    * Consultar la descripcion
     * @return 
    */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Modificar la descripcion
     * @param Descripcion
    */
    public void setDescripcion(String Descripcion) {
        fechaUltimaModificacion=new Timestamp(new Date().getTime());
        this.descripcion = Descripcion;
    }
    /**
    * Consultar fecha creacion
     * @return 
    */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    /**
     * Modificar la fecha creacion
     * @param fechaCreacion
    */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
     /**
    * Consultar fecha ultima modificacion
     * @return 
    */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }
    /**
     * Modificar la fecha de ultima modificacion
     * @param fechaUltimaModificacion
    */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    @Override
    public boolean equals(Object o){
        Diagrama o2=(Diagrama)o;
        return o2.getTitulo().equals(titulo) && o2.getDescripcion().equals(descripcion);
    }
}
