/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author dell
 */
public class clsArbolBinario {
    private nodoAB raiz;
    public clsArbolBinario(){
        this.raiz = null;
    }
    public void insertarAB(int dato){
       nodoAB nA = new nodoAB(dato);
       insertarAB(raiz, nA);
       
    }
    private void insertarAB(nodoAB raizA,nodoAB nAux){
        if(raiz == null){
            raiz = nAux;
        }else{
            if((int)(nAux.getDato())<= (int) (raizA.getDato())){
                if(raizA.getHijoI()== null){
                    raizA.setHijoI(nAux);
                }else{
                    insertarAB(raizA.getHijoI(),nAux);
                }
            }else{
                if(raizA.getHijoD()== null){
                    raizA.setHijoD(nAux);
                }else{
                    insertarAB(raizA.getHijoD(),nAux);
                }
            }
        }
    }

    //metodo publico para eliminar un nodo del arbol binario
    public boolean eliminarAB(int dato){
        if(raiz == null){
            return false; //arbol vacio
        }
        raiz = eliminarAB(raiz, dato);
        return true;
    }

    //metodo privado recursivo para eliminar
    private nodoAB eliminarAB(nodoAB raizA, int dato){
        if(raizA == null){
            return null; //no se encontro el dato
        }

        //buscar el nodo a eliminar
        if(dato < (int)raizA.getDato()){
            raizA.setHijoI(eliminarAB(raizA.getHijoI(), dato));
        }else if(dato > (int)raizA.getDato()){
            raizA.setHijoD(eliminarAB(raizA.getHijoD(), dato));
        }else{
            //caso 1: nodo hoja (sin hijos)
            if(raizA.getHijoI() == null && raizA.getHijoD() == null){
                return null;
            }
            //caso 2: nodo con un solo hijo
            else if(raizA.getHijoI() == null){
                return raizA.getHijoD(); //retorna el hijo derecho
            }else if(raizA.getHijoD() == null){
                return raizA.getHijoI(); //retorna el hijo izquierdo
            }
            //caso 3: nodo con dos hijos
            else{
                //encontrar el sucesor in-orden (menor del subarbol derecho)
                nodoAB sucesor = obtenerMinimo(raizA.getHijoD());
                raizA.setDato(sucesor.getDato());
                raizA.setHijoD(eliminarAB(raizA.getHijoD(), (int)sucesor.getDato()));
            }
        }
        return raizA;
    }

    //metodo auxiliar para obtener el nodo minimo de un subarbol
    private nodoAB obtenerMinimo(nodoAB nodo){
        while(nodo.getHijoI() != null){
            nodo = nodo.getHijoI();
        }
        return nodo;
    }
    
    //recorrido en orden (izquierda-raiz-derecha)
    //resultado: datos ordenados de menor a mayor
    public void EnOrden (){
        EnOrden(raiz);
    }

    private void EnOrden (nodoAB rAux){
        if(rAux != null){
            if(rAux.getHijoI()!= null){
                EnOrden(rAux.getHijoI());
            }
            System.out.println("Dato: "+ rAux.getDato());
            if(rAux.getHijoD()!= null){
              EnOrden(rAux.getHijoD());
            }
        }
    }

    //recorrido pre orden (raiz-izquierda-derecha)
    //resultado: util para copiar el arbol
    public void PreOrden(){
        PreOrden(raiz);
    }

    private void PreOrden(nodoAB rAux){
        if(rAux != null){
            System.out.println("Dato: "+ rAux.getDato());
            if(rAux.getHijoI()!= null){
                PreOrden(rAux.getHijoI());
            }
            if(rAux.getHijoD()!= null){
                PreOrden(rAux.getHijoD());
            }
        }
    }

    //recorrido post orden (izquierda-derecha-raiz)
    //resultado: util para eliminar el arbol
    public void PostOrden(){
        PostOrden(raiz);
    }

    private void PostOrden(nodoAB rAux){
        if(rAux != null){
            if(rAux.getHijoI()!= null){
                PostOrden(rAux.getHijoI());
            }
            if(rAux.getHijoD()!= null){
                PostOrden(rAux.getHijoD());
            }
            System.out.println("Dato: "+ rAux.getDato());
        }
    }
}

