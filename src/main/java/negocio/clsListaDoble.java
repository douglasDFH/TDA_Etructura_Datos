package negocio;

public class clsListaDoble {
    private clsNodoDoble head;
    private clsNodoDoble tail;

    public clsListaDoble(){
        this.head = null;
        this.tail = null;
    }

    public void insertarInicio(int dato){
        clsNodoDoble n = new clsNodoDoble(dato);
        if(head == null){
            head = tail = n;
            return;
        }
        n.setNext(head);
        head.setPrev(n);
        head = n;
    }

    public void insertarFinal(int dato){
        clsNodoDoble n = new clsNodoDoble(dato);
        if(tail == null){
            head = tail = n;
            return;
        }
        tail.setNext(n);
        n.setPrev(tail);
        tail = n;
    }

    public boolean insertarEnPos(int pos, int dato){
        if(pos < 0) return false;
        if(pos == 0){
            insertarInicio(dato);
            return true;
        }
        clsNodoDoble aux = head;
        int i = 0;
        while(aux != null && i < pos - 1){
            aux = aux.getNext();
            i++;
        }
        if(aux == null) return false;
        if(aux == tail){
            insertarFinal(dato);
            return true;
        }
        clsNodoDoble n = new clsNodoDoble(dato);
        clsNodoDoble siguiente = aux.getNext();
        aux.setNext(n);
        n.setPrev(aux);
        n.setNext(siguiente);
        if(siguiente != null) siguiente.setPrev(n);
        return true;
    }

    public int eliminarInicio(){
        if(head == null) return -1;
        int val = head.getDato();
        head = head.getNext();
        if(head == null) tail = null;
        else head.setPrev(null);
        return val;
    }

    public int eliminarFinal(){
        if(tail == null) return -1;
        int val = tail.getDato();
        tail = tail.getPrev();
        if(tail == null) head = null;
        else tail.setNext(null);
        return val;
    }

    public boolean eliminarPorValor(int valor){
        clsNodoDoble aux = head;
        while(aux != null){
            if(aux.getDato() == valor){
                clsNodoDoble p = aux.getPrev();
                clsNodoDoble n = aux.getNext();
                if(p != null) p.setNext(n);
                else head = n;
                if(n != null) n.setPrev(p);
                else tail = p;
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public boolean buscar(int valor){
        clsNodoDoble aux = head;
        while(aux != null){
            if(aux.getDato() == valor) return true;
            aux = aux.getNext();
        }
        return false;
    }

    /**
     * Busca un valor y retorna todas las posiciones donde se encuentra
     * @param valor Valor a buscar
     * @return Array con todas las posiciones (0-indexadas) donde se encuentra el valor
     */
    public int[] buscarTodasLasPosiciones(int valor) {
        java.util.ArrayList<Integer> posiciones = new java.util.ArrayList<>();
        clsNodoDoble aux = head;
        int posicion = 0;
        
        while(aux != null) {
            if(aux.getDato() == valor) {
                posiciones.add(posicion);
            }
            aux = aux.getNext();
            posicion++;
        }
        
        // Convertir ArrayList a array int[]
        int[] resultado = new int[posiciones.size()];
        for(int i = 0; i < posiciones.size(); i++) {
            resultado[i] = posiciones.get(i);
        }
        return resultado;
    }

    public int size(){
        int cnt = 0;
        clsNodoDoble aux = head;
        while(aux != null){
            cnt++;
            aux = aux.getNext();
        }
        return cnt;
    }

    public void vaciar(){
        head = null;
        tail = null;
    }

    public String recorridoForward(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        clsNodoDoble aux = head;
        while(aux != null){
            sb.append(aux.getDato());
            if(aux.getNext() != null) sb.append(",");
            aux = aux.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public String recorridoBackward(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        clsNodoDoble aux = tail;
        while(aux != null){
            sb.append(aux.getDato());
            if(aux.getPrev() != null) sb.append(",");
            aux = aux.getPrev();
        }
        sb.append("]");
        return sb.toString();
    }

    // Getters para dibujo desde GUI
    public clsNodoDoble getHead(){
        return this.head;
    }

    public clsNodoDoble getTail(){
        return this.tail;
    }
}
