package Backend;

public class NodoSimplePila<Object> {
    

    private Object elemento;
    private NodoSimplePila siguiente;
    

    public NodoSimplePila(Object elemento){
        this.elemento = elemento;
        this.siguiente = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoSimplePila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimplePila siguiente) {
        this.siguiente = siguiente;
    }
    
}
