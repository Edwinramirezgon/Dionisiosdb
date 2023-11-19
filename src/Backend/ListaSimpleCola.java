package Backend;

public class ListaSimpleCola<Object> {
    
    private NodoSimpleCola<Object> inicio, actual;
    
    public ListaSimpleCola(){
        Establecer(null);
    }
    
    public ListaSimpleCola(Object elemento){
        Establecer(new NodoSimpleCola(elemento));
    }
    
    private void Establecer(NodoSimpleCola Valor){
        this.inicio = this.actual = Valor;
    }
    
    private void Reiniciar(){
        this.actual = this.inicio;
    }
    
    public void EnlazaA(NodoSimpleCola Nodo, Object Elemento){
        NodoSimpleCola aux = Nodo.getSiguiente();
        Nodo.setSiguiente(new NodoSimpleCola(Elemento));
        Nodo.getSiguiente().setSiguiente(aux);
    }
    
    public boolean EstaVacia(){
        return inicio == null;
    }
    
    public void EliminarInicio(){
        NodoSimpleCola aux = this.inicio.getSiguiente();
        if(this.actual == this.inicio){
            this.actual = this.inicio.getSiguiente();
        }
        this.inicio.setSiguiente(null);
        this.inicio = aux;
    }
    

    public void Anadir(Object elemento){
        if(this.EstaVacia()){
            this.Establecer(new NodoSimpleCola(elemento));
            return;
        }

        this.EnlazaA(this.actual, elemento);
        this.actual = this.actual.getSiguiente();
    }
    
    public Object getElemento(){
        return this.inicio.getElemento();
    }
    
    public void setElemento(Object Elemento){
        this.inicio.setElemento(Elemento);
    }

}
