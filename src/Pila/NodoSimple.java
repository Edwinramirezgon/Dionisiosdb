package Pila;

public class NodoSimple<Object> {
    

    private Object elemento;
    private NodoSimple siguiente;
    

    public NodoSimple(Object elemento){
        this.elemento = elemento;
        this.siguiente = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
    
}
