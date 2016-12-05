/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.umlcolaborativo.services;

import edu.eci.arsw.umlcolaborativo.entities.Clase;
import edu.eci.arsw.umlcolaborativo.entities.ClaseAbstracta;
import edu.eci.arsw.umlcolaborativo.entities.Elemento;
import edu.eci.arsw.umlcolaborativo.entities.Interface;
import edu.eci.arsw.umlcolaborativo.entities.ProyectoExcepcion;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;


/**
 *
 * @author ger9410
 */
@Service
public final class InMemoryElements implements PersistenciaElementos{
    private Map<String,Elemento> elementos;
    
    public InMemoryElements() throws ProyectoExcepcion{
        elementos=new HashMap<>();
        cargarElementos();
    }
    
    /**
     * @return the elementos
     */
     @Override
    public Map<String,Elemento> getElementos() {
        return elementos;
    }

    
    /**
     * @param elementos the elementos to set
     */
    @Override
    public void setElementos(Map<String,Elemento> elementos) {
        this.elementos = elementos;
    }

    
    /**
     * 
     * @param nombre el nombre o tipo del elemento a consultar
     * @return el elemento solicitado
     */
    @Override
    public Elemento consultarElemento(String nombre){
        return elementos.get(nombre);
    }
    
    
    public void cargarElementos() throws ProyectoExcepcion{
        Elemento claseAsbtracta = new ClaseAbstracta("Abstract Class");
        Elemento interfaz = new Interface("Interface");
        Elemento clase = new Clase("Class");
        elementos.put(claseAsbtracta.getNombre(), claseAsbtracta);
        elementos.put(clase.getNombre(), clase);
        elementos.put(interfaz.getNombre(), interfaz);
    }
    
    
}
