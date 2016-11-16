/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author Maxi
 */
public class ABB <T extends Comparable<T>> implements IABB <T>{
    private nodoABB raiz;
    public ABB(){
        this.raiz = null;
    }
    public ABB(nodoABB raiz){
        this.raiz = raiz;
    }
    /**
     * @return the raiz
     */
    public nodoABB getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(nodoABB raiz) {
        this.raiz = raiz;
    }
    
    @Override
    public boolean isEmpty(){
        return (this.raiz == null);
    }
    @Override
    public void insertar(T x){
        if(isEmpty()){
            nodoABB nuevo = new nodoABB(x);
            raiz = nuevo;
        }else{
            insertar(x,raiz);
        }
    }
    
    private void insertar(T dato, nodoABB nodo){
       if(nodo != null){
           nodoABB elNodo = new nodoABB(dato);
           if(elNodo.compareTo(nodo.getDato()) <0){
               if(nodo.getDer() == null){
                   nodoABB nuevo = new nodoABB(dato);
                   nodo.setDer(nuevo);
               }else{
                   insertar(dato, nodo.getDer());
               }
           }else{
               if(nodo.getIzq() == null){
                   nodoABB nuevo = new nodoABB(dato);
                   nodo.setIzq(nuevo);
               }else{
                   insertar(dato, nodo.getIzq());
               }
           }
       }
    }
    
    @Override
    public void eliminar(T dato){
        eliminar(dato, this.raiz);
    }
    private void eliminar(T dato, nodoABB nodo){
        if(nodo == null){
            System.out.println("No se encuentra el nodo");
        }else{
            nodoABB elNodo = new nodoABB(dato);
            if(elNodo.compareTo(nodo.getDato()) < 0){
                eliminar(dato, nodo.getIzq());
            }else if(elNodo.compareTo(nodo) > 0 ){
                eliminar(dato, nodo.getIzq());
            }else{
                if(nodo.getDer() != null && nodo.getIzq() != null){
                   eliminarCon2Hijos(nodo);
                }else if(nodo.getDer() == null){
                    nodo = nodo.getIzq();
                }else if(nodo.getIzq() == null){
                    nodo = nodo.getDer();
                }
            }
        }
    }
    
    private void eliminarCon2Hijos(nodoABB nodo){
        nodoABB min = buscarMin(nodo.getDer());
        nodo.setDato(min.getDato());
        T dato = (T) min.getDato();
        eliminar(dato, nodo.getDer());
    }
    private nodoABB buscarMin(nodoABB nodo){
        if(nodo.getIzq() == null){
            return nodo;
        }
        return buscarMin(nodo.getIzq());
    }
    @Override
    public void borrarMinimo(nodoABB nodo1){
        if(nodo1.getIzq() != null){
//            nodoABB aux = new nodoABB(nodo.getDato());
            borrarMinimo(nodo1);
        }else{
            raiz = raiz.getDer();
        }
    }
    
    private void borrarMinimo2(nodoABB nodo){
        
            if(nodo.getIzq().getIzq() == null){
                 nodo.setIzq(nodo.getIzq().getDer());
            }else{
                borrarMinimo(nodo.getIzq());
            }
    }
    @Override
    public void imprimirPreOrder(){
            imprimirPreOrder(this.raiz);
    }

    public void imprimirPreOrder(nodoABB a){
        if (a!=null){
        System.out.println(a.getDato()+" - ");
        imprimirPreOrder(a.getIzq());
        imprimirPreOrder(a.getDer());
        }
    }
    @Override
    public void imprimirPostOrder(){
		imprimirPostOrder(this.raiz);
    }
    public void imprimirPostOrder(nodoABB a){
            if (a!=null){
            imprimirPostOrder(a.getIzq());
            imprimirPostOrder(a.getDer());
            System.out.println(a.getDato()+" - ");
           }
    }
    @Override
    public void imprimirInOrder(){
        imprimirInOrder(this.raiz);
    }
    public void imprimirInOrder(nodoABB a){
            if (a!=null){
                imprimirInOrder(a.getIzq());
                imprimirInOrder(a.getDer());
                 System.out.println(a.getDato()+" - ");
             }
    }
    public int cantDeNodos(nodoABB a){
    if (a!=null){
        return ( 1 + cantDeNodos(a.getIzq())) + (cantDeNodos(a.getDer()));

     }
    return 0;
    }


}
