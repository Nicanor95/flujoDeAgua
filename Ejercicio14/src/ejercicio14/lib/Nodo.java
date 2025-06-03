package ejercicio14.lib;

import java.util.Objects;

public class Nodo <T> {
    T data;
    int etiqueta;
    Nodo next;

    public Nodo() {
    }

    public Nodo(T data) {
        this.data = data;
        this.etiqueta = 0;
    }
    
    public Nodo(T data, int etiqueta) {
        this.data = data;
        this.etiqueta = etiqueta;
    }

    public Nodo(T data, Nodo next) {
        this.data = data;
        this.etiqueta = 0;
        this.next = next;
    }
    
    public Nodo(T data, Nodo next, int etiqueta) {
        this.data = data;
        this.etiqueta = etiqueta;
        this.next = next;
    }
    
    public boolean hasNext() {
        return next != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo<?> other = (Nodo<?>) obj;
        return Objects.equals(this.data, other.data);
    }
}
