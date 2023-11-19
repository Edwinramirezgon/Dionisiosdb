package Backend;

public class NodoSimpleCola<Object> {
    
    private Object elemento;
    private NodoSimpleCola siguiente;
    
    public NodoSimpleCola(){
        
    }

    public NodoSimpleCola(Object elemento) {
        this.elemento = elemento;
        this.siguiente = null;
    }
    
    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoSimpleCola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimpleCola siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoSimple{" + "elemento=" + elemento + ", siguiente=" + siguiente + '}';
    }
}
