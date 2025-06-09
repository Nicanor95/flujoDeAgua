/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio14.lib;

/**
 * Clase ayudante para pasar vertices con costos.
 * @author nikan
 * @param <T> 
 */
public class VerticeCosto <T> {
    T vertice;
    T costo;

    public VerticeCosto() {
    }

    public VerticeCosto(T vertice, T costo) {
        this.vertice = vertice;
        this.costo = costo;
    }

    public T getVertice() {
        return vertice;
    }

    public void setVertice(T vertice) {
        this.vertice = vertice;
    }

    public T getCosto() {
        return costo;
    }

    public void setCosto(T costo) {
        this.costo = costo;
    }
    
    
}
