package ejercicio14.lib;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Grafo <T> {
    LinkedList<Nodo> vertices;

    public Grafo() {
        vertices = new LinkedList<Nodo>();
    }
    
    /**
     * Añade el vertice al grafo.
     * @param dato Nombre del vertice, debe ser unica en el grafo.
     */
    public void addVertice(T dato) {
        Nodo vertice = new Nodo(dato);

        if (!vertices.contains(vertice)) {
            vertices.add(vertice);
        }
    }
    
    /**
     * Busca el vertice que contiene el nombre <code>dato</code>
     * @param dato El Nombre
     * @return <code>Node</code>, el vertice. O <code>null</code> si no existe.
     */
    private Nodo getVertice(T dato) {
        for (Nodo vertice: vertices) {
            if (vertice.getData().equals(dato)) {
                return vertice;
            }
        }
        return null;
    }
    
    /**
     * Busca busca un arco especifico en el vertice especificado y regresa su etiqueta, <code>null</code> si no lo encuentra.
     * @param vertice Vertice del que se parte
     * @param arco Arco a encontrar entre los arcos de <code>vertice</code>
     * @return La etiqueta del arco, <code>null</code> si no lo encuentra.
     */
    public Integer getEtiqueta(T vertice, T arco) {
        Nodo v = getArco(vertice, arco);
        
        if (v == null) {
            return null;
        }
        
        return v.getEtiqueta();
    }
    
    /**
     * Busca busca un arco especifico en el vertice especificado. <code>null</code> si no lo encuentra.
     * @param vertice Vertice del que se parte
     * @param arco Arco a encontrar entre los arcos de <code>vertice</code>
     * @return El nodo del arco.
     */
    private Nodo getArco(T vertice, T arco) {
        Nodo vert = getVertice(vertice);
        
        if (vertice == null) {
            throw new UnsupportedOperationException("Origen o destino no existe");
        }
        
        return getArco(vert, arco);
    }
    
    private Nodo getArco(Nodo vertice, T arco) {
        if (vertice == null) {
            return null;
        }
        
        if (vertice.getData().equals(arco)) {
            return vertice;
        }
        
        return getArco(vertice.getNext(), arco);
    }
    
    /**
     * Añade el arco <code>origen -> destino</code>
     * @param origen Nombre del vertice origen
     * @param destino Nombre del vertice destino
     * @param etiqueta Etiqueta del arco
     * @throws UnsupportedOperationException si <code>origen</code> o <code>destino</code> no existen.
     */
    public void addArco(T origen, T destino, int etiqueta) throws UnsupportedOperationException {
        Nodo vertice = getVertice(origen);
        
        if (getVertice(destino) == null || vertice == null) {
            throw new UnsupportedOperationException("Origen o destino no existe");
        }
        
        addArco(vertice, destino, etiqueta);
    }
    
    /**
     * Añade el arco <code>origen -> destino</code>
     * @param origen Nombre del vertice origen
     * @param destino Nombre del vertice destino
     * @throws UnsupportedOperationException si <code>origen</code> o <code>destino</code> no existen.
     */
    public void addArco(T origen, T destino) throws UnsupportedOperationException {
        Nodo vertice = getVertice(origen);
        
        if (getVertice(destino) == null || vertice == null) {
            throw new UnsupportedOperationException("Origen o destino no existe");
        }
        
        addArco(vertice, destino);
    }
    
    private void addArco(Nodo origen, T destino) {
        if (!origen.getData().equals(destino)) {
            if (!origen.hasNext()) {
                origen.setNext(new Nodo(destino));
            } else {
                addArco(origen.next, destino);
            }
        }
    }
    
    private void addArco(Nodo origen, T destino, int etiqueta) {
        if (!origen.getData().equals(destino)) {
            if (!origen.hasNext()) {
                origen.setNext(new Nodo(destino, etiqueta));
            } else {
                addArco(origen.next, destino, etiqueta);
            }
        }
    }
    
    /**
     * Elimina el arco <code>origen -> destino</code>
     * @param origen Nombre del <em>vertice</em> del cual eliminar el arco a <code>destino</code>
     * @param destino Nombre del <em>vertice</em> destino a eliminar
     */
    public void delArco(T origen, T destino) {
        Nodo vertice = getVertice(origen);
        if (vertice != null) {
            delArco(vertice, destino);
        }
    }
    
    private void delArco(Nodo origen, T destino) {
        if (origen.hasNext()){
            if (origen.next.equals(new Nodo(destino))) {
                origen.next = origen.next.next;
            } else {
                delArco(origen.next, destino);
            }
        }
    }
    
    /**
     * Calcula el grado saliente del vertice con nombre <code>dato</code>
     * @param dato El <em>nombre</em> del vertice
     * @return El grado saliente del vertice, o -1 si no se encuentra. 
     */
    public int gradoSal(T dato) {
        Nodo vertice = getVertice(dato);
        if (vertice != null) {
            return gradoSal(vertice);
        }
        return -1; // No se encontró el vertice.
    }
    
    private int gradoSal(Nodo vertice) {
        if (!vertice.hasNext()) {
            return 0;
        } else {
            return 1 + gradoSal(vertice.next);
        }
    }
    
    /**
     * Calcula el grado entrante del vertice con nombre <code>dato</code>
     * @param dato Nombre del vertice.
     * @return Grado entrante del vertice con nombre <code>dato</code>
     */
    public int gradoEnt(T dato) {
        int acumulador = 0;
        for (Nodo vertice: vertices) {
            if (!vertice.getData().equals(dato)) {
                acumulador += gradoEnt(vertice, dato);
            }
        }
        return acumulador;
    }
    
    private int gradoEnt(Nodo vertice, T dato) {
        if (vertice.getData().equals(dato)) {
            return 1;
        }
        
        if (!vertice.hasNext()) {
            return 0;
        }
        
        return gradoEnt(vertice.getNext(), dato);
    }
    
    
    /**
     * Regresa las Nombres de los vertices adyacentes al vertice con nombre <code>dato</code>
     * @param dato Nombre del vertice.
     * @return Lista de los nombres.
     */
    public ArrayList right(T dato) {
        Nodo vertice = getVertice(dato);
        if (vertice != null) {
            ArrayList<T> adyacentes = new ArrayList();
            if (vertice.hasNext()) {
                adyacentes.addAll(right(vertice.next));
            }
            return adyacentes;
        }
        return null; // No se encontro el vertice
    }
    
    private ArrayList right(Nodo vertice) {
        ArrayList retorno = new ArrayList();
        retorno.add(vertice.data);
        
        if(!vertice.hasNext()) {
            return retorno;
        }
        
        retorno.addAll(right(vertice.next));
        return retorno;
    }
    
    /**
     * Familia extendida del vertice con nombre <code>dato</code>
     * @param dato Nombre del vertice
     * @return Familia extendida del vertice de nombre <code>dato</code>
     */
    public ArrayList rightExt(T dato) {
        Nodo vertice = getVertice(dato);
        if (vertice != null) {
            return rightExt(vertice, new ArrayList());
        }
        
        return null;
    }
    
    private ArrayList rightExt(Nodo vertice, ArrayList visitados) {
        if (vertice == null || visitados.contains(vertice.getData())) {
            return new ArrayList();  
        }
        
        visitados.add(vertice.getData());
        
        Set<T> queue = new HashSet<T>();
        queue.addAll(right(vertice));
        
        for (T v: queue) {
            rightExt(getVertice(v), visitados);
        }
        
        return visitados;
    }
    
    
    /**
     * Regresa las Nombres de los vertices incidentes al vertice con nombre <code>dato</code>
     * @param dato Nombre del vertice
     * @return Lista de incidentes en vertice de nombre <code>dato</code>
     */
    public ArrayList left(T dato) {
        Nodo vertice = getVertice(dato);
        
        if (vertice == null) {
            return null; // No se encontró el vertice.
        }
        
        ArrayList incidentes = new ArrayList();
        
        for (Nodo v: vertices) {
            if (!v.equals(vertice) && isLeft(v,vertice)) {
                incidentes.add(v.getData());
            }
        }
        
        return incidentes;
    }
    
    /**
     * Regresa si el <code>vertice</code> es incidente en <code>origen</code>
     * @param vertice El vertice a chequear
     * @param origen
     * @return <code>true</code> si hay un arco <code>vertice -> origen</code>
     */
    private boolean isLeft(Nodo vertice, Nodo origen) {
        if (vertice.equals(origen)) {
            return true;
        }
        
        if (!vertice.hasNext()) {
            return false;
        }
        
        return isLeft(vertice.next, origen);
    }
    
    /**
     * Regresa una lista con la familia incidente extendida del vertice con nombre <code>dato</code>
     * @param dato Nombre del vertice.
     * @return Lista de incidentes extendida del vertice de nombre <code>dato</code>
     */
    public ArrayList leftExt(T dato) {
        Nodo vertice = getVertice(dato);
        
        if (vertice == null) {
            return null; // No se encontró el vertice.
        }
        
        return leftExt(vertice, new ArrayList());
    }
    
    private ArrayList leftExt(Nodo vertice, ArrayList visitados) {
        if (vertice == null || visitados.contains(vertice.getData())) {
            return new ArrayList();  
        }
        
        visitados.add(vertice.getData());
        
        Set<T> queue = new HashSet<T>();
        queue.addAll(left((T) vertice.data));
        
        for (T v: queue) {
            leftExt(getVertice(v), visitados);
        }
        
        return visitados;
    }
}
