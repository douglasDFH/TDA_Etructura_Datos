package negocio;

public class clsNodoDoble {
    private int dato;
    private clsNodoDoble prev;
    private clsNodoDoble next;

    public clsNodoDoble(){
        this.dato = 0;
        this.prev = null;
        this.next = null;
    }

    public clsNodoDoble(int dato){
        this.dato = dato;
        this.prev = null;
        this.next = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    // Métodos con nombres educativos más claros
    public clsNodoDoble getRefI() { // Referencia Izquierda
        return prev;
    }

    public void setRefI(clsNodoDoble refI) {
        this.prev = refI;
    }

    public clsNodoDoble getRefD() { // Referencia Derecha
        return next;
    }

    public void setRefD(clsNodoDoble refD) {
        this.next = refD;
    }
    
    // Mantener métodos antiguos para compatibilidad
    public clsNodoDoble getPrev() {
        return prev;
    }

    public void setPrev(clsNodoDoble prev) {
        this.prev = prev;
    }

    public clsNodoDoble getNext() {
        return next;
    }

    public void setNext(clsNodoDoble next) {
        this.next = next;
    }
}
