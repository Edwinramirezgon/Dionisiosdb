package Cola;

public class Cola<Object> {
    
    private ListaSimple<Object> elementos;
    
    public Cola(){

        this.elementos = new ListaSimple<Object>();
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

    public void setElementos(ListaSimple<Object> elementos) {
        this.elementos = elementos;
    }

}
