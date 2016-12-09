/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

/**
 *Clase realacion, donde elementoA es el elemento enviado para crear una relacion con el elementoB con un determinado nombre
 * @author Daniela Sepulveda
 */
public abstract class Relacion {
    private String elementoA;
    private String elementoB;
    private String nombreRelacion;
    
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
    public Relacion(String A, String B, String nombre) throws ProyectoExcepcion{
        if (A.equals("") || B.equals("")) throw new ProyectoExcepcion("Favor colocar colocar nombre a los elementos de la relacion");
        elementoA=A;
        elementoB=B;
        nombreRelacion=nombre;
    }
    /**
     * Nombre del elementoA de la relacion
     * @return elementoA
     */
    public String getElementoA() {
        return elementoA;
    }
    /**
     * Enviar nombre del elementoA de la relacion
     * @param elementoA
     */
    public void setElementoA(String elementoA) {
        this.elementoA = elementoA;
    }
    /**
     * Nombre del elementoB de la relacion
     * @return elementoB
     */
    public String getElementoB() {
        return elementoB;
    }
    /**
     * Enviar nombre del elementoB de la relacion
     * @param elementoB
     */
    public void setElementoB(String elementoB) {
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
}
