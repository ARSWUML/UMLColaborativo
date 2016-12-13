/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *Clase realacion, donde elementoA es el elemento enviado para crear una relacion con el elementoB con un determinado nombre
 * @author Daniela Sepulveda
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RelacionAsociacion.class, name = "Asociacion"),
    @JsonSubTypes.Type(value = RelacionDependencia.class, name = "Dependencia"),
})
public abstract class Relacion {
    protected Elemento elementoA;
    protected Elemento elementoB;
    protected String nombreRelacion;
    protected boolean isbidireccional;
    
    /**
     * Crea un relacion
     */
    public Relacion() {
    }
    /**
     * Crea un relacion del elemento A al elemento B con ese nombre.
     * @param A
     * @param B
     * @param nombre
     * @throws edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion
     */
    public Relacion(Elemento A, Elemento B, String nombre) throws ProyectoExcepcion{
        if (A==null || B==null) throw new ProyectoExcepcion("Por favo, verifique los elementos. No es posible asignar la relacion");
        elementoA=A;
        elementoB=B;
        nombreRelacion=nombre;
    }
    /**
     * Constructor clase Relacion
     * @param nombre
     */
    public Relacion(String nombre){
        nombreRelacion=nombre;
    }
    /**
     * Nombre del elementoA de la relacion
     * @return elementoA
     */
    public Elemento getElementoA() {
        return elementoA;
    }
    /**
     * Enviar nombre del elementoA de la relacion
     * @param elementoA
     */
    public void setElementoA(Elemento elementoA) {
        this.elementoA = elementoA;
    }
    /**
     * Nombre del elementoB de la relacion
     * @return elementoB
     */
    public Elemento getElementoB() {
        return elementoB;
    }
    /**
     * Enviar nombre del elementoB de la relacion
     * @param elementoB
     */
    public void setElementoB(Elemento elementoB) {
        this.elementoB = elementoB;
    }
    /**
     * Consultar nombre de la relacion
     * @return elementoB
     */
    public String getNombreRelacion() {
        return nombreRelacion;
    }
    /**
     * Enviar nombre de la relacion
     * @param nombreRelacion 
     */
    public void setNombreRelacion(String nombreRelacion) {
        this.nombreRelacion = nombreRelacion;
    }    
    /**
     * Consultar direccion
     * @return isbidireccional
     */
    public boolean isIsbidireccional() {
        return isbidireccional;
    }
     /**
     * Enviar direccion
     * @param isbidireccional 
     */
    public void setIsbidireccional(boolean isbidireccional) {
        this.isbidireccional = isbidireccional;
    }
    
    @Override
    public boolean equals(Object o){
        Relacion o2 = (Relacion)o;
        return o2.getElementoA().equals(elementoA) && o2.getElementoB().equals(elementoB) && o2.nombreRelacion.equals(nombreRelacion);
    }
}
