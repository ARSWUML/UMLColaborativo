/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.entities;

import java.util.Date;

/**
 *
 * @author ger9410
 */
public abstract class DiagramaEstructural implements Diagrama{
    protected String nombre;
    protected String descripcion;
    protected Date fechaCreacion;
    protected Date fechaUltimaModificacion;
}
