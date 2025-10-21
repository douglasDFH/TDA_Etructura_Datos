package negocio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Lista Circular Doble especializada para Ruleta de la Suerte
 * Basada en clsListaCircular pero adaptada para trabajar con clsNodoDoble (nombre + color)
 * Mantiene los mismos conceptos educativos: RefI (Referencia Izquierda) y RefD (Referencia Derecha)
 */
public class clsListaCircular {
    private clsNodoDoble pLC; // Puntero Lista Circular

    public clsListaCircular() {
        this.pLC = null;
    }

    // Insertar a la izquierda del puntero actual
    // Si la lista está vacía, crea el primer nodo que apunta a sí mismo
    public void insertarIzquierda(String nombre, Color color) {
        clsNodoDoble nAux = new clsNodoDoble(nombre, color);

        if (this.pLC == null) {
            // Lista vacía: crear primer nodo circular
            nAux.setRefD(nAux);  // Apunta a sí mismo (derecha)
            nAux.setRefI(nAux);  // Apunta a sí mismo (izquierda)
            this.pLC = nAux;
        } else {
            // Lista con elementos: insertar a la izquierda del puntero
            nAux.setRefI((clsNodoDoble)this.pLC.getRefI());           // el nodo auxiliar (nAux) establece su referencia izquierda (RefI) hacia el nodo que esta a la izquierda del puntero actual (pLC)
            ((clsNodoDoble)this.pLC.getRefI()).setRefD(nAux);         // El nodo que estaba a la izquierda del puntero (pLC) ahora establece su referencia derecha (RefD) hacia el nuevo nodo (nAux)
            this.pLC.setRefI(nAux);                                    // El puntero (pLC) ahora establece su referencia izquierda (RefI) al nuevo nodo (nAux)
            nAux.setRefD(this.pLC);                                    // El nuevo nodo (nAux) establece su referencia derecha (RefD) al puntero (pLC)
        }
    }

    // Insertar a la derecha del puntero actual
    // Si la lista está vacía, crea el primer nodo que apunta a sí mismo
    public void insertarDerecha(String nombre, Color color) {
        clsNodoDoble nAux = new clsNodoDoble(nombre, color);

        if (this.pLC == null) {
            // Lista vacía: crear primer nodo circular
            nAux.setRefD(nAux);  // El nuevo nodo (nAux) establece su referencia derecha (RefD) a sí mismo
            nAux.setRefI(nAux);  // El nuevo nodo (nAux) establece su referencia izquierda (RefI) a sí mismo
            this.pLC = nAux;     // El puntero principal (pLC) apunta al nuevo nodo
        } else {
            // Lista con elementos: insertar a la derecha del puntero
            nAux.setRefD((clsNodoDoble)this.pLC.getRefD());           // El nodo auxiliar (nAux) establece su referencia derecha (RefD) hacia el nodo que está a la derecha del puntero actual (pLC)
            ((clsNodoDoble)this.pLC.getRefD()).setRefI(nAux);         // El nodo que estaba a la derecha del puntero (pLC) ahora establece su referencia izquierda (RefI) al nuevo nodo (nAux)
            this.pLC.setRefD(nAux);                                    // El puntero (pLC) ahora establece su referencia derecha (RefD) al nuevo nodo (nAux)
            nAux.setRefI(this.pLC);                                    // El nuevo nodo (nAux) establece su referencia izquierda (RefI) al puntero (pLC)
        }
    }

   

    // RULETA: Mover el puntero una posición usando su referencia derecha (RefD) - sentido horario
    // Simula el giro de una ruleta en dirección del reloj
    public void girarDerecha() {
        if (this.pLC != null) {
            this.pLC = (clsNodoDoble)this.pLC.getRefD();  // El puntero (pLC) se mueve al siguiente nodo usando su referencia derecha (RefD)
        }
    }

    // RULETA: Mover el puntero una posición usando su referencia izquierda (RefI) - sentido antihorario
    // Simula el giro de una ruleta en dirección contraria del reloj
    public void girarIzquierda() {
        if (this.pLC != null) {
            this.pLC = (clsNodoDoble)this.pLC.getRefI();  // El puntero (pLC) se mueve al siguiente nodo usando su referencia izquierda (RefI)
        }
    }

    // RULETA: Mover el puntero múltiples posiciones usando referencia derecha (RefD)
    // Permite girar varias posiciones de una vez usando la referencia derecha
    public void girarDerecha(int posiciones) {
        for (int i = 0; i < posiciones && this.pLC != null; i++) {
            girarDerecha();  // Llama al método simple de giro usando referencia derecha (RefD) 'posiciones' veces
        }
    }

    // RULETA: Mover el puntero múltiples posiciones usando referencia izquierda (RefI)
    // Permite girar varias posiciones de una vez usando la referencia izquierda
    public void girarIzquierda(int posiciones) {
        for (int i = 0; i < posiciones && this.pLC != null; i++) {
            girarIzquierda();  // Llama al método simple de giro usando referencia izquierda (RefI) 'posiciones' veces
        }
    }

    // RULETA: Obtener el nodo donde está posicionada actualmente la ruleta
    // Retorna el nodo completo (con nombre y color) al cual apunta pLC, o null si la lista está vacía
    public clsNodoDoble obtenerNodoActual() {
        return this.pLC;  // Si pLC no es null, retorna el nodo actual
    }

    // RULETA: Consultar el nodo usando referencia derecha (RefD) sin mover la posición actual
    // Permite "espiar" el próximo nodo por referencia derecha sin cambiar pLC
    public clsNodoDoble verNodoDerecha() {
        return (this.pLC != null && this.pLC.getRefD() != null) ?
               (clsNodoDoble)this.pLC.getRefD() : null;  // Si existe, retorna el nodo que está en la referencia derecha (RefD) de pLC
    }

    // RULETA: Consultar el nodo usando referencia izquierda (RefI) sin mover la posición actual
    // Permite "espiar" el próximo nodo por referencia izquierda sin cambiar pLC
    public clsNodoDoble verNodoIzquierda() {
        return (this.pLC != null && this.pLC.getRefI() != null) ?
               (clsNodoDoble)this.pLC.getRefI() : null;  // Si existe, retorna el nodo que está en la referencia izquierda (RefI) de pLC
    }

    // ==================== MÉTODOS DE NAVEGACIÓN AVANZADA ====================

    // RULETA: Buscar un nombre específico y posicionar la ruleta en él
    // Recorre toda la lista circular hasta encontrar el nombre o volver al punto inicial
    public boolean buscarYPosicionar(String nombre) {
        if (this.pLC == null) return false;  // Lista vacía: no se puede buscar

        clsNodoDoble inicial = this.pLC;     // Guarda la posición inicial para detectar ciclo completo
        do {
            if (this.pLC.getNombre().equalsIgnoreCase(nombre)) {
                return true; // Nombre encontrado: pLC ya está posicionado en él
            }
            this.pLC = (clsNodoDoble)this.pLC.getRefD();   // Avanza al siguiente nodo usando la referencia derecha (RefD)
        } while (this.pLC != inicial);       // Continúa hasta volver al nodo inicial

        return false; // Nombre no encontrado después de recorrer toda la lista circular
    }

    // RULETA: Simular un giro aleatorio de ruleta real
    // Genera un número aleatorio de giros y una dirección aleatoria para simular realismo
    public clsNodoDoble girarAleatoriamente() {
        if (this.pLC == null) return null;  // Lista vacía: no se puede girar

        // Generar número aleatorio de giros (1-20)
        int giros = (int)(Math.random() * 20) + 1;  // Calcula un número aleatorio entre 1 y 20

        // Alternar dirección aleatoriamente
        boolean direccionDerecha = Math.random() > 0.5;  // 50% probabilidad de ir a la derecha

        if (direccionDerecha) {
            girarDerecha(giros);    // Gira usando referencia derecha (RefD) el número calculado de posiciones
        } else {
            girarIzquierda(giros);  // Gira usando referencia izquierda (RefI) el número calculado de posiciones
        }

        return obtenerNodoActual();  // Retorna el nodo final donde se detuvo la ruleta
    }

    // ==================== MÉTODOS DE ELIMINACIÓN ====================

    // Eliminar el nodo donde está posicionada actualmente la ruleta (pLC)
    // Reconecta los nodos vecinos y mueve pLC al siguiente nodo
    public clsNodoDoble eliminarActual() {
        if (this.pLC == null) return null;  // Lista vacía: no hay nada que eliminar

        clsNodoDoble eliminado = this.pLC;   // Guarda el nodo que se va a eliminar

        if (this.pLC.getRefD() == this.pLC) {
            // Solo hay un elemento: la lista queda vacía
            this.pLC = null;              // El puntero queda en null (lista vacía)
        } else {
            // Hay más elementos: reconectar los nodos vecinos
            clsNodoDoble anterior = (clsNodoDoble)this.pLC.getRefI();  // Nodo que está a la izquierda del actual
            clsNodoDoble siguiente = (clsNodoDoble)this.pLC.getRefD(); // Nodo que está a la derecha del actual

            anterior.setRefD(siguiente);  // El nodo anterior ahora apunta al siguiente
            siguiente.setRefI(anterior);  // El nodo siguiente ahora apunta al anterior

            // Mover puntero al siguiente elemento para mantener posición válida
            this.pLC = siguiente;         // pLC se mueve al nodo que estaba a la derecha
        }

        return eliminado;  // Retorna el nodo eliminado
    }

    // Buscar un nombre específico y eliminarlo de la lista circular
    // Combina la búsqueda con la eliminación en una sola operación
    public boolean eliminarPorNombre(String nombre) {
        if (buscarYPosicionar(nombre)) {  // Busca el nombre y posiciona pLC en él
            eliminarActual();             // Elimina el nodo donde está posicionado pLC
            return true;                  // Nombre encontrado y eliminado exitosamente
        }
        return false;                     // Nombre no encontrado en la lista
    }

    // ==================== MÉTODOS DE INFORMACIÓN ====================

    // Verificar si la lista circular está vacía
    // Método simple para determinar si hay elementos en la lista
    public boolean estaVacia() {
        return this.pLC == null;  // Si pLC es null, la lista está vacía
    }

    // Contar el número total de elementos en la lista circular
    // Recorre toda la lista una vez para contar todos los nodos
    public int size() {
        if (this.pLC == null) return 0;  // Lista vacía: cero elementos

        int count = 1;                   // Empieza contando el nodo actual (pLC)
        clsNodoDoble actual = (clsNodoDoble)this.pLC.getRefD();  // Comienza desde el siguiente nodo usando referencia derecha (RefD)

        while (actual != this.pLC) {     // Recorre hasta volver al nodo inicial
            count++;                     // Incrementa el contador por cada nodo visitado
            actual = (clsNodoDoble)actual.getRefD();   // Avanza al siguiente nodo usando la referencia derecha (RefD)
        }

        return count;  // Retorna el número total de elementos encontrados
    }

    // RULETA: Obtener todos los nodos como lista (para dibujar la ruleta completa)
    // Crea una lista con todos los nodos empezando desde la posición actual
    public List<clsNodoDoble> obtenerTodosLosNodos() {
        List<clsNodoDoble> nodos = new ArrayList<>();

        if (this.pLC == null) return nodos;  // Lista vacía: retorna lista vacía

        clsNodoDoble actual = this.pLC;  // Comienza desde el nodo actual
        do {
            nodos.add(actual);                 // Agrega el nodo actual a la lista
            actual = (clsNodoDoble)actual.getRefD();  // Avanza al siguiente nodo usando referencia derecha (RefD)
        } while (actual != this.pLC);          // Continúa hasta volver al nodo inicial

        return nodos;  // Retorna la lista completa de nodos
    }

    // RULETA: Mostrar todos los nombres en orden circular desde la posición actual
    // Crea una representación visual de la lista circular completa
    public String mostrarRuleta() {
        if (this.pLC == null) return "Ruleta vacía";  // Caso especial: lista vacía

        StringBuilder sb = new StringBuilder();      // Constructor de cadena para eficiencia
        clsNodoDoble inicial = this.pLC;             // Marca el punto de inicio para el recorrido

        sb.append("[ACTUAL→").append(this.pLC.getNombre()).append("]");  // Resalta la posición actual

        clsNodoDoble actual = (clsNodoDoble)this.pLC.getRefD();    // Comienza desde el siguiente nodo usando referencia derecha (RefD)
        while (actual != inicial) {                  // Recorre hasta volver al nodo inicial
            sb.append(" → ").append(actual.getNombre());  // Agrega cada nombre con flecha
            actual = (clsNodoDoble)actual.getRefD();               // Avanza al siguiente nodo usando referencia derecha (RefD)
        }

        sb.append(" → [VUELVE AL INICIO]");  // Indica que la lista es circular
        return sb.toString();  // Retorna la representación completa como cadena
    }

    // RULETA: Mostrar contexto inmediato usando referencias (RefI, actual, RefD)
    // Proporciona una vista local de la posición actual con sus vecinos por referencias
    public String mostrarContexto() {
        if (this.pLC == null) return "Ruleta vacía";  // Caso especial: lista vacía

        clsNodoDoble izq = verNodoIzquierda();
        clsNodoDoble der = verNodoDerecha();

        return String.format("← %s | [%s] | %s →",    // Formato visual con flechas
                           izq != null ? izq.getNombre() : "null",       // Nombre del nodo en referencia izquierda (RefI)
                           this.pLC.getNombre(),                         // Nombre del nodo actual (pLC)
                           der != null ? der.getNombre() : "null");      // Nombre del nodo en referencia derecha (RefD)
    }

    // ==================== MÉTODOS ESPECÍFICOS PARA RULETA DE JUEGO ====================

    // RULETA: Crear una ruleta con nombres y colores personalizados
    // Permite inicializar la ruleta con un conjunto de nombres y colores alternados
    public void inicializarRuletaPersonalizada(String[] nombres, Color[] colores) {
        // Limpiar lista para empezar desde cero
        this.pLC = null;                // Resetea la lista a estado vacío

        // Insertar cada nombre con su color correspondiente
        for (int i = 0; i < nombres.length; i++) {
            Color colorActual = colores[i % colores.length];  // Alternar colores si hay menos colores que nombres
            insertarDerecha(nombres[i], colorActual);         // Agrega cada elemento secuencialmente
        }
    }

    // RULETA: Simular giro con desaceleración gradual (efecto realista)
    // Imita el comportamiento físico de una ruleta real que va perdiendo velocidad
    public clsNodoDoble simularGiroGradual(int velocidadInicial) {
        if (this.pLC == null) return null;  // Lista vacía: no se puede simular

        int velocidad = velocidadInicial; // Velocidad actual (empieza alta)
        int totalGiros = 0;              // Contador total de giros realizados

        // Simular desaceleración gradual como una ruleta física
        while (velocidad > 0) {          // Continúa mientras haya velocidad
            girarDerecha(velocidad);     // Gira tantas posiciones como la velocidad actual
            totalGiros += velocidad;     // Acumula el total de giros
            velocidad = Math.max(0, velocidad - 1);  // Reduce la velocidad gradualmente

            // Pequeña pausa para simular fricción y efecto visual
            try {
                Thread.sleep(50);        // Pausa de 50ms entre cada paso de velocidad
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restaura el estado de interrupción del hilo
            }
        }

        System.out.println("Ruleta giró " + totalGiros + " posiciones");  // Muestra estadísticas del giro
        return obtenerNodoActual();  // Retorna el nodo final donde se detuvo la ruleta
    }

    // ==================== GETTERS Y SETTERS PARA ACCESO EXTERNO ====================

    // Obtener el puntero principal de la lista circular
    // Permite acceso directo al nodo actual para operaciones avanzadas
    public clsNodoDoble getpLC() {
        return this.pLC;  // Retorna la referencia al nodo donde está posicionada la ruleta
    }

    // Establecer el puntero principal de la lista circular
    // Permite cambiar la posición actual de la ruleta directamente
    public void setpLC(clsNodoDoble pLC) {
        this.pLC = pLC;  // Cambia la posición actual de la ruleta al nodo especificado
    }

    // Vaciar completamente la lista circular
    // Elimina todos los elementos y resetea la ruleta a estado inicial
    public void vaciar() {
        this.pLC = null;  // Establece el puntero a null, efectivamente vaciando la lista
    }
}
