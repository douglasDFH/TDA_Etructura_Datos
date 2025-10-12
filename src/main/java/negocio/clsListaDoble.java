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

    // ==================== MÉTODOS ADICIONALES PARA LISTA DOBLE ====================

    /**
     * Insertar a la izquierda de una posición específica (antes del nodo en esa posición)
     * @param pos Posición de referencia (0-indexada)
     * @param dato Valor a insertar
     * @return true si se insertó correctamente, false si la posición no existe
     */
    public boolean insertarIzquierda(int pos, int dato) {
        if (pos < 0) return false;
        if (pos == 0 || head == null) {
            insertarInicio(dato);
            return true;
        }
        
        clsNodoDoble aux = head;
        int i = 0;
        
        // Navegar hasta la posición
        while (aux != null && i < pos) {
            aux = aux.getNext();
            i++;
        }
        
        if (aux == null) return false; // Posición fuera de rango
        
        // Insertar antes del nodo actual
        clsNodoDoble nuevo = new clsNodoDoble(dato);
        clsNodoDoble anterior = aux.getPrev();
        
        nuevo.setNext(aux);
        nuevo.setPrev(anterior);
        aux.setPrev(nuevo);
        
        if (anterior != null) {
            anterior.setNext(nuevo);
        } else {
            head = nuevo; // El nuevo nodo es la nueva cabeza
        }
        
        return true;
    }

    /**
     * Insertar a la derecha de una posición específica (después del nodo en esa posición)
     * @param pos Posición de referencia (0-indexada)
     * @param dato Valor a insertar
     * @return true si se insertó correctamente, false si la posición no existe
     */
    public boolean insertarDerecha(int pos, int dato) {
        if (pos < 0 || head == null) return false;
        
        clsNodoDoble aux = head;
        int i = 0;
        
        // Navegar hasta la posición
        while (aux != null && i < pos) {
            aux = aux.getNext();
            i++;
        }
        
        if (aux == null) return false; // Posición fuera de rango
        
        // Si es el último nodo, usar insertarFinal
        if (aux == tail) {
            insertarFinal(dato);
            return true;
        }
        
        // Insertar después del nodo actual
        clsNodoDoble nuevo = new clsNodoDoble(dato);
        clsNodoDoble siguiente = aux.getNext();
        
        nuevo.setPrev(aux);
        nuevo.setNext(siguiente);
        aux.setNext(nuevo);
        
        if (siguiente != null) {
            siguiente.setPrev(nuevo);
        }
        
        return true;
    }

    /**
     * Eliminar el nodo a la izquierda de una posición específica (anterior al nodo en esa posición)
     * @param pos Posición de referencia (0-indexada)
     * @return Valor del nodo eliminado, -1 si no se pudo eliminar
     */
    public int eliminarIzquierda(int pos) {
        if (pos <= 0 || head == null) return -1; // No hay nodo a la izquierda de posición 0
        
        clsNodoDoble aux = head;
        int i = 0;
        
        // Navegar hasta la posición
        while (aux != null && i < pos) {
            aux = aux.getNext();
            i++;
        }
        
        if (aux == null || aux.getPrev() == null) return -1; // No hay nodo anterior
        
        // Eliminar el nodo anterior
        clsNodoDoble aEliminar = aux.getPrev();
        int valor = aEliminar.getDato();
        
        clsNodoDoble anterior = aEliminar.getPrev();
        
        aux.setPrev(anterior);
        if (anterior != null) {
            anterior.setNext(aux);
        } else {
            head = aux; // aux se convierte en la nueva cabeza
        }
        
        return valor;
    }

    /**
     * Eliminar el nodo a la derecha de una posición específica (posterior al nodo en esa posición)
     * @param pos Posición de referencia (0-indexada)
     * @return Valor del nodo eliminado, -1 si no se pudo eliminar
     */
    public int eliminarDerecha(int pos) {
        if (pos < 0 || head == null) return -1;
        
        clsNodoDoble aux = head;
        int i = 0;
        
        // Navegar hasta la posición
        while (aux != null && i < pos) {
            aux = aux.getNext();
            i++;
        }
        
        if (aux == null || aux.getNext() == null) return -1; // No hay nodo siguiente
        
        // Eliminar el nodo siguiente
        clsNodoDoble aEliminar = aux.getNext();
        int valor = aEliminar.getDato();
        
        clsNodoDoble siguiente = aEliminar.getNext();
        
        aux.setNext(siguiente);
        if (siguiente != null) {
            siguiente.setPrev(aux);
        } else {
            tail = aux; // aux se convierte en la nueva cola
        }
        
        return valor;
    }

    /**
     * Insertar manteniendo orden ascendente
     * @param dato Valor a insertar ordenadamente
     */
    public void insertarOrdenado(int dato) {
        clsNodoDoble nuevo = new clsNodoDoble(dato);
        
        // Lista vacía
        if (head == null) {
            head = tail = nuevo;
            return;
        }
        
        // Insertar al inicio si es menor que head
        if (dato <= head.getDato()) {
            insertarInicio(dato);
            return;
        }
        
        // Insertar al final si es mayor que tail
        if (dato >= tail.getDato()) {
            insertarFinal(dato);
            return;
        }
        
        // Buscar posición correcta
        clsNodoDoble aux = head;
        while (aux.getNext() != null && aux.getNext().getDato() < dato) {
            aux = aux.getNext();
        }
        
        // Insertar después de aux
        clsNodoDoble siguiente = aux.getNext();
        aux.setNext(nuevo);
        nuevo.setPrev(aux);
        nuevo.setNext(siguiente);
        if (siguiente != null) {
            siguiente.setPrev(nuevo);
        }
    }

    /**
     * Sumar todos los elementos de la lista
     * @return Suma total de los elementos
     */
    public int sumarElementos() {
        int suma = 0;
        clsNodoDoble aux = head;
        
        while (aux != null) {
            suma += aux.getDato();
            aux = aux.getNext();
        }
        
        return suma;
    }

    /**
     * Ordenar la lista en orden ascendente usando algoritmo burbuja
     */
    public void ordenarAscendente() {
        if (head == null || head.getNext() == null) return;
        
        boolean intercambio;
        do {
            intercambio = false;
            clsNodoDoble actual = head;
            
            while (actual.getNext() != null) {
                if (actual.getDato() > actual.getNext().getDato()) {
                    // Intercambiar valores
                    int temp = actual.getDato();
                    actual.setDato(actual.getNext().getDato());
                    actual.getNext().setDato(temp);
                    intercambio = true;
                }
                actual = actual.getNext();
            }
        } while (intercambio);
    }

    /**
     * Verificar si la lista está vacía
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return head == null;
    }
}
