package Backend;

import Backend.ListaSimplePila;

public class Pila<Object> {
    
    private ListaSimplePila<Object> elementos;
        
    public Pila(){
        this.elementos = new ListaSimplePila();
    }
    
    public void apilar(Object elemento){
        this.elementos.insertarAlInicio(elemento);
    }
    
    public void desapilar(){
        this.elementos.eliminarInicio();
    }
    
    public boolean estaVacia(){
        return this.elementos.estaVacia();
    }
    
    public Object getElemento(){
        return this.elementos.getElemento();
    }
    
    public void setElemento(Object elemento){
        this.elementos.setElemento(elemento);
    }
}
