package Cola;

public class ListaSimple<Object> {
    
    private NodoSimple<Object> inicio, actual;
    
    public ListaSimple(){
        Establecer(null);
    }
    
    public ListaSimple(Object elemento){
        Establecer(new NodoSimple(elemento));
    }
    
    private void Establecer(NodoSimple Valor){
        this.inicio = this.actual = Valor;
    }
    
    private void Reiniciar(){
        this.actual = this.inicio;
    }
    
    public void EnlazaA(NodoSimple Nodo, Object Elemento){
        NodoSimple aux = Nodo.getSiguiente();
        Nodo.setSiguiente(new NodoSimple(Elemento));
        Nodo.getSiguiente().setSiguiente(aux);
    }
    
    public boolean EstaVacia(){
        return inicio == null;
    }
    
    public void EliminarInicio(){
        NodoSimple aux = this.inicio.getSiguiente();
        if(this.actual == this.inicio){
            this.actual = this.inicio.getSiguiente();
        }
        this.inicio.setSiguiente(null);
        this.inicio = aux;
    }
    

    public void Anadir(Object elemento){
        if(this.EstaVacia()){
            this.Establecer(new NodoSimple(elemento));
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
