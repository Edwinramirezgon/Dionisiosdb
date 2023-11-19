package Backend;

public class Cola<Object> {
    
    private ListaSimpleCola<Object> elementos;
    
    public Cola(){

        this.elementos = new ListaSimpleCola<Object>();
    }
    
    public void Encolar(Object elemento){
        this.elementos.Anadir(elemento);
    }
    
    public void Desencolar(){
        this.elementos.EliminarInicio();
    }
    
    public boolean EstaVacia(){
        return elementos.EstaVacia();
    }

    public Object getElementos() {
        return elementos.getElemento();
    }

    public void setElementos(ListaSimpleCola<Object> elementos) {
        this.elementos = elementos;
    }

}
