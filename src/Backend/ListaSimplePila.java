package Backend;

public class ListaSimplePila<Object> {
    

    private NodoSimplePila<Object> inicio, actual;
    
    public ListaSimplePila(){
        establecer(null);
    }
    
    public ListaSimplePila(Object elemento){
        establecer(new NodoSimplePila(elemento));
    }
    
    private void establecer(NodoSimplePila valor){
        this.inicio = this.actual = valor;
    }
    
    public void reiniciar(){
        this.actual = this.inicio;
    }
    
    public void enlazaA(Object elemento){
        NodoSimplePila nodo = new NodoSimplePila(elemento);
        nodo.setSiguiente(inicio);
        establecer(nodo);
    }
    
    public void enlazaA(NodoSimplePila nodo, Object elemento){
        NodoSimplePila aux = nodo.getSiguiente();
        nodo.setSiguiente(new NodoSimplePila(elemento));
        nodo.getSiguiente().setSiguiente(aux);
    }
    
    public void anadir(Object elemento){
        if(this.estaVacia())
            this.establecer(new NodoSimplePila(elemento));
        else
            this.enlazaA(this.actual, elemento);
    }
    
    public void anadir(){
        this.anadir(null);
    }
    
    public boolean siguiente(){
        this.actual = this.actual.getSiguiente();
        return this.actual != null;
    }
    
    public Object getElemento(){
        return this.actual.getElemento();
    }
    
    public void setElemento(Object Elemento){
        this.actual.setElemento(Elemento);
    }
    
    public Object getElementoInicio(){
        return this.inicio.getElemento();
    }
    
    public void setElementoInicio(Object elemento){
        this.inicio.setElemento(elemento);
    }
    
    public boolean estaVacia(){
        return this.inicio == null;
    }
    
    public void insertarAlInicio(Object elemento){
        if(estaVacia()){
            this.establecer(new NodoSimplePila(elemento));
            return; //se sale del condicional y del método
        }
        Object aux = this.inicio.getElemento();
        this.inicio.setElemento(elemento);
        this.enlazaA(this.inicio, aux);
    }
    
    public void insertarAlInicio(){
        this.insertarAlInicio(null);
    }
    
    public void eliminarInicio(){
        NodoSimplePila aux = this.inicio.getSiguiente();
        if(this.actual == this.inicio){
            this.actual = this.inicio.getSiguiente();
        }
        this.inicio.setSiguiente(null);
        this.inicio = aux;
    }
    
}
