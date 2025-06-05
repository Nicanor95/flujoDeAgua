package ejercicio14.lib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GrafoM <T> {
    ArrayList<T> vertices;
    Integer [][] arcos;
    int size;
    
    public GrafoM() {
        vertices = new ArrayList<T>();
        size = -1;
    }
    
    public GrafoM(int size) {
       vertices = new ArrayList<T>();
       arcos = new Integer[size][size];
       this.size = size;
    }
    
    public GrafoM(ArrayList<T> vertices) {
        this.vertices = vertices;
        arcos = new Integer[vertices.size()][vertices.size()];
    }
    
    /**
     * Agrega un vertice al grafo. Extiende el arreglo si no queda espacio en el mismo.
     * @param vertice el nombre del vertice
     */
    public void agregarVertice(T vertice) {
        vertices.add(vertice);
        if (vertices.size() > size) {
            Integer [][] nuevoArcos = new Integer[vertices.size()][vertices.size()];
            if (arcos != null) {
                for (int i = 0; i < size; i++) {
                    System.arraycopy(arcos[i], 0, nuevoArcos[i], 0, size);
                }
            }
            this.size = vertices.size();
            arcos = nuevoArcos;
        }
    }
    
    /**
     * Agrega un Arco <code>origen -> destino</code> con etiqueta 0.
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @throws UnsupportedOperationException 
     */
    public void agregarArco(T origen, T destino) throws UnsupportedOperationException {
        int indexOrigen = vertices.indexOf(origen);
        int indexDestino = vertices.indexOf(destino);
        
        if (indexOrigen != -1 && indexDestino != -1) {
            arcos[indexOrigen][indexDestino] = 0;
        } else {
            throw new UnsupportedOperationException("Origen o destino no existe.");
        }
    }
    
    /**
     * Agrega un Arco <code>origen -> destino</code> con etiqueta <code>etiqueta</code>.
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @param etiqueta Etiqueta del  arco, normalmente el costo del mismo.
     * @throws UnsupportedOperationException 
     */
    public void agregarArco(T origen, T destino, int etiqueta) throws UnsupportedOperationException {
        int indexOrigen = vertices.indexOf(origen);
        int indexDestino = vertices.indexOf(destino);
        
        if (indexOrigen != -1 && indexDestino != -1) {
            arcos[indexOrigen][indexDestino] = etiqueta;
        } else {
            throw new UnsupportedOperationException("Origen o destino no existe.");
        }
    }
    
    /**
     * Elimina el arco <code>origen -> destino</code>.
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @throws UnsupportedOperationException Cuando origen o destino no existen.
     */
    public void eliminarArco(T origen, T destino) throws UnsupportedOperationException {
        int indexOrigen = vertices.indexOf(origen);
        int indexDestino = vertices.indexOf(destino);
        
        if (indexOrigen != -1 && indexDestino != -1) {
            arcos[indexOrigen][indexDestino] = null;
        } else {
            throw new UnsupportedOperationException("Origen o destino no existe.");
        }
    }
    
    /**
     * Regresa la etiqueta del arco, usualmente el costo del mismo.
     * @param origen Vertice de origen
     * @param destino Vertice de destino
     * @return Etiqueta del arco
     */
    public Integer getEtiqueta(T origen, T destino) {
        int indexOrigen = vertices.indexOf(origen);
        int indexDestino = vertices.indexOf(destino);
        
        if (indexOrigen != -1 && indexDestino != -1) {
            return arcos[indexOrigen][indexDestino];
        }
        
        return null;
    }
    
    /**
     * Interactivo para cargar el grafo.
     */
    public void inicializar() {
        Scanner input = new Scanner(System.in);
        
        do {
            System.out.printf("vertice: ");
            this.agregarVertice((T) input.nextLine()); // IDK
            
            System.out.println("Agegar mas vertices? S/N");
        } while (input.nextLine().toLowerCase().startsWith("s"));
        
        size = vertices.size();
        arcos = new Integer[size][size];
    }
    
    /**
     * Grafica la matriz en stdout.
     */
    public void dibujarMatriz() {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (arcos[i][j] != null) {
                    System.out.print(" | V");
                } else {
                    System.out.print(" | F");
                }
            }
            System.out.println(" |");
        }
    }
    
    /**
     * Regresa el grado incidente en <code>vertice</code>
     * @param vertice El vertice
     * @return La cantidad de vertices que tienen <code>vertice</code> como destino.
     */
    public int gradoEntrante(T vertice) {
        int index = vertices.indexOf(vertice);
        int contador = 0;
        
        for (int i = 0; i < vertices.size(); i++) {
            if (arcos[i][index] != null) {
                contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * Rregresa el grado adyacente de <code>vertice</code>
     * @param vertice El vertice
     * @return La cantidad de vertices adyacentes a <code>vertice</code>
     */
    public int gradoSaliente(T vertice) {
        int index = vertices.indexOf(vertice);
        int contador = 0;
        
        for (int i = 0; i < vertices.size(); i++) {
            if (arcos[index][i] != null) {
                contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * Regresa una lista de los vertices adyacentes de <code>vertice</code>
     * @param vertice 
     * @return Lista de los vertices adyacentes de <code>vertice</code>.
     */
    public LinkedList<T> right(T vertice) {
        int index = vertices.indexOf(vertice);
        LinkedList<T> adyacentes = new LinkedList<>();
        
        for (int i = 0; i < vertices.size(); i++) {
            if (arcos[index][i] != null) {
                adyacentes.add(vertices.get(i));
            }
        }
        
        return adyacentes;
    }
    
    /**
     * Regresa una lista de vertices incidentes en <code>vertice</code>
     * @param vertice 
     * @return Lista de vertices incidentes en <code>vertice</code>
     */
    public LinkedList<T> left(T vertice) {
        int index = vertices.indexOf(vertice);
        LinkedList<T> incidentes = new LinkedList<>();
     
        for (int i = 0; i < vertices.size(); i++) {
            if (arcos[i][index] != null) {
                incidentes.add(vertices.get(i));
            }
        }
        
        return incidentes;
    }
    
    /**
     * Regresa una lista extendida de adyacentes de <code>vertice</code>
     * @param vertice 
     * @return  Lista extendida de adyacentes de <code>vertice</code>
     */
    public LinkedList<T> rightExtended(T vertice) {
        return rightExtended(vertice, new LinkedList<T>());
    }
    
    private LinkedList<T> rightExtended(T vertice, LinkedList<T> visitados) {
        if (vertices.contains(vertice) && !visitados.contains(vertice)) {
            int index = vertices.indexOf(vertice);
            
            visitados.add(vertice);
            
            for (int i = 0; i < vertices.size(); i++) {
                if (arcos[index][i] != null) {
                    rightExtended(vertices.get(i), visitados);
                }
            }
            return visitados;
        } 
        return new LinkedList();
    }
    
    /**
     * Regresa una lista extendida de incidentes en <code>vertice</code>
     * @param vertice 
     * @return Lista extendida de incidentes en <code>vertice</code>
     */
    public LinkedList<T> leftExtended(T vertice) {
        return leftExtended(vertice, new LinkedList<T>());
    }
    
    private LinkedList<T> leftExtended(T vertice, LinkedList<T> visitados) {
        if (vertices.contains(vertice) && !visitados.contains(vertice)) {
            int index = vertices.indexOf(vertice);
            
            visitados.add(vertice);
            
            for (int i = 0; i < vertices.size(); i++) {
                if (arcos[i][index] != null) {
                    leftExtended(vertices.get(i), visitados);
                }
            }
            return visitados;
        } 
        return new LinkedList();
    }
    
    /**
     * Regresa el indice del <code>Integer</code> minimo en <code>arr</code>
     * Toma solo los indices en <code>available</code>
     * @param arr arreglo de ingreso
     * @param available indices a chequear en <code>arr</code>
     * @return Indice del minimo en <code>arr</code> tal que este en <code>available</code>
     */
    private Integer indexMin(Integer[] arr, List<Integer> available) {
        int smallest = available.getFirst(); // Fill max with a value in available
        
        for(int i : available) {
            if (arr[i] < smallest) {
                smallest = i;
            }
        }
        
        return smallest;
    }
    
    private Integer indexMax(Integer[] arr, List<Integer> available) {
        int max = available.getFirst(); // Fill max with a value in available
        
        for(int i : available) {
            if (!arr[i].equals(Integer.MAX_VALUE) && arr[i] > max) {
                max = i;
            }
        }
        
        return max;
    }
    
    /**
     * Arma una matriz de costos
     * 
     * @return Una matriz con los costos de todos los arcos en el grafo.
     */
    public Integer[][] getCostos() {
        Integer[][] costos = new Integer[size][size];
        
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == i) {
                    costos[i][j] = 0;
                } else if (arcos[i][j] == null) {
                    costos[i][j] = Integer.MAX_VALUE;
                } else {
                    costos[i][j] = arcos[i][j];
                }
            }
        }
        
        return costos;
    }
    
    /**
     * Regresa un arreglo con los costos minimos desde un vertice <code>origen</code> a cada uno de los vertices.
     * @param origen Vertice de origen
     * @return Arreglo con costos minimos de <code>origen</code> a el resto de vertices alcanzables.
     */
    public Integer[] dijkstra(T origen) {
        Integer[][] costos = getCostos();
        Integer[] costosMinimos = new Integer[size];
        Integer indexOrigen = vertices.indexOf(origen);
        LinkedList<Integer> vertQueue = new LinkedList<>();
        
        for (int i = 0; i < size; i++) {
            costosMinimos[i] = costos[indexOrigen][i]; // Copiamos los costos minimos de una fila
            if (!indexOrigen.equals(i)) { 
                vertQueue.add(i); // Agregamos los indices de cada uno de los vertices a probar
            }
        }
        
        while (!vertQueue.isEmpty()) {
            Integer indexMinimo = indexMin(costosMinimos, vertQueue);
            
            // Integer removes the element whose value matches
            vertQueue.remove(indexMinimo);
            
            for (int queuedVert : vertQueue) {
                if (costosMinimos[indexMinimo] != Integer.MAX_VALUE && costos[indexMinimo][queuedVert] != Integer.MAX_VALUE) {
                    if (costosMinimos[queuedVert] > costosMinimos[indexMinimo] + costos[indexMinimo][queuedVert]) {
                        costosMinimos[queuedVert] = costosMinimos[indexMinimo] + costos[indexMinimo][queuedVert];
                    }
                } 
            }
        }
        
        for (int i = 0; i < size; i++) {
            if (costosMinimos[i] == Integer.MAX_VALUE) costosMinimos[i] = null;
        }
        
        return costosMinimos;
    }
    
    public Integer[] dijkstraInvertido(T origen) {
        Integer[][] costos = getCostos();
        Integer[] costosMaximos = new Integer[size];
        Integer indexOrigen = vertices.indexOf(origen);
        LinkedList<Integer> vertQueue = new LinkedList<>();
        
        for (int i = 0; i < size; i++) {
            costosMaximos[i] = costos[indexOrigen][i]; // Copiamos los costos minimos de una fila
            if (!indexOrigen.equals(i)) { 
                vertQueue.add(i); // Agregamos los indices de cada uno de los vertices a probar
            }
        }
        
        while (!vertQueue.isEmpty()) {
            Integer indexMaximo = indexMax(costosMaximos, vertQueue);
            
            // Integer removes the element whose value matches
            vertQueue.remove(indexMaximo);
            
            for (int queuedVert : vertQueue) {
                if (costosMaximos[queuedVert] == Integer.MAX_VALUE) {
                    costosMaximos[queuedVert] = 0;
                }
                if (costosMaximos[indexMaximo] != Integer.MAX_VALUE && costos[indexMaximo][queuedVert] != Integer.MAX_VALUE) {
                    if (costosMaximos[queuedVert] < costosMaximos[indexMaximo] + costos[indexMaximo][queuedVert]) {
                        costosMaximos[queuedVert] = costosMaximos[indexMaximo] + costos[indexMaximo][queuedVert];
                    }
                } 
            }
        }
        
        for (int i = 0; i < size; i++) {
            if (costosMaximos[i] == Integer.MAX_VALUE) costosMaximos[i] = null;
        }
        
        return costosMaximos;
    }
    
    /**
     * Regresa una matriz de costos minimos y una matriz de vertices intermedios.usados en conjunto para reconstruir los caminos si es necesario.
     * @return Lista en forma <code>{costosMaximos, intermedios}</code>
     */
    public ArrayList floydWarshall() {
        Integer[][] costos = getCostos();
        Integer[][] costosMinimos = costos.clone();
        Integer[][] intermedios = new Integer[size][size]; //Para reconstruir caminos
        
        // Inicializamos matriz intermedia.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (costos[i][j] == Integer.MAX_VALUE || costos[i][j] == 0) {
                    intermedios[i][j] = null;
                } else {
                    intermedios[i][j] = i;
                }
            }
        }
        
        // Encontramos los caminos minimos de todos los vertices hacia todos los vertices.
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (costosMinimos[i][k] != Integer.MAX_VALUE 
                        && costosMinimos[k][j] != Integer.MAX_VALUE 
                        && costosMinimos[i][k] != null 
                        && costosMinimos[k][j] != null) {
                        Integer camino = costosMinimos[i][k] + costosMinimos[k][j];
                        if (costosMinimos[i][j] > camino) {
                            costosMinimos[i][j] = camino;
                            intermedios[i][j] = k;
                        }
                    }
                }
            }
        }
        
        ArrayList retorno = new ArrayList();
        
        retorno.add(costosMinimos);
        retorno.add(intermedios);
        
        return retorno;
    }
    
    /**
     * Regresa el camino de el vertice <code>origen</code> al vertice <code>destino</code>
     * @param intermedios
     * @param origen
     * @param destino
     * @return Lista de <code>T</code>
     */
    public ArrayList getCamino(Integer[][] intermedios, T origen, T destino) {
        Integer indexOrigen = vertices.indexOf(origen);
        Integer indexDestino = vertices.indexOf(destino);
        return getCamino(intermedios, indexOrigen, indexDestino);
    }
    
    private ArrayList getCamino(Integer[][] intermedios, Integer indexOrigen, Integer indexDestino) {
        ArrayList<T> retorno = new ArrayList<>();
        
        if (indexOrigen.equals(indexDestino)) {
            retorno.add(vertices.get(indexOrigen));
        } else {
            if (intermedios[indexOrigen][indexDestino] == null) {
                return new ArrayList();
            } else {
                retorno.addAll(getCamino(intermedios, indexOrigen, intermedios[indexOrigen][indexDestino]));
                retorno.add(vertices.get(indexDestino));
            }
        }
        
        return retorno;
    }
}
